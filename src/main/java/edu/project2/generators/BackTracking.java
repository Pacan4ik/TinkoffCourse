package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class BackTracking implements Generator {

    private static final int[][] OFFSETS = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
    private static final Random RANDOM = new Random();
    private final List<Cell> alreadyVisited = new ArrayList<>();

    @Override
    public Maze generate(int height, int width) {
        //odd
        int oddHeight = height / 2 * 2 + 1;
        int oddWidth = width / 2 * 2 + 1;

        Stack<Cell> currentBranch = new Stack<>();
        Cell[][] grid = initGrid(oddHeight, oddWidth);

        Coordinate startCoord = new Coordinate(
            RANDOM.nextInt(oddHeight / 2) * 2 + 1,
            RANDOM.nextInt(oddWidth / 2) * 2 + 1
        );

        Cell currentCell = grid[startCoord.row()][startCoord.col()];
        alreadyVisited.add(currentCell);
        currentBranch.add(currentCell);

        do {
            Cell newCell = getNeighbour(currentCell, grid);
            if (newCell != null) {
                alreadyVisited.add(newCell);
                currentBranch.add(newCell);
                currentCell = newCell;
            } else {
                while (!currentBranch.isEmpty()) {
                    currentCell = currentBranch.pop();
                    if (!peekPositions(currentCell, grid).isEmpty()) {
                        break;
                    }
                }
            }
        } while (!currentBranch.isEmpty());

        if (oddHeight > oddWidth) {
            createEntryAndExitHorizontal(grid, oddWidth);
        } else {
            createEntryAndExitOnVertical(grid, oddHeight);
        }
        return new Maze(oddHeight, oddWidth, grid);
    }

    private void createEntryAndExitHorizontal(Cell[][] grid, int width) {
        int entryCol = RANDOM.nextInt(1, width - 1);
        int exitCol = RANDOM.nextInt(1, width - 1);

        if (grid[1][entryCol].type() == Cell.Type.WALL) {
            entryCol = adjustPosition(entryCol, width);
        }

        if (grid[grid.length - 2][exitCol].type() == Cell.Type.WALL) {
            exitCol = adjustPosition(exitCol, width);
        }

        grid[0][entryCol] = new Cell(0, entryCol, Cell.Type.PASSAGE);
        grid[grid.length - 1][exitCol] = new Cell(grid.length - 1, exitCol, Cell.Type.PASSAGE);
    }

    private void createEntryAndExitOnVertical(Cell[][] grid, int height) {
        int entryRow = RANDOM.nextInt(1, height - 1);
        int exitRow = RANDOM.nextInt(1, height - 1);

        if (grid[entryRow][1].type() == Cell.Type.WALL) {
            entryRow = adjustPosition(entryRow, height);
        }

        if (grid[exitRow][grid[0].length - 2].type() == Cell.Type.WALL) {
            exitRow = adjustPosition(exitRow, height);
        }

        grid[entryRow][0] = new Cell(entryRow, 0, Cell.Type.PASSAGE);
        grid[exitRow][grid[0].length - 1] = new Cell(exitRow, grid[0].length - 1, Cell.Type.PASSAGE);
    }

    private int adjustPosition(int position, int limit) {

        if (position == limit - 2) {
            return position - 1;
        } else {
            return position + 1;
        }
    }

    private Cell getNeighbour(Cell cell, Cell[][] grid) {
        List<Integer> positions = peekPositions(cell, grid);
        if (positions.isEmpty()) {
            return null;
        }

        int randomPosition = positions.get(RANDOM.nextInt(positions.size()));
        int row = cell.row() + OFFSETS[randomPosition][0];
        int col = cell.col() + OFFSETS[randomPosition][1];

        if (isValidCell(row, col, grid) && !alreadyVisited.contains(grid[row][col])) {
            int wallRow = (cell.row() + row) / 2;
            int wallCol = (cell.col() + col) / 2;
            grid[wallRow][wallCol] = new Cell(wallRow, wallCol, Cell.Type.PASSAGE);
            return grid[row][col];
        }

        return null;
    }

    private List<Integer> peekPositions(Cell cell, Cell[][] grid) {
        int row = cell.row();
        int col = cell.col();
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < OFFSETS.length; i++) {
            int newRow = row + OFFSETS[i][0];
            int newCol = col + OFFSETS[i][1];

            if (isValidCell(newRow, newCol, grid) && !alreadyVisited.contains(grid[newRow][newCol])) {
                positions.add(i);
            }
        }

        return positions;
    }

    private boolean isValidCell(int row, int col, Cell[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    private Cell[][] initGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];

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

        return grid;
    }
}
