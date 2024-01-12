package edu.hw6.task3;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.regex.Pattern;

public class Filters {

    private Filters() {
    }

    @SuppressWarnings("ConstantName")
    public static final AbstractFilter regularFile = Files::isRegularFile;
    @SuppressWarnings("ConstantName")
    public static final AbstractFilter readable = Files::isReadable;
    @SuppressWarnings("ConstantName")
    public static final AbstractFilter writable = Files::isWritable;
    @SuppressWarnings("ConstantName")
    public static final AbstractFilter hidden = Files::isHidden;

    public static AbstractFilter largerThan(long size) {
        return entry -> Files.size(entry) > size;
    }

    public static AbstractFilter lessThan(long size) {
        return entry -> Files.size(entry) < size;
    }

    public static AbstractFilter sizeExactly(long size) {
        return entry -> Files.size(entry) == size;
    }

    public static AbstractFilter globMatches(String glob) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return entry -> pathMatcher.matches(entry.getFileName());

    }

    public static AbstractFilter magicNumber(int... values) {
        return entry -> {
            try (FileChannel channel = FileChannel.open(entry)) {
                ByteBuffer buffer = ByteBuffer.allocate(values.length);
                int bytesRead = channel.read(buffer);
                int valPointer = 0;
                while (bytesRead != -1) {
                    buffer.flip();
                    while (buffer.hasRemaining() && valPointer != values.length) {
                        if (buffer.get() != (byte) values[valPointer++]) {
                            return false;
                        }
                    }
                    buffer.clear();
                    bytesRead = channel.read(buffer);
                }
                return valPointer == values.length;
            }

        };
    }

    public static AbstractFilter regexContains(String regex) {
        return entry -> Pattern.matches(regex, entry.getFileName().toString());
    }

}

