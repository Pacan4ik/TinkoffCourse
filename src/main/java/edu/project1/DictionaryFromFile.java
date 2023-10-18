package edu.project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
            FileReader fileReader =
                new FileReader(DictionaryFromFile.class.getClassLoader()
                    .getResource("Dictionary").getFile());
            readWords(fileReader);
            fileReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    DictionaryFromFile(String path) {
        try {
            if (!new File(path).isFile()) {
                throw new FileNotFoundException(String.format("File %s doesn't exit", path));
            }
            FileReader fileReader = new FileReader(path);
            readWords(fileReader);
            fileReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void readWords(FileReader fileReader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            words.add(bufferedReader.readLine());
        }
        bufferedReader.close();
    }

    @Override
    public @NotNull String getRandomWord() {
        return words.get(random.nextInt(words.size())
        );
    }

}
