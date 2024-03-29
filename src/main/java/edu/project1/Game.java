package edu.project1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

final class Game {
    enum GameState {
        NOT_STARTED,
        IN_PROCESS,
        WIN,
        LOSE,
        EXIT
    }

    private GameState gameState = GameState.NOT_STARTED;

    private final Dictionary dictionary;

    private final GameInterface gameInterface;

    private String word;

    private char[] shadowedWord;

    private final Map<Character, Boolean> alreadyGuessed = new HashMap<>();

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
        while (gameState == GameState.IN_PROCESS) {
            gameInterface.typeWord(new String(shadowedWord));
            char guessed;
            try {
                guessed = Character.toLowerCase(gameInterface.askLetter());
            } catch (GameInterface.ForcedExitException e) {
                gameState = GameState.EXIT;
                gameInterface.notifyGameOver(gameState);
                gameInterface.typeWord(word);
                return;
            }

            if (alreadyGuessed.containsKey(guessed)) {
                continue;
            } else {
                alreadyGuessed.put(guessed, true);
            }

            if (tryOpenLetter(guessed)) {
                gameInterface.notifyRightGuess();
                if (checkWordIsCompletelyGuessed()) {
                    gameInterface.typeWord(new String(shadowedWord));
                    gameState = GameState.WIN;
                    gameInterface.notifyGameOver(gameState);
                }
            } else {
                gameInterface.notifyWrongGuess(++mistakes, maxMistakes);
                if (mistakes == maxMistakes) {
                    gameState = GameState.LOSE;
                    gameInterface.notifyGameOver(gameState);
                    gameInterface.typeWord(word);
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
}
