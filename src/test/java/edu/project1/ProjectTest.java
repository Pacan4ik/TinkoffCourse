package edu.project1;

import java.io.ByteArrayInputStream;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThat(logs.getLast().toLowerCase())
            .isEqualTo("вы победили!");

    }

    @Test
    @Disabled
    @DisplayName("(GameRunner) Игра завершается поражением, если превышено количество попыток")
    void gameIsLost() {
        //given
        String[] words = {"слово"};
        String inputData = "к\nк\nк\n";

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
            .isEqualTo("вы проиграли");

    }

    @Test
    @DisplayName("(Game) Игра не запускается, если слово имеет неккоректную длину")
    void gameWillNotStartIfLengthIsInCorrect() {
        //given
        String[] words = {"да"};

        //when
        Game game = new Game(
            new DictionaryFromScratch(words),
            new ConsoleInterface(),
            10
        );

        //then
        assertThrows(RuntimeException.class, game::gameStart);
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
        (new ConsoleInterface()).typeShadowedWord(word);
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

}
