package edu.project2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Renderer {

    private static final String WALL = "# ";
    private static final String PASSAGE = "  ";

    private static final String PATH = "\u001B[32m" + "▪ " + "\u001B[0m";

    public String render(Maze maze) {
        Cell[][] grid = maze.grid();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maze.height(); i++) {
            Arrays.stream(grid[i]).forEachOrdered(cell -> {
                sb.append(cell.type() == Cell.Type.WALL ? WALL : PASSAGE);
            });
            sb.append('\n');
        }
        return sb.toString();
    }

    public String render(Maze maze, @NotNull List<Coordinate> path) {
        Cell[][] grid = maze.grid();
        Set<Coordinate> coordsSet = new HashSet<>(path);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                if (coordsSet.contains(new Coordinate(i, j))) {
                    sb.append(PATH);
                } else {
                    sb.append(grid[i][j].type() == Cell.Type.WALL ? WALL : PASSAGE);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}

