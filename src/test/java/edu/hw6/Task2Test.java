package edu.hw6;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Task2Test {

    private static Path tempDir;

    @BeforeEach
    void createTempDir() throws IOException {
        tempDir = Files.createTempDirectory("tempDir");
    }

    @AfterEach
    void deleteTempDir() throws IOException {
        Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e)
                throws IOException {
                if (e == null) {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                } else {
                    throw e;
                }
            }
        });

    }

    @Test
    void shouldCopy() throws IOException {
        //given
        Path file = Paths.get(tempDir.toString(), "newfile.txt");
        Files.createFile(file);

        //when
        Task2.cloneFile(file);

        //then
        List<String> fileNames = new ArrayList<>();
        try (var stream = Files.newDirectoryStream(tempDir)) {
            stream.forEach(path -> fileNames.add(path.getFileName().toString()));
        }
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "newfile.txt",
                "newfile — копия.txt"
            )
        );

    }

    @Test
    void shouldCopyContent() throws IOException {
        //given
        String text = "some text";
        Path file = Paths.get(tempDir.toString(), "newfile.txt");
        Files.createFile(file);
        try (FileWriter writer = new FileWriter(file.toFile())) {
            writer.write(text);
        }

        //when
        Task2.cloneFile(file);

        //then
        List<String> lines = Files.readAllLines(Paths.get(tempDir.toString() + "/" + "newfile — копия.txt"));
        Assertions.assertThat(lines.size()).isEqualTo(1);
        Assertions.assertThat(lines.get(0)).isEqualTo(text);


    }

    @Test
    void shouldCopyRepeatedly() throws IOException {
        //given
        Path file = Paths.get(tempDir.toString(), "newfile.txt");
        Files.createFile(file);

        //when
        Task2.cloneFile(file);
        Task2.cloneFile(file);
        Task2.cloneFile(file);

        //then
        List<String> fileNames = new ArrayList<>();
        try (var stream = Files.newDirectoryStream(tempDir)) {
            stream.forEach(path ->
                fileNames.add(path.getFileName().toString())
            );
        }
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "newfile.txt",
                "newfile — копия.txt",
                "newfile — копия (2).txt",
                "newfile — копия (3).txt"
            )
        );

    }

    @Test
    void shouldFillSpaces() throws IOException {
        //given
        Path file = Paths.get(tempDir.toString(), "newfile.txt");
        Files.createFile(file);
        Files.createFile(Paths.get(tempDir.toString(), "newfile — копия (3).txt"));
        Files.createFile(Paths.get(tempDir.toString(), "newfile — копия (5).txt"));

        //when
        Task2.cloneFile(file);
        Task2.cloneFile(file);
        Task2.cloneFile(file);
        Task2.cloneFile(file);

        //then
        List<String> fileNames = new ArrayList<>();
        try (var stream = Files.newDirectoryStream(tempDir)) {
            stream.forEach(path ->
                fileNames.add(path.getFileName().toString())
            );
        }
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "newfile.txt",
                "newfile — копия.txt",
                "newfile — копия (2).txt",
                "newfile — копия (3).txt",
                "newfile — копия (4).txt",
                "newfile — копия (5).txt",
                "newfile — копия (6).txt"
            )
        );

    }

    @Test
    void shouldCopyFileWithoutExtension() throws IOException {
        //given
        Path file = Paths.get(tempDir.toString(), "newfile");
        Files.createFile(file);

        //when
        Task2.cloneFile(file);
        Task2.cloneFile(file);

        //then
        List<String> fileNames = new ArrayList<>();
        try (var stream = Files.newDirectoryStream(tempDir)) {
            stream.forEach(path ->
                fileNames.add(path.getFileName().toString())
            );
        }
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "newfile",
                "newfile — копия",
                "newfile — копия (2)"
            )
        );

    }
}
