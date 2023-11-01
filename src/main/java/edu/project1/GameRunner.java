package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public final class GameRunner {

    private static final int MAX_MISTAKES = 5;

    private GameRunner() {
    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        run(MAX_MISTAKES);
    }

    public static void run(int maxMistakes) {
        try (ConsoleInterface consoleInterface = new ConsoleInterface()) {
            Game game = new Game(
                new DictionaryDefault(),
                consoleInterface,
                maxMistakes
            );
            game.gameStart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void run(String[] dictionary, int maxMistakes) {
        try (ConsoleInterface consoleInterface = new ConsoleInterface()) {
            Game game = new Game(
                new DictionaryFromScratch(dictionary),
                consoleInterface,
                maxMistakes
            );
            game.gameStart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void run(String dictionaryPath, int maxMistakes) {
        try (ConsoleInterface consoleInterface = new ConsoleInterface()) {
            Game game = new Game(
                new DictionaryFromFile(dictionaryPath),
                consoleInterface,
                maxMistakes
            );
            game.gameStart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void runWithInputStream(String[] dictionary, ByteArrayInputStream inputStream, int maxMistakes) {
        try (ConsoleInterface consoleInterface = new ConsoleInterface(inputStream)) {
            Game game = new Game(
                new DictionaryFromScratch(dictionary),
                consoleInterface,
                maxMistakes
            );
            game.gameStart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
