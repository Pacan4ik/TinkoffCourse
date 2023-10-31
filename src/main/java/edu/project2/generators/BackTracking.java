package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class BackTracking implements Generator {

    private static final int[][] OFFSETS = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
    private static final Random random = new Random();
    private List<Cell> alreadyVisited = new ArrayList<>();
    public Coordinate startCord;

    @Override
    public Maze generate(int height, int width) {
        int oddHeight = height / 2 * 2 + 1;
        int oddWidth = width / 2 * 2 + 1;

        Stack<Cell> currentBranch = new Stack<>();

        Cell[][] grid = initGrid(oddHeight, oddWidth);
        startCord =
            new Coordinate(
                random.nextInt(oddHeight / 2) * 2 + 1,
                random.nextInt(oddHeight / 2) * 2 + 1
            );
        Cell curCell = grid[startCord.row()][startCord.col()];
        alreadyVisited.add(curCell);
        currentBranch.add(curCell);
        do {
            Cell newCell = getNeighbour(curCell, grid);
            if (newCell != null) {
                alreadyVisited.add(newCell);
                currentBranch.add(newCell);
                curCell = newCell;
            } else {
                while (!currentBranch.isEmpty()){
                    curCell = currentBranch.pop();
                    if (canPeek(curCell, grid)){
                        break;
                    }
                }
            }
        } while (!currentBranch.isEmpty());
        return new Maze(oddHeight, oddWidth, grid);
    }

    private Cell getNeighbour(Cell cell, Cell[][] grid) {
        int row;
        int col;
        int height = grid.length;
        int width = grid[0].length;
        int rnd = random.nextInt(4);
        row = cell.row() + OFFSETS[rnd][0];
        col = cell.col() + OFFSETS[rnd][1];
        switch (0) {
            case 0:

                if ((row < height && col < width && row >= 0 && col >= 0) &&
                    !alreadyVisited.contains(grid[row][col])) {
                    grid[row][col] = new Cell(row, col - 1, Cell.Type.PASSAGE);
                    return grid[row][col];
                }
            case 1:
                row = cell.row() + OFFSETS[1][0];
                col = cell.col() + OFFSETS[1][1];
                if ((row < height && col < width && row >= 0 && col >= 0) &&
                    !alreadyVisited.contains(grid[row][col])) {
                    grid[row - 1][col] = new Cell(row - 1, col, Cell.Type.PASSAGE);
                    return grid[row][col];
                }
            case 2:
                row = cell.row() + OFFSETS[2][0];
                col = cell.col() + OFFSETS[2][1];
                if ((row < height && col < width && row >= 0 && col >= 0) &&
                    !alreadyVisited.contains(grid[row][col])) {
                    grid[row][col + 1] = new Cell(row, col + 1, Cell.Type.PASSAGE);
                    return grid[row][col];
                }
            case 3:
                row = cell.row() + OFFSETS[3][0];
                col = cell.col() + OFFSETS[3][1];
                if ((row < height && col < width && row >= 0 && col >= 0) &&
                    !alreadyVisited.contains(grid[row][col])) {
                    grid[row + 1][col] = new Cell(row + 1, col, Cell.Type.PASSAGE);
                    return grid[row][col];

                }
            default:
                return null;
        }

    }

    private boolean canPeek(Cell cell, Cell[][] grid) {
        int row;
        int col;
        int height = grid.length;
        int width = grid[0].length;
        for (int[] offset : OFFSETS) {
            row = cell.row() + offset[0];
            col = cell.col() + offset[1];
            if (row < height && col < width && row >= 0 && col >= 0 && !alreadyVisited.contains(grid[row][col])) {
                return true;
            }
        }
        return false;

    }

    private Cell[][] initGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];

        //cтенки
        for (int i = 0; i < width; i++) {
            grid[0][i] = new Cell(0, i, Cell.Type.WALL);
            grid[height - 1][i] = new Cell(height - 1, i, Cell.Type.WALL);
        }
        for (int i = 1; i < height - 1; i++) {
            grid[i][0] = new Cell(i, 0, Cell.Type.WALL);
            grid[i][width - 1] = new Cell(i, width - 1, Cell.Type.WALL);
        }

        //внутри

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
