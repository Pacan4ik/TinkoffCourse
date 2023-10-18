package edu.project1;

import java.util.Arrays;
import java.util.regex.Pattern;

final class Game {
    private enum GameState {
        NOT_STARTED,
        IN_PROCESS,
        OVER
    }

    private final static int MIN_WORD_LEN = 3;

    private GameState gameState = GameState.NOT_STARTED;

    private final Dictionary dictionary;

    private final GameInterface gameInterface;

    private String word;

    private char[] shadowedWord;

    private final int maxMistakes;

    Game(Dictionary dictionary, GameInterface gameInterface, int maxMistakes) {
        this.maxMistakes = maxMistakes;
        this.dictionary = dictionary;
        this.gameInterface = gameInterface;
    }

    void gameStart() {
        try {
            chooseWord();
        } catch (BadWordsSourceException e) {
            throw new RuntimeException(e);
        }
        initShadowedWord();
        gameInterface.showRules();
        gameState = GameState.IN_PROCESS;
        int mistakes = 0;
        while (gameState != GameState.OVER) {
            gameInterface.typeShadowedWord(new String(shadowedWord));
            char guessed;
            try {
                guessed = Character.toLowerCase(gameInterface.askLetter());
            } catch (GameInterface.ForcedExitException e) {
                gameState = GameState.OVER;

                return;
            }
            if (tryOpenLetter(guessed)) {
                gameInterface.notifyRightGuess();
                if (checkWordIsCompletelyGuessed()) {
                    gameInterface.typeShadowedWord(new String(shadowedWord));
                    gameInterface.notifyWin();
                    gameState = GameState.OVER;
                }
            } else {
                gameInterface.notifyWrongGuess(++mistakes, maxMistakes);
                if (mistakes == maxMistakes) {
                    gameInterface.notifyLose();
                    gameState = GameState.OVER;
                }
            }

        }

    }

    private boolean checkWordIsCompletelyGuessed() {
        for (char c : shadowedWord) {
            if (c == '*') {
                return false;
            }
        }
        return true;
    }

    private void chooseWord() throws BadWordsSourceException {
        String wordFromDict = dictionary.getRandomWord();

        if (!Pattern.compile("[a-zA-zа-яА-Я]+")
            .matcher(wordFromDict)
            .matches()) {
            throw new BadWordsSourceException(String.format(
                "Word %s don't match pattern [a-zA-zа-яА-Я]+",
                wordFromDict
            ));
        }

        if (wordFromDict.length() < MIN_WORD_LEN) {
            throw new BadWordsSourceException("Word's len can't be less than 3");
        }

        wordFromDict = wordFromDict.toLowerCase().replace('ё', 'е');
        word = wordFromDict;
    }

    private void initShadowedWord() {
        shadowedWord = new char[word.length()];
        Arrays.fill(shadowedWord, '*');
    }

    private boolean tryOpenLetter(char letter) {
        boolean isMatched = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                if (shadowedWord[i] != letter) {
                    shadowedWord[i] = letter;
                    isMatched = true;
                } else {
                    return isMatched;
                }
            }
        }
        return isMatched;
    }

    private static class BadWordsSourceException extends RuntimeException {
        @SuppressWarnings("AvoidNoArgumentSuperConstructorCall") BadWordsSourceException() {
            super();
        }

        BadWordsSourceException(String message) {
            super(message);
        }

        BadWordsSourceException(Throwable cause) {
            super(cause);
        }

        BadWordsSourceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
