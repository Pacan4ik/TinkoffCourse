package edu.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

class DictionaryFromFile implements Dictionary {
    private final List<String> words = new ArrayList<>();
    private final Random random = new Random();

    DictionaryFromFile() {
        try {
            readWords(DictionaryFromFile.class.getResource("/Dictionary").getFile());
        } catch (IOException e) {
            throw new RuntimeException("Unable to read from default source", e);
        }

        if (!isSourceCorrect(words)) {
            throw new RuntimeException(new BadWordsSourceException());
        }
    }

    DictionaryFromFile(String path) {
        try {
            readWords(path);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read from file", e);
        }

        if (!isSourceCorrect(words)) {
            throw new RuntimeException(new BadWordsSourceException());
        }
    }

    private void readWords(String path) throws IOException {
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                words.add(bufferedReader.readLine());
            }

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public @NotNull String getRandomWord() {
        return words.get(random.nextInt(words.size())
        );
    }

}
