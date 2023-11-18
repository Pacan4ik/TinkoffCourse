package edu.hw6.task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private static final Pattern CONTENT_PATTERN = Pattern.compile("^(.)+:(.)+$");
    private static final String KEY_VALUE_FORMAT = "%s:%s";

    private final Path pathToDisk;

    private final Map<String, String> actualMap = new HashMap<>();

    public DiskMap(File file) {
        pathToDisk = file.toPath();
        readFileToMap();
    }

    private void readFileToMap() {
        List<String> lines;
        try {
            lines = Files.readAllLines(pathToDisk);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines) {
            if (!CONTENT_PATTERN.matcher(line).matches()) {
                throw new PatternSyntaxException("file contents do not match pattern", CONTENT_PATTERN.toString(), -1);
            }
            String[] keyValueStr = line.split(":");
            actualMap.put(keyValueStr[0], keyValueStr[1]);
        }
    }

    private void appendNewLine(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathToDisk.toFile(), true))) {
            writer.write(line + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkString(String string) {
        if (string.isEmpty() || string.contains("\n")) {
            throw new IllegalArgumentException("Expecting only not empty one line arguments");
        }
    }

    @Override
    public int size() {
        return actualMap.size();
    }

    @Override
    public boolean isEmpty() {
        return actualMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return actualMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return actualMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return actualMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        checkString(key);
        checkString(value);
        String resPutting = actualMap.put(key, value);
        if (resPutting != null) {
            removeFromFile(String.format(KEY_VALUE_FORMAT, key, resPutting));
        }
        appendNewLine(String.format(KEY_VALUE_FORMAT, key, value));
        return resPutting;
    }

    @Override
    public String remove(Object key) {
        String resRemoving = actualMap.remove(key);
        if (resRemoving != null) {
            removeFromFile(String.format(KEY_VALUE_FORMAT, key, resRemoving));
        }
        return resRemoving;
    }

    private void removeFromFile(String lineToRemove) {
        try {
            List<String> lines = Files.readAllLines(pathToDisk).stream()
                .filter(line -> !line.equals(lineToRemove))
                .toList();
            Files.write(pathToDisk, lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        actualMap.clear();
        try {
            Files.write(pathToDisk, Collections.emptyList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return Collections.unmodifiableSet(actualMap.keySet());
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return Collections.unmodifiableCollection(actualMap.values());
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return Collections.unmodifiableSet(actualMap.entrySet());
    }
}
