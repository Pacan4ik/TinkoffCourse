package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class DictionaryFromScratch implements Dictionary {
    private final Random random = new Random();
    private final String[] words;

    public DictionaryFromScratch(String[] words) {
        this.words = words;
    }

    @Override
    public @NotNull String getRandomWord() {
        return words[random.nextInt(words.length)];
    }
}
