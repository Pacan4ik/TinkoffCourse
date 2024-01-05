package edu.project1;

import java.util.Arrays;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

class DictionaryFromScratch implements Dictionary {
    private final Random random = new Random();
    private final String[] words;

    DictionaryFromScratch(String[] words) {
        this.words = words;
        if (!isSourceCorrect(Arrays.stream(words).toList())) {
            throw new RuntimeException(new BadWordsSourceException());
        }

    }

    @Override
    public @NotNull String getRandomWord() {
        return words[random.nextInt(words.length)];
    }

}
