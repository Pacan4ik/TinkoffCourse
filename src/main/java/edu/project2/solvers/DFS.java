package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.LinkedList;
import java.util.List;

public class DFS implements Solver {

    protected int minPathLength = 0;

    protected Cell[][] grid;
    protected int height;
    protected int width;

    protected static final int[][] OFFSETS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    @Override
    public List<Coordinate> solve(Maze maze) {
        grid = maze.grid();
        height = maze.height();
        width = maze.width();
        List<Coordinate> startPath = new LinkedList<>();
        startPath.add(maze.start());
        return findPath(startPath, maze.end());
    }

    protected List<Coordinate> findPath(List<Coordinate> currentPath, Coordinate end) {
        if (minPathLength != 0 && currentPath.size() >= minPathLength) {
            return null;
        }
        Coordinate curCoord = currentPath.getLast();
        if (curCoord.equals(end)) {
            minPathLength = currentPath.size();
            return currentPath;
        }

        List<Coordinate> minPath = null;
        for (int i = 0; i < OFFSETS.length; i++) {
            Coordinate newCoord = getNewCoordinate(curCoord, i);
            List<Coordinate> newPath = null;
            if (isPossibleMove(newCoord) && !currentPath.contains(newCoord)) {
                newPath = new LinkedList<>(currentPath);
                newPath.add(newCoord);
                List<Coordinate> tempPath = findPath(newPath, end);
                if (tempPath != null && (minPath == null || tempPath.size() < minPath.size())) {
                    minPath = tempPath;
                }
            }
        }

        return minPath;
    }

    protected boolean isPossibleMove(Coordinate coordinate) {
        return ((coordinate.row() >= 0 && coordinate.row() < height)
            && (coordinate.col() >= 0 && coordinate.col() < width)
            && grid[coordinate.row()][coordinate.col()].type() == Cell.Type.PASSAGE);
    }

    protected Coordinate getNewCoordinate(Coordinate current, int offsetIndex) {
        return new Coordinate(
            current.row() + OFFSETS[offsetIndex][0],
            current.col() + OFFSETS[offsetIndex][1]
        );
    }
}
