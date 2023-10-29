package edu.project1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectTest {

    @Test
    @DisplayName("(DictionaryDefault) Стандартный словарь возвращает слово")
    void dictionaryDefault() {
        //given
        Dictionary dictionary = new DictionaryDefault();

        //when
        String output = dictionary.getRandomWord();

        //then
        assertFalse(output.isBlank());
    }

    @Test
    @DisplayName("(GameRunner) Игра завершается выигрышем, если слово угадано")
    void gameIsWon() {
        //given
        String[] words = {"слово"};
        String inputData = "с\nл\nо\nв\n";

        //when
        var logCaptor = LogCaptor.forClass(ConsoleInterface.class);
        GameRunner.runWithInputStream(
            words,
            new ByteArrayInputStream(inputData.getBytes()),
            10
        );
        var logs = logCaptor.getInfoLogs();

        //then
        assertTrue(logs.stream().anyMatch(s -> s.equalsIgnoreCase("вы победили!")));

    }

    @Test
    @DisplayName("(GameRunner) Игра завершается поражением, если превышено количество попыток")
    void gameIsLost() {
        //given
        String[] words = {"слово"};
        String inputData = "а\nд\nе\n";

        //when
        var logCaptor = LogCaptor.forClass(ConsoleInterface.class);
        GameRunner.runWithInputStream(
            words,
            new ByteArrayInputStream(inputData.getBytes()),
            3
        );
        var logs = logCaptor.getInfoLogs();

        //then
        assertTrue(logs.stream().anyMatch(s -> s.equalsIgnoreCase("вы проиграли!")));

    }

    @Test
    @DisplayName("(DictionaryFromScratch) Игра не запускается, если слово имеет неккоректную длину")
    void gameWillNotStartIfLengthIsInCorrect() {
        //given
        String[] words = {"да"};

        //when

        //then
        assertThrows(RuntimeException.class, () -> new DictionaryFromScratch(words));
    }

    @Test
    @DisplayName("(ConsoleInterface) Опечатка приводит к повторному вводу")
    void typoResultsReEntry() {
        //given
        String inputData = "%\nа.\nа\n";

        //when
        GameInterface gameInterface = new ConsoleInterface(new ByteArrayInputStream(inputData.getBytes()));
        char c = gameInterface.askLetter();

        //then
        assertThat(c).isEqualTo('а');
    }

    @Test
    @DisplayName("(Game) Повторный ввод найденной буквы не приводит к поражению")
    void reEnterFoundLetter() {
        //given
        String[] words = {"слово"};
        String inputData = "с\nс\nл\nо\nв\n";

        //when
        var logCaptor = LogCaptor.forClass(ConsoleInterface.class);
        GameRunner.runWithInputStream(
            words,
            new ByteArrayInputStream(inputData.getBytes()),
            1
        );
        var logs = logCaptor.getInfoLogs();

        //then
        assertThat(logs.getLast().toLowerCase())
            .isEqualTo("вы победили!");
    }

    @Test
    @DisplayName("(Game) Повторный ввод ненайденной, но проверенной буквы не приводит к поражению")
    void reEnterWrongLetter() {
        //given
        String[] words = {"слово"};
        String inputData = "у\nу\nс\nс\nл\nо\nв\n";

        //when
        var logCaptor = LogCaptor.forClass(ConsoleInterface.class);
        GameRunner.runWithInputStream(
            words,
            new ByteArrayInputStream(inputData.getBytes()),
            2
        );
        var logs = logCaptor.getInfoLogs();

        //then
        assertThat(logs.getLast().toLowerCase())
            .isEqualTo("вы победили!");
    }

    @Test
    @DisplayName("(ConsoleInterface) /exit - выход")
    void exitTest() {
        //given
        String inputData = "/exit\n";

        //when
        GameInterface gameInterface = new ConsoleInterface(new ByteArrayInputStream(inputData.getBytes()));

        //then
        assertThrows(GameInterface.ForcedExitException.class, gameInterface::askLetter);
    }

    @Test

    @DisplayName("(ConsoleInterface) вывод слова")
    void outputWord() {
        //given
        String word = "w*rd";

        //when
        var logCaptor = LogCaptor.forClass(ConsoleInterface.class);
        (new ConsoleInterface()).typeWord(word);
        var logs = logCaptor.getInfoLogs();
        //then
        assertThat(logs.getFirst())
            .contains(word);

    }

    @Test

    @DisplayName("(GameRunner) Отсутствует чувствительность к регистру")
    void notCaseSensitive() {
        //given
        String[] words = {"СлОво"};
        String inputData = "с\nЛ\nо\nВ\n";

        //when
        var logCaptor = LogCaptor.forClass(ConsoleInterface.class);
        GameRunner.runWithInputStream(
            words,
            new ByteArrayInputStream(inputData.getBytes()),
            3
        );
        var logs = logCaptor.getInfoLogs();

        //then
        assertThat(logs.getLast().toLowerCase())
            .contains("вы победили");

    }

    @Test
    @DisplayName("(ConsoleInterface) буква не угадана")
    void outputMistake() {
        //given
        int mistakes = 3;
        int maxMistakes = 10;

        //when
        var logCaptor = LogCaptor.forClass(ConsoleInterface.class);
        (new ConsoleInterface()).notifyWrongGuess(mistakes, maxMistakes);
        var logs = logCaptor.getInfoLogs();

        //then
        assertThat(logs.getFirst().toLowerCase())
            .contains("промах");
        assertThat(logs.getFirst().toLowerCase())
            .contains(String.format("%d из %d", mistakes, maxMistakes));
    }

    @Test
    @DisplayName("(ConsoleInterface) буква угадана")
    void letterIsGuessed() {
        //given

        //when
        var logCaptor = LogCaptor.forClass(ConsoleInterface.class);
        (new ConsoleInterface()).notifyRightGuess();
        var logs = logCaptor.getInfoLogs();

        //then
        assertThat(logs.getFirst().toLowerCase())
            .contains("правильно");
    }

    @Test
    @DisplayName("(DictionaryFromFile) Словарь возвращает слово из файла")
    void dictionaryFromFileReturnsWord() throws Exception {
        //given
        Dictionary dictionary = new DictionaryFromFile();

        FileReader fileReader =
            new FileReader(DictionaryFromFile.class.getClassLoader()
                .getResource("Dictionary").getFile());
        List<String> words = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            words.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        fileReader.close();

        //when
        String output = dictionary.getRandomWord();

        //then
        assertThat(words.contains(output)).isTrue();
    }
}
