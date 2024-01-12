package edu.project1;

import java.util.Arrays;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

class DictionaryDefault implements Dictionary {

    private final Random random = new Random();

    private final String[] words = {
        "компьютер",
        "шоколад",
        "автомобиль",
        "книга"
    };

    DictionaryDefault() {
        if (!isSourceCorrect(Arrays.stream(words).toList())) {
            throw new RuntimeException(new BadWordsSourceException());
        }
    }

    @Override
    @NotNull public String getRandomWord() {
        return words[random.nextInt(words.length)];
    }

}
