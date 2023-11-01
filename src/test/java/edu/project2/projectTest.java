package edu.project2;

import edu.project2.Solvers.BFS;
import edu.project2.generators.BackTracking;
import java.sql.SQLOutput;
import java.util.List;

public class projectTest {

    public static void main(String[] args) {
        BackTracking backTracking = new BackTracking();
        Maze maze = backTracking.generate(15, 91);
        Renderer renderer = new Renderer();
        System.out.println(renderer.render(maze));

        BFS bfs = new BFS();
        List<Coordinate> path = bfs.solve(maze, maze.end(), maze.start());
        System.out.println(renderer.render(maze, path));
        //System.out.println(backTracking.startCord);
    }
}
