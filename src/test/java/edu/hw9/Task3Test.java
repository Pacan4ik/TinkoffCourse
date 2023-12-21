package edu.hw9;

import edu.hw9.task3.DFSConcurrency;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.generators.BackTracking;
import edu.project2.solvers.Solver;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    void shouldReturnCorrectPath() {
        //given
        Maze maze = new BackTracking().generate(101, 101);

        //when
        Solver solver = new DFSConcurrency();
        List<Coordinate> path = solver.solve(maze);

        //then
        if (path != null) {
            Assertions.assertThat(path).allMatch(coordinate -> {
                int[][] OFFSETS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] offset : OFFSETS) {
                    Coordinate newCord = new Coordinate(coordinate.row() + offset[0], coordinate.col() + offset[1]);
                    if (path.contains(newCord)) {
                        return true;
                    }
                }
                return false;
            });
            Assertions.assertThat(path).anyMatch(coordinate -> coordinate.equals(maze.start()));
            Assertions.assertThat(path).anyMatch(coordinate -> coordinate.equals(maze.end()));
        }
    }
}
