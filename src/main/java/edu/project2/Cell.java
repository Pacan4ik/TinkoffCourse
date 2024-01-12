package edu.project2;

public record Cell(Coordinate coordinate, Type type) {

    public Cell(int row, int col, Type type) {
        this(new Coordinate(row, col), type);
    }

    public enum Type { WALL, PASSAGE }
}
