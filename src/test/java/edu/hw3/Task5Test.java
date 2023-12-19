package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Task5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    static Arguments[] contacts() {
        return new Arguments[] {
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
                new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"},
                Task5.Order.ASC
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
                new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"},
                Task5.Order.DESK
            ),
            Arguments.of(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl"},
                new String[] {"Leonhard Euler", "Paul Erdos", "Carl"},
                Task5.Order.DESK
            ),
            Arguments.of(
                new String[] {"John Locke", "Thomas Aquinas", "David", "Rene Descartes"},
                new String[] {"Thomas Aquinas", "David", "Rene Descartes", "John Locke"},
                Task5.Order.ASC
            ),

        };
    }

    @ParameterizedTest
    @MethodSource("contacts")
    void shouldParseContacts(String[] names, String[] expected, Task5.Order order) {
        Contact[] actual = Task5.parseContacts(names, order);

        assertThat(actual).extracting(Contact::getFullName).containsExactly(expected);

    }

    @Test
    void shouldReturnEmptyWhenEmpty() {
        //given
        String[] names = new String[] {};

        //when
        Contact[] contacts = Task5.parseContacts(names, Task5.Order.DESK);

        //then
        assertThat(contacts).isEmpty();
    }

    @Test
    void shouldReturnEmptyWhenNull() {
        //given
        String[] names = null;

        //when
        Contact[] contacts = Task5.parseContacts(names, Task5.Order.DESK);

        //then
        assertThat(contacts).isEmpty();
    }
}
