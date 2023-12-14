package edu.hw6;

import edu.hw6.task1.DiskMap;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    static File file;

    static {
        try {
            file = new File(Objects.requireNonNull(DiskMap.class.getResource("/diskmap")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void clearFile() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldWriteToFile() throws IOException {
        //given
        String key = "key";
        String value = "value";
        String key2 = "key2";
        String value2 = "value2";

        //when
        Map<String, String> diskMap = new DiskMap(file);
        diskMap.put(key, value);
        diskMap.put(key2, value2);

        //then
        Assertions.assertThat(Files.readAllLines(file.toPath()))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    key + ":" + value,
                    key2 + ":" + value2
                )
            );
    }

    @Test
    void shouldReturnValuesByKeys() throws IOException {
        //given
        String key = "key";
        String value = "value";
        String key2 = "key2";
        String value2 = "value2";

        //when
        Map<String, String> diskMap = new DiskMap(file);
        diskMap.put(key, value);
        diskMap.put(key2, value2);

        //then
        assertThat(diskMap.get(key)).isEqualTo(value);
        assertThat(diskMap.get(key2)).isEqualTo(value2);
    }

    @Test
    void shouldRemoveCorrectly() throws IOException {
        //given
        String key = "key";
        String value = "value";
        String key2 = "key2";
        String value2 = "value2";

        //when
        Map<String, String> diskMap = new DiskMap(file);
        diskMap.put(key, value);
        diskMap.put(key2, value2);
        var removed = diskMap.remove(key);

        //then
        Assertions.assertThat(Files.readAllLines(file.toPath()))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    key2 + ":" + value2
                )
            );
        assertThat(removed).isEqualTo(value);
        assertThat(diskMap.size()).isEqualTo(1);

    }

    @Test
    void shouldRewriteExistingValueByKey() throws IOException {
        //given
        String key = "key";
        String value = "value";
        String value2 = "value2";

        //when
        Map<String, String> diskMap = new DiskMap(file);
        diskMap.put(key, value);
        diskMap.put(key, value2);

        //then
        assertThat(diskMap.get(key)).isEqualTo(value2);
        assertThat(diskMap.size()).isEqualTo(1);
        Assertions.assertThat(Files.readAllLines(file.toPath()))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    key + ":" + value2
                )
            );

    }


    @Test
    void shouldClearCorrectly() throws IOException {
        //given
        Map<String, String> diskMap = new DiskMap(file);
        diskMap.put("key", "value");
        diskMap.put("key2", "value2");

        //when
        diskMap.clear();

        //then
        assertThat(diskMap.size()).isEqualTo(0);
        assertThat(Files.readAllLines(file.toPath())).isEqualTo(Collections.emptyList());

    }

}
