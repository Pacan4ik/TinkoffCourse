package edu.hw6;

import edu.hw6.task3.Filters;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    private static final Path dir;

    static {
        try {
            dir = Paths.get(Objects.requireNonNull(Task3Test.class.getResource("/hw6Task3")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldFilterReadable() {
        //given
        DirectoryStream.Filter<Path> filter = Filters.readable;

        //when
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            stream.forEach(
                path -> fileNames.add(path.getFileName().toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "123_file.txt",
                "456_file.txt",
                "defaultfile",
                "hidden",
                "notepad.txt",
                "OnlyRead",
                "PNG.png",
                "Архив WinRAR.rar"
            )
        );
    }

    @Test
    void shouldFilterWritable() {
        //given
        DirectoryStream.Filter<Path> filter = Filters.writable;

        //when
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            stream.forEach(
                path -> fileNames.add(path.getFileName().toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "123_file.txt",
                "456_file.txt",
                "defaultfile",
                "hidden",
                "notepad.txt",
                "PNG.png",
                "Архив WinRAR.rar"
            )
        );
    }

    @Test
    void shouldFilterHidden() {
        //given
        DirectoryStream.Filter<Path> filter = Filters.hidden;

        //when
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            stream.forEach(
                path -> fileNames.add(path.getFileName().toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "hidden"
            )
        );
    }

    @Test
    void shouldFilterLarger() {
        //given
        DirectoryStream.Filter<Path> filter = Filters.largerThan(10);

        //when
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            stream.forEach(
                path -> fileNames.add(path.getFileName().toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "456_file.txt",
                "notepad.txt",
                "PNG.png",
                "Архив WinRAR.rar"
            )
        );
    }

    @Test
    void shouldFilterMagicNumber() {
        //given
        DirectoryStream.Filter<Path> filter = Filters.magicNumber(0x89, 'P', 'N', 'G');

        //when
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            stream.forEach(
                path -> fileNames.add(path.getFileName().toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "PNG.png"
            )
        );
    }

    @Test
    void shouldFilterGlobMatches() {
        //given
        DirectoryStream.Filter<Path> filter = Filters.globMatches("*.rar");

        //when
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            stream.forEach(
                path -> fileNames.add(path.getFileName().toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "Архив WinRAR.rar"
            )
        );
    }

    @Test
    void shouldFilterRegex() {
        //given
        DirectoryStream.Filter<Path> filter = Filters.regexContains("^\\d+_[a-z]+\\.txt$");

        //when
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            stream.forEach(
                path -> fileNames.add(path.getFileName().toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "123_file.txt",
                "456_file.txt"
            )
        );
    }

    @Test
    void shouldFilterWithAnd() {
        //given
        DirectoryStream.Filter<Path> filter = Filters.readable
            .and(Filters.largerThan(10))
            .and(Filters.magicNumber('R','a','r'))
            .and(Filters.globMatches("*.rar"))
            .and(Filters.regexContains("^Архив.*$"));

        //when
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter)) {
            stream.forEach(
                path -> fileNames.add(path.getFileName().toString())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //then
        Assertions.assertThat(fileNames).containsExactlyInAnyOrderElementsOf(
            List.of(
                "Архив WinRAR.rar"
            )
        );
    }


}
