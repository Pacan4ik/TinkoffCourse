package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class Task3Test {

    @Test
    void shouldCountFreqWithStrings(){
        //given
        List<String> list = List.of("a", "bb", "a", "bb");

        //when
        var Dict = Task3.freqDict(list);

        //then
        assertEquals(2,Dict.size());
        assertEquals(2,Dict.get("a"));
        assertEquals(2,Dict.get("bb"));

    }

    @Test
    void shouldCountFreqWithInts(){
        //given
        List<Integer> list = List.of(1, 1, 2, 2);

        //when
        var Dict = Task3.freqDict(list);

        //then
        assertEquals(2,Dict.size());
        assertEquals(2,Dict.get(1));
        assertEquals(2,Dict.get(2));

    }


    @Test
    void shouldCountFreqWithObjects(){
        //given
        Object obj1 = "str";
        List<Object> list = List.of(obj1, obj1, 3, 4);

        //when
        var Dict = Task3.freqDict(list);

        //then
        assertEquals(3,Dict.size());
        assertEquals(2,Dict.get(obj1));
        assertEquals(1,Dict.get(3));
        assertEquals(1,Dict.get(4));

    }
}
