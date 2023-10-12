package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Сломанная строка (Task4.fixString). 123456 -> 214365")
    void brokenStringNumbersInOrderTest() {
        // given
        String str = "123456";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("214365");
    }

    @Test
    @DisplayName("Сломанная строка (Task4.fixString). This is a mixed up string")
    void brokenStringMixedUpTest() {
        // given
        String str = "hTsii  s aimex dpus rtni.g";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Сломанная строка (Task4.fixString). Нечетное кол-во знаков.")
    void brokenStringOddTest() {
        // given
        String str = "badce";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("abcde");
    }

    @Test
    @DisplayName("Сломанная строка (Task4.fixString). Пустая строка")
    void brokenStringEmptyTest() {
        // given
        String str = "";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("");
    }
}
