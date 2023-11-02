package edu.project2;

import edu.project2.Solvers.DFS;
import edu.project2.Solvers.Solver;
import edu.project2.generators.BackTracking;
import edu.project2.generators.Generator;
import java.util.List;

public class Main {

    private static final int HEIGHT = 17;

    private static final int WIDTH = 147;

    private static final Generator GENERATOR = new BackTracking();

    private static final Solver SOLVER = new DFS();

    private static final Renderer RENDERER = new Renderer();

    private Main() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) {
        Maze maze = GENERATOR.generate(HEIGHT, WIDTH);
        List<Coordinate> path = SOLVER.solve(maze);
        System.out.println(RENDERER.render(maze, path));
    }
}
