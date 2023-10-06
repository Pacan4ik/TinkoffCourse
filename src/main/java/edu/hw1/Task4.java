package edu.hw1;

public class Task4 {
    public static String fixString(String mixedStr) {
        StringBuilder fixedStr = new StringBuilder();
        for (int i = 0; i < mixedStr.length()-1; i += 2) {
            fixedStr.append(mixedStr.charAt(i + 1));
            fixedStr.append(mixedStr.charAt(i));
        }
        if (mixedStr.length()%2!=0)
            fixedStr.append(mixedStr.charAt(mixedStr.length()-1));
        return fixedStr.toString();
    }
}
