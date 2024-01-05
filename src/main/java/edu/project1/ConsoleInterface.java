package edu.project1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("RegexpSinglelineJava")
class ConsoleInterface implements GameInterface {

    private final InputStreamReader inputStreamReader;
    private final BufferedReader bufferedReader;

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern LETTER_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]");

    ConsoleInterface() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    ConsoleInterface(ByteArrayInputStream inputStream) {
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    @Override
    public void showRules() {
        LOGGER.info("""
            Игра "Виселица"
            Цель - угадать слово до окончания "виселицы".
            Игрок должен вводить по одной букве, тем самым угадав слово целиком
            При угадывании слова игрок выигрывает, при достижении лимита ошибок — проигрывает.
            /exit - если хотите сдатьcя""");
    }

    @Override
    public char askLetter() throws RuntimeException {

        try {
            String input = bufferedReader.readLine();
            while (!input.equals("/exit")) {
                if (LETTER_PATTERN.matcher(input).matches()) {
                    return input.charAt(0);
                }
                LOGGER.info("Неккоректный ввод. Возможные символы: " + LETTER_PATTERN);
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new ForcedExitException("User forces exit (/exit)");
    }

    @Override
    public void typeWord(String shadowedWord) {
        LOGGER.info("Слово: " + shadowedWord);
    }

    @Override
    public void notifyWrongGuess(int amountMistakes, int maxMistakes) {
        LOGGER.info(String.format("Промах! Ошибка %d из %d%n", amountMistakes, maxMistakes));
    }

    @Override
    public void notifyRightGuess() {
        LOGGER.info("Правильно!");
    }

    @Override
    public void notifyGameOver(Game.GameState gameState) {
        switch (gameState) {
            case WIN -> LOGGER.info("Вы победили!");
            case LOSE -> LOGGER.info("Вы проиграли!");
            case EXIT -> LOGGER.info("Вы завершили игру.");
            default -> throw new RuntimeException("Game is not over, but there was an attempt to notify");
        }

    }

    public void close() throws IOException {
        bufferedReader.close();
        inputStreamReader.close();
        LOGGER.warn("closed");
    }
}
