package edu.project2.Solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class DFS implements Solver {

    private int minPathLength = 0;
    private Cell[][] grid;
    private int height;
    private int width;

    private static final int[][] OFFSETS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    @Override
    public List<Coordinate> solve(Maze maze) {
        grid = maze.grid();
        height = maze.height();
        width = maze.width();
        List<Coordinate> startPath = new LinkedList<>();
        startPath.add(maze.start());
        return findPath(startPath, maze.end());
    }

    private List<Coordinate> findPath(List<Coordinate> currentPath, Coordinate end) {
        if (minPathLength != 0 && currentPath.size() >= minPathLength) {
            return null;
        }
        Coordinate curCoord = currentPath.getLast();
        if (curCoord.equals(end)) {
            minPathLength = currentPath.size();
            return currentPath;
        }

        List<List<Coordinate>> ways = new ArrayList<>(OFFSETS.length);
        for (int i = 0; i < OFFSETS.length; i++) {
            Coordinate newCoord = getNewCoordinate(curCoord, i);
            List<Coordinate> newPath = null;
            if (isPossibleMove(newCoord) && !currentPath.contains(newCoord)) {
                newPath = new LinkedList<>(currentPath);
                newPath.add(newCoord);
                ways.add(findPath(newPath, end));
            }
        }

        if (ways.isEmpty() || ways.stream().allMatch(Objects::isNull)) {
            return null;
        }

        return ways.stream().filter(Objects::nonNull).min(Comparator.comparing(List::size)).get();

    }

    private boolean isPossibleMove(Coordinate coordinate) {
        return ((coordinate.row() >= 0 && coordinate.row() < height)
            && (coordinate.col() >= 0 && coordinate.col() < width)
            && grid[coordinate.row()][coordinate.col()].type() == Cell.Type.PASSAGE);
    }

    private Coordinate getNewCoordinate(Coordinate current, int offsetIndex) {
        return new Coordinate(
            current.row() + OFFSETS[offsetIndex][0],
            current.col() + OFFSETS[offsetIndex][1]
        );
    }
}
