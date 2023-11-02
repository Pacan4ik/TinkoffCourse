package edu.project2;

import edu.project2.Solvers.DFS;
import edu.project2.Solvers.Solver;
import edu.project2.generators.BackTracking;
import edu.project2.generators.Generator;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectTest {

    private static Maze StringConverter(String mazeStr, Coordinate start, Coordinate end) {
        String[] splitted = mazeStr.split("\n");
        int size = splitted[0].length();
        if (!Arrays.stream(splitted).allMatch(str -> str.length() == size)) {
            throw new IllegalArgumentException("The sizes are not the same");
        }
        int width = (size + 1) / 2;
        Cell[][] cells = new Cell[splitted.length][width];
        for (int i = 0; i < splitted.length; i++) {
            for (int j = 0; j < size; j += 2) {
                cells[i][j / 2] =
                    new Cell(i, j / 2, (splitted[i].charAt(j) == ' ') ? Cell.Type.PASSAGE : Cell.Type.WALL);

            }
        }
        return new Maze(splitted.length, width, cells, start, end);
    }

    @Test
    void renderShouldMatch() {
        //given
        String mazeStr =
            """
                # # # # # # # # # # # # # # #\s
                #           #               #\s
                # # #   # # #   #   # # # # #\s
                #       #       #   #       #\s
                #   # # #   # # #   #   #   #\s
                #               #       #   #\s
                #   # # # # # # # # # # #   #\s
                #   #       #               #\s
                #   #   # # #   # # # # #   #\s
                #       #       #           #\s
                # # #   #   # # # # # # # # #\s
                #   #   #   #           #   #\s
                    #   #   #   # # #   #    \s
                #       #           #       #\s
                # # # # # # # # # # # # # # #\s
                """;
        Coordinate start = new Coordinate(12, 0);
        Coordinate end = new Coordinate(12, 14);

        //when
        Maze maze = StringConverter(mazeStr, start, end);
        Renderer renderer = new Renderer();

        //then
        Assertions.assertThat(mazeStr).isEqualTo(renderer.render(maze));

    }

    @Test
    void shouldFindPath() {
        //given
        String mazeStr =
            """
                #   # # # # #\s
                #           #\s
                # # #   # # #\s
                #           #\s
                #   # # #   #\s
                # # # # #   #\s
                """;
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(5, 5);
        Maze maze = StringConverter(mazeStr, start, end);

        //when
        Solver solver = new DFS();

        //then
        Assertions.assertThat(solver.solve(maze)).isNotNull();
    }

    @Test
    void shouldFindPathInOpenSpace() {
        //given
        String mazeStr =
            """
                #   # # # # #\s
                #           #\s
                #           #\s
                #           #\s
                #           #\s
                # # # # #   #\s
                """;
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(5, 5);
        Maze maze = StringConverter(mazeStr, start, end);

        //when
        Solver solver = new DFS();

        //then
        List<Coordinate> path = solver.solve(maze);
        Assertions.assertThat(path).isNotNull();
        //System.out.println(new Renderer().render(maze,path));
    }

    @Test
    void shouldFindPathWhenStartAndStopTheSame() {
        //given
        String mazeStr =
            """
                #   # # # # #\s
                #           #\s
                #           #\s
                #           #\s
                #           #\s
                # # # # #   #\s
                """;
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(0, 1);
        Maze maze = StringConverter(mazeStr, start, end);

        //when
        Solver solver = new DFS();

        //then
        List<Coordinate> path = solver.solve(maze);
        Assertions.assertThat(path).containsExactly(start);
    }

    @Test
    void shouldFindPathInCircledMaze() {
        //given
        String mazeStr =
            """
                #   # # # # #\s
                #           #\s
                #   #   #   #\s
                #           #\s
                #   #   #   #\s
                #           #\s
                # # # # #   #\s
                """;
        Coordinate start = new Coordinate(6, 5);
        Coordinate end = new Coordinate(0, 1);
        Maze maze = StringConverter(mazeStr, start, end);

        //when
        Solver solver = new DFS();

        //then
        List<Coordinate> path = solver.solve(maze);
        Assertions.assertThat(path).isNotNull();
        System.out.println(new Renderer().render(maze, path));
    }

    @Test
    void shouldntFindPath() {
        //given
        String mazeStr =
            """
                #   # # # # #\s
                #           #\s
                #   # # #   #\s
                #   #   #   #\s
                #   # # #   #\s
                #           #\s
                # # # # # # #\s
                """;
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(3, 3);
        Maze maze = StringConverter(mazeStr, start, end);

        //when
        Solver solver = new DFS();

        //then
        List<Coordinate> path = solver.solve(maze);
        Assertions.assertThat(path).isNull();
    }

    @Test
    void shouldGenerateThicknessOneMaze() {
        //given
        Generator generator = new BackTracking();

        //when
        Maze maze = generator.generate(7, 3);

        //then
        String mazeStr =
            """
                #   #\s
                #   #\s
                #   #\s
                #   #\s
                #   #\s
                #   #\s
                #   #\s
                """;
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(6, 1);
        //System.out.println(new Renderer().render(maze));
        Assertions.assertThat(maze.grid()).isEqualTo(StringConverter(mazeStr, start, end).grid());
    }

    @Test
    void shouldGenerateThicknessOneMazeHorizontal() {
        //given
        Generator generator = new BackTracking();

        //when
        Maze maze = generator.generate(3, 9);

        //then
        String mazeStr =
            """
                # # # # # # # # #\s
                                 \s
                # # # # # # # # #\s
                """;
        Coordinate start = new Coordinate(1, 0);
        Coordinate end = new Coordinate(1, 8);
        //System.out.println(new Renderer().render(maze));
        Assertions.assertThat(maze.grid()).isEqualTo(StringConverter(mazeStr, start, end).grid());
    }

    @Test
    void shouldThrowExceptionWhenEntriesAreWrong() {
        //given
        String mazeStr =
            """
                #   # # # # #\s
                #           #\s
                # # #   # # #\s
                #           #\s
                #   # # #   #\s
                # # # # #   #\s
                """;

        //when
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Maze maze = StringConverter(mazeStr, start, end);
        });
    }

    @Test
    void shouldReturnCorrectPath() {
        //given
        Maze maze = new BackTracking().generate(100, 100);

        //when
        Solver solver = new DFS();
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

    @Test
    void shouldFindShortestWay() {
        //given
        String mazeStr =
            """
                #   # # # # # # #\s
                #               #\s
                #   # # # # #   #\s
                #   #           #\s
                #   #   # # #   #\s
                #   #       #   #\s
                #   # # #   #   #\s
                #           #   #\s
                # # # # # # #   #\s
                """;
        Coordinate start = new Coordinate(0, 1);
        Coordinate end = new Coordinate(8, 7);
        Maze maze = StringConverter(mazeStr, start, end);

        //when
        Solver solver = new DFS();

        //then
        List<Coordinate> path = solver.solve(maze);
        Assertions.assertThat(path.size()).isEqualTo(15);
    }

}
