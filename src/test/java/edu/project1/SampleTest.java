package edu.project1;

import com.github.stefanbirkner.systemlambda.Statement;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SampleTest {
    @Test
    @DisplayName("(GameRunner) Игра завершается выигрышем, если слово угадано")
    void gameIsWon() throws Exception {
        //given
        String[] words = {"слово"};
        String inputData = "с\nл\nо\nв\n";

        //when
        String gameOutput = tapSystemOut(() -> GameRunner.runWithInputStream(
            words,
            new ByteArrayInputStream(inputData.getBytes()),
            10
        ));

        //then
        assertThat(gameOutput).contains("Вы победили!");

    }

    @Test
    @DisplayName("(GameRunner) Игра завершается поражение, если превышено количество попыток")
    void gameIsLost() throws Exception {
        //given
        String[] words = {"слово"};
        String inputData = "к\nк\nк\n";

        //when
        Statement statement = () -> GameRunner.runWithInputStream(
            words,
            new ByteArrayInputStream(inputData.getBytes()),
            3
        );
        String gameOutput = tapSystemOut(statement);

        //then
        assertThat(gameOutput).contains("Вы проиграли!");

    }

    @Test
    @DisplayName("(GameRunner) Отсутствует чувстивительность к регистру")
    void notCaseSensetive() throws Exception {
        //given
        String[] words = {"СлОво"};
        String inputData = "с\nЛ\nо\nВ\n";

        //when
        Statement statement = () -> GameRunner.runWithInputStream(
            words,
            new ByteArrayInputStream(inputData.getBytes()),
            3
        );
        String gameOutput = tapSystemOut(statement);

        //then
        assertThat(gameOutput).contains("Вы победили!");

    }

    @Test
    @DisplayName("(Game) Игра не запускается, если слово имеет неккоректную длину")
    void gameWontStart() {
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
    void outputWord() throws Exception {
        //given
        String word = "w*rd";

        //when
        String output = tapSystemOut(() -> (new ConsoleInterface()).typeShadowedWord(word));

        //then
        assertThat(output).contains(word);

    }

    @Test
    @DisplayName("(ConsoleInterface) буква неугадана")
    void outputMistake() throws Exception {
        //given
        int mistakes = 3;
        int maxMistakes = 10;

        //when
        String output = tapSystemOut(() -> (new ConsoleInterface()).notifyWrongGuess(mistakes, maxMistakes));

        //then
        assertThat(output.toLowerCase()).contains("промах");
        assertThat(output).contains(String.format("%d из %d", mistakes, maxMistakes));
    }

    @Test
    @DisplayName("(ConsoleInterface) буква угадана")
    void letterIsGuessed() throws Exception {
        //given

        //when
        String output = tapSystemOut((new ConsoleInterface())::notifyRightGuess);

        //then
        assertThat(output.toLowerCase()).contains("правильно");
    }

    @Test
    @DisplayName("(ConsoleInterface)Сообщение о победе")
    void winMessage() throws Exception {
        //given

        //when
        String output = tapSystemOut(() -> (new ConsoleInterface()).notifyWin());

        //then
        assertThat(output.toLowerCase()).contains("вы победили");
    }

    @Test
    @DisplayName("(ConsoleInterface)Сообщение о поражении")
    void loseMessage() throws Exception {
        //given

        //when
        String output = tapSystemOut(() -> (new ConsoleInterface()).notifyLose());

        //then
        assertThat(output).contains("Вы проиграли");
    }
}
