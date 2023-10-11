package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("1.Сломанная строка")
    void brokenStringTest() {
        // given
        String str = "123456";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("214365");
    }

    @Test
    @DisplayName("2.Сломанная строка. This is a mixed up string")
    void brokenStringTest2() {
        // given
        String str = "hTsii  s aimex dpus rtni.g";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("3.Сломанная строка. Нечетное кол-во знаков.")
    void brokenStringTest3() {
        // given
        String str = "badce";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("abcde");
    }

    @Test
    @DisplayName("4.Сломанная строка. Пустая строка")
    void brokenStringTest4() {
        // given
        String str = "";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("");
    }
}
