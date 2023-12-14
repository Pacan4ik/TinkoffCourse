package edu.hw6;

import edu.hw6.task3.Filters;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class Task4Test {
    @Test
    void shouldWriteToFile() throws IOException {  //???
        //given
        File file = File.createTempFile("tempfile", ".txt");

        //when
        Task4.write(file.toPath(), "Programming is learned by writing programs. ― Brian Kernighan");

        //then
        Assertions.assertThat(Files.readAllLines(file.toPath()))
            .containsExactly("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
