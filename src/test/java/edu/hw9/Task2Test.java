package edu.hw9;

import edu.hw9.task2.FileSystemFinder;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    private static final Path PATH;

    static {
        try {
            PATH = Paths.get(Objects.requireNonNull(Task2Test.class.getResource("/hw9Task2")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldFindDirs() {
        //given
        int n = 3;

        //when
        var dirs = FileSystemFinder.findDirectoriesWithMoreThanNFiles(PATH, n, 4);

        //then
        assertThat(dirs.stream().map(p -> p.getFileName().toString()))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    "hw9Task2",
                    "subdir"
                )
            );
    }

    @Test
    void shouldFindFilesWithExtension() {
        //given
        String extension = ".txt";

        //when
        var files = FileSystemFinder.findFiles(
            PATH,
            FileSystemFinder.SimpleFilePredicates.hasExtension(extension),
            4
        );

        //then
        assertThat(files.stream().map(p -> p.getFileName().toString()))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    "file5.txt",
                    "file.txt",
                    "file.txt",
                    "file5.txt",
                    "file2.txt"
                )
            );
    }

    @Test
    void shouldFindFilesWithSize() {
        //given
        int bytesThreshold = 2;

        //when
        var files = FileSystemFinder.findFiles(
            PATH,
            FileSystemFinder.SimpleFilePredicates.sizeMoreThan(bytesThreshold),
            4
        );

        //then
        assertThat(files.stream().map(p -> p.getFileName().toString()))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    "file2",
                    "file",
                    "file4",
                    "file.txt"
                )
            );
    }

    @Test
    void shouldFindWithAnotherPredicate() {
        //given
        Predicate<File> predicate = (f) -> f.getName().startsWith("another");

        //when
        var files = FileSystemFinder.findFiles(
            PATH,
            predicate,
            4
        );

        //then
        assertThat(files.stream().map(p -> p.getFileName().toString()))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    "anotherfile",
                    "anotherdir"
                )
            );
    }

}
