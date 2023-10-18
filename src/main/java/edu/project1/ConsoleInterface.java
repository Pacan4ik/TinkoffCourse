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
    private static final Pattern LETTER_PATTERN = Pattern.compile("[a-zA-zа-яА-Я]");

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
                LOGGER.info("Неккоректный ввод");
                input = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        close();
        throw new ForcedExitException("User forces exit (/exit)");
    }

    @Override
    public void typeShadowedWord(String shadowedWord) {
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
    public void notifyWin() {
        LOGGER.info("Вы победили!");
        close();
    }

    @Override
    public void notifyLose() {
        LOGGER.info("Вы проиграли!");
        close();
    }

    public void close() throws RuntimeException {
        try {
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Unable close readers", e);
        }
    }
}
