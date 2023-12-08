package edu.hw9.task3;

import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.solvers.DFS;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class DFSConcurrency extends DFS {

    private volatile int minPathLength = 0;
    @SuppressWarnings("MagicNumber")
    private int threads = 4;

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    private static final int[][] OFFSETS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    @Override
    public List<Coordinate> solve(Maze maze) {
        grid = maze.grid();
        height = maze.height();
        width = maze.width();
        List<Coordinate> startPath = new LinkedList<>();
        startPath.add(maze.start());
        try (ForkJoinPool forkJoinPool = new ForkJoinPool(threads)) {
            return forkJoinPool.invoke(new PathFinder(startPath, maze.end()));
        }
    }

    private class PathFinder extends RecursiveTask<List<Coordinate>> {

        private final List<Coordinate> currentPath;
        private final Coordinate end;

        PathFinder(List<Coordinate> currentPath, Coordinate end) {
            this.currentPath = currentPath;
            this.end = end;
        }

        @Override
        protected List<Coordinate> compute() {
            return findPath();
        }

        private List<Coordinate> findPath() {
            if (minPathLength != 0 && currentPath.size() >= minPathLength) {
                return null;
            }
            Coordinate curCoord = currentPath.getLast();
            if (curCoord.equals(end)) {
                minPathLength = currentPath.size();
                return currentPath;
            }

            List<Coordinate> minPath = null;
            List<PathFinder> recursiveTasks = new ArrayList<>();
            for (int i = 0; i < OFFSETS.length; i++) {
                Coordinate newCoord = getNewCoordinate(curCoord, i);
                List<Coordinate> newPath = null;
                if (isPossibleMove(newCoord) && !currentPath.contains(newCoord)) {
                    newPath = new LinkedList<>(currentPath);
                    newPath.add(newCoord);
                    PathFinder recursiveTask = new PathFinder(newPath, end);
                    recursiveTask.fork();
                    recursiveTasks.add(recursiveTask);
                }
            }
            for (var task : recursiveTasks) {
                List<Coordinate> tempPath = task.join();
                if (tempPath != null && (minPath == null || tempPath.size() < minPath.size())) {
                    minPath = tempPath;
                }
            }

            return minPath;
        }
    }
}
