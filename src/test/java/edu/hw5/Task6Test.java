package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task6Test {
    @Test
    void shouldSpotSubsequence(){
        //given
        String s = "abc";
        String t = "qweabcde";

        //when
        boolean isSubsequence = Task6.isSubsequence(s,t);

        //then
        assertTrue(isSubsequence);

    }

    @Test
    void shouldSpotSubsequenceInBeginning(){
        //given
        String s = "abc";
        String t = "abcde";

        //when
        boolean isSubsequence = Task6.isSubsequence(s,t);

        //then
        assertTrue(isSubsequence);

    }

    @Test
    void shouldSpotSubsequenceInEnd(){
        //given
        String s = "abc";
        String t = "qweabc";

        //when
        boolean isSubsequence = Task6.isSubsequence(s,t);

        //then
        assertTrue(isSubsequence);

    }

    @Test
    void shouldntSpotSubsequence(){
        //given
        String s = "abc";
        String t = "qweabjde";

        //when
        boolean isSubsequence = Task6.isSubsequence(s,t);

        //then
        assertFalse(isSubsequence);

    }
}
