package edu.project2;

import edu.project2.generators.BackTracking;
import edu.project2.generators.Renderer;

public class projectTest {

    public static void main(String[] args) {
        BackTracking backTracking = new BackTracking();
        Maze maze = backTracking.generate(14, 50);
        Renderer renderer = new Renderer();
        System.out.println(renderer.render(maze));
        System.out.println(backTracking.startCord);
    }
}
