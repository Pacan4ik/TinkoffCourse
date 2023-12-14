package edu.hw10;

import edu.hw10.task2.Cache;
import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.fibcalc.FibCalculator;
import edu.hw10.task2.fibcalc.SimpleRecursiveFibCalculator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Task2Test {

    private static Path tempDir;

    @BeforeEach
    void createTempDir() throws IOException {
        tempDir = Files.createTempDirectory("hw10TEMP_");
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
    void shouldReturnWorkingInstance() {
        //given
        FibCalculator c = new SimpleRecursiveFibCalculator();
        FibCalculator proxy = CacheProxy.create(c, c.getClass());

        //when
        long res = proxy.fib(6);

        //then
        Assertions.assertEquals(8, res);
    }

    @Test
    void shouldReturnValueFromCache() {
        //given
        String fileStr = Path.of(tempDir.toString(), "wrongcache").toString();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileStr))) {
            outputStream.writeObject(
                Map.of(
                    String.format("%s(%s)", "fib", Arrays.toString(new Object[] {100})),
                    (long) -5
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FibCalculator c = new SimpleRecursiveFibCalculator();
        FibCalculator proxy = CacheProxy.create(c, c.getClass(), fileStr);

        //when
        long res = proxy.fib(100);

        //then
        Assertions.assertEquals(-5, res);
    }

    public interface SimpleAdder {
        @Cache(persist = true)
        default int add(short a, short b) {
            return a + b;
        }
    }

    @Test
    void shouldWorkWithOtherInterfaces() {
        //given
        String fileStr = Path.of(tempDir.toString(), "wrongcache").toString();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileStr))) {
            outputStream.writeObject(
                Map.of(
                    String.format("%s(%s)", "add", Arrays.toString(new Object[] {(short) 100, (short) 100})),
                    (int) 0
                )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SimpleAdder simpleAdder = new SimpleAdder() {
        };
        SimpleAdder proxy = CacheProxy.create(simpleAdder, simpleAdder.getClass(), fileStr);

        //when
        int res1 = proxy.add((short) 2, (short) 3);
        int res2 = proxy.add((short) 100, (short) 100);


        //then
        Assertions.assertEquals(5, res1);
        Assertions.assertEquals(0, res2);
    }
}
