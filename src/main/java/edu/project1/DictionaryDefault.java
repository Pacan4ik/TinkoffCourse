package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

class DictionaryDefault implements Dictionary {

    private final Random random = new Random();

    private static final String[] WORDS = {
        "компьютер",
        "шоколад",
        "автомобиль",
        "книга"
    };

    @Override
    @NotNull public String getRandomWord() {
        return WORDS[random.nextInt(WORDS.length)];
    }
}
