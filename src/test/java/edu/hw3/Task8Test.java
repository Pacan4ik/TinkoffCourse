package edu.hw3;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {

    @Test
    void Iter() {
        //given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        //when
        Iterator<Integer> it = new Task8.BackwardIterator<>(list);

        //then
        assertEquals(3, it.next().intValue());
        assertEquals(2, it.next().intValue());
        assertEquals(1, it.next().intValue());
        assertFalse(it.hasNext());

    }

    @Test
    void shouldThrowExceptionIfModificated() {
        //given
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        //when
        Iterator<Integer> it = new Task8.BackwardIterator<>(list);
        list.add(4);

        //then
        assertThrows(ConcurrentModificationException.class, it::next);

    }
}
