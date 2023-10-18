package edu.project1;

import java.io.ByteArrayInputStream;

public final class GameRunner {
    private GameRunner() {
    }

    public static void run(int maxMistakes) {
        Game game = new Game(
            new DictionaryDefault(),
            new ConsoleInterface(),
            maxMistakes
        );
        game.gameStart();
    }

    public static void run(String[] dictionary, int maxMistakes) {
        Game game = new Game(
            new DictionaryFromScratch(dictionary),
            new ConsoleInterface(),
            maxMistakes
        );
        game.gameStart();
    }

    public static void run(String dictionaryPath, int maxMistakes) {
        Game game = new Game(
            new DictionaryFromFile(dictionaryPath),
            new ConsoleInterface(),
            maxMistakes
        );
        game.gameStart();
    }

    static void runWithInputStream(String[] dictionary, ByteArrayInputStream inputStream, int maxMistakes) {
        Game game = new Game(
            new DictionaryFromScratch(dictionary),
            new ConsoleInterface(inputStream),
            maxMistakes
        );
        game.gameStart();
    }

}
