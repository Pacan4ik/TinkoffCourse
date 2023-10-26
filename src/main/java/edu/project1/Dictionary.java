package edu.project1;

import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

interface Dictionary {
    @NotNull String getRandomWord();

    int MIN_WORD_LEN = 3;

    default boolean isSourceCorrect(Iterable<String> words) {
        for (String word : words) {
            if (!Pattern.compile("[a-zA-Zа-яА-Я]+")
                .matcher(word)
                .matches() || word.length() < MIN_WORD_LEN) {
                return false;
            }
        }
        return true;
    }

}

