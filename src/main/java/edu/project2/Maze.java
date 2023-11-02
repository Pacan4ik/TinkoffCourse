package edu.project2;

import java.util.Arrays;

public record Maze(int height, int width, Cell[][] grid, Coordinate start, Coordinate end) {

    private static final int MIN_SIZE = 3;

    public Maze {
        if (height < MIN_SIZE || width < MIN_SIZE) {
            throw new IllegalArgumentException("Wrong size");
        }
        if (grid.length != height || Arrays.stream(grid).anyMatch(row -> row.length != width)) {
            throw new IllegalArgumentException("The grid sizes does not match with params");
        }
        if (Arrays.stream(grid).noneMatch(
            row -> Arrays.stream(row).anyMatch(
                cell -> cell.coordinate().equals(start) && cell.type() == Cell.Type.PASSAGE))) {
            throw new IllegalArgumentException("The start is unattainable");
        }
        if (Arrays.stream(grid).noneMatch(
            row -> Arrays.stream(row).anyMatch(
                cell -> cell.coordinate().equals(end) && cell.type() == Cell.Type.PASSAGE))) {
            throw new IllegalArgumentException("The end is unattainable");
        }
    }

    @Override
    public String toString() {
        return "Maze["
            + "height=" + height + ", "
            + "width=" + width + ", "
            + "grid=" + Arrays.deepToString(grid) + ", "
            + "start=" + start + ", "
            + "end=" + end + ']';
    }

}
