package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BackTracking implements Generator {

    private static final int MIN_SIZE = 3;
    private static final int[][] OFFSETS = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
    private static final Random RANDOM = new Random();

    private final Set<Cell> alreadyVisited = new HashSet<>();

    private Coordinate entry = null;
    private Coordinate exit = null;
    private int height;
    private int width;
    Cell[][] grid;

    /**
     * Generate a maze with backtracking algorithm.
     *
     * @param height only odd numbers
     * @param width  only odd numbers
     * @return {@link Maze} with dimensions {@code height} and {@code width}
     * @throws IllegalArgumentException if {@code height} or {@code width} less than 3 or even
     **/
    @Override
    public Maze generate(int height, int width) {
        if (height < MIN_SIZE || width < MIN_SIZE) {
            throw new IllegalArgumentException();
        }

        //odd
        if (height % 2 != 1) {
            throw new IllegalArgumentException("height must be an odd number");
        }
        if (width % 2 != 1) {
            throw new IllegalArgumentException("width must be an odd number");
        }
        this.height = height;
        this.width = width;

        initGrid(height, width);

        Coordinate startCoord;
        if (width > MIN_SIZE && height > MIN_SIZE) {
            startCoord = new Coordinate(
                RANDOM.nextInt(height / 2) * 2 + 1,
                RANDOM.nextInt(width / 2) * 2 + 1
            );
        } else {
            startCoord = new Coordinate(1, 1);
        }

        Cell currentCell = grid[startCoord.row()][startCoord.col()];
        alreadyVisited.add(currentCell);
        Deque<Cell> currentBranch = new ArrayDeque<>();
        currentBranch.add(currentCell);

        do {
            Cell newCell = getNeighbour(currentCell);
            if (newCell != null) {
                alreadyVisited.add(newCell);
                currentBranch.add(newCell);
                currentCell = newCell;
            } else {
                while (!currentBranch.isEmpty()) {
                    currentCell = currentBranch.pop();
                    if (!peekPositions(currentCell).isEmpty()) {
                        break;
                    }
                }
            }
        } while (!currentBranch.isEmpty());

        if (height > width) {
            createEntryAndExitHorizontal();
        } else {
            createEntryAndExitOnVertical();
        }
        return new Maze(height, width, grid, entry, exit);
    }

    private void createEntryAndExitHorizontal() {
        int entryCol = RANDOM.nextInt(1, width - 1);
        int exitCol = RANDOM.nextInt(1, width - 1);

        if (grid[1][entryCol].type() == Cell.Type.WALL) {
            entryCol = adjustPosition(entryCol, width - 2);
        }

        if (grid[height - 2][exitCol].type() == Cell.Type.WALL) {
            exitCol = adjustPosition(exitCol, width - 2);
        }

        grid[0][entryCol] = new Cell(0, entryCol, Cell.Type.PASSAGE);
        entry = new Coordinate(0, entryCol);
        grid[height - 1][exitCol] = new Cell(height - 1, exitCol, Cell.Type.PASSAGE);
        exit = new Coordinate(height - 1, exitCol);
    }

    private void createEntryAndExitOnVertical() {
        int entryRow = RANDOM.nextInt(1, height - 1);
        int exitRow = RANDOM.nextInt(1, height - 1);

        if (grid[entryRow][1].type() == Cell.Type.WALL) {
            entryRow = adjustPosition(entryRow, height - 2);
        }

        if (grid[exitRow][width - 2].type() == Cell.Type.WALL) {
            exitRow = adjustPosition(exitRow, height - 2);
        }

        grid[entryRow][0] = new Cell(entryRow, 0, Cell.Type.PASSAGE);
        entry = new Coordinate(entryRow, 0);
        grid[exitRow][width - 1] = new Cell(exitRow, width - 1, Cell.Type.PASSAGE);
        exit = new Coordinate(exitRow, width - 1);
    }

    private int adjustPosition(int position, int limit) {
        if (position == limit) {
            return position - 1;
        } else {
            return position + 1;
        }
    }

    private Cell getNeighbour(Cell cell) {
        List<Integer> positions = peekPositions(cell);
        if (positions.isEmpty()) {
            return null;
        }

        int randomPosition = positions.get(RANDOM.nextInt(positions.size()));
        int row = cell.coordinate().row() + OFFSETS[randomPosition][0];
        int col = cell.coordinate().col() + OFFSETS[randomPosition][1];

        if (isValidCoords(row, col) && !alreadyVisited.contains(grid[row][col])) {
            int wallRow = (cell.coordinate().row() + row) / 2;
            int wallCol = (cell.coordinate().col() + col) / 2;
            grid[wallRow][wallCol] = new Cell(wallRow, wallCol, Cell.Type.PASSAGE);
            return grid[row][col];
        }

        return null;
    }

    private List<Integer> peekPositions(Cell cell) {
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < OFFSETS.length; i++) {
            int newRow = cell.coordinate().row() + OFFSETS[i][0];
            int newCol = cell.coordinate().col() + OFFSETS[i][1];

            if (isValidCoords(newRow, newCol) && !alreadyVisited.contains(grid[newRow][newCol])) {
                positions.add(i);
            }
        }

        return positions;
    }

    private boolean isValidCoords(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    private void initGrid(int height, int width) {
        grid = new Cell[height][width];

        //границы
        for (int i = 0; i < width; i++) {
            grid[0][i] = new Cell(0, i, Cell.Type.WALL);
            grid[height - 1][i] = new Cell(height - 1, i, Cell.Type.WALL);
        }
        for (int i = 1; i < height - 1; i++) {
            grid[i][0] = new Cell(i, 0, Cell.Type.WALL);
            grid[i][width - 1] = new Cell(i, width - 1, Cell.Type.WALL);
        }

        // внутри
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }

    }

}
