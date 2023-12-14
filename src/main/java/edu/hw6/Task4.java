package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    private Task4() {
    }

    public static void write(Path path, String text) {
        try (OutputStream os = Files.newOutputStream(path);
             CheckedOutputStream checkedOS = new CheckedOutputStream(os, new CRC32());
             BufferedOutputStream bufferedOS = new BufferedOutputStream(checkedOS);
             OutputStreamWriter osWriter = new OutputStreamWriter(bufferedOS, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(osWriter);
        ) {
            printWriter.write(text);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
