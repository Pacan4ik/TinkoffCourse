package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.Arrays;

public class Renderer {

    private static final String WALL = "# ";
    private static final String PASSAGE = "  ";

    public String render(Maze maze) {
        Cell[][] grid = maze.getGrid();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            Arrays.stream(grid[i]).forEachOrdered(cell -> {
                sb.append(cell.type() == Cell.Type.WALL ? WALL : PASSAGE);
            });
            sb.append('\n');
        }
        return sb.toString();
    }

}

