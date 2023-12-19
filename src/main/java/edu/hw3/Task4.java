package edu.hw3;

import java.util.LinkedHashMap;
import java.util.Map;

public class Task4 {
    private Task4() {
    }

    private static final int MINLEGAL = 1;
    private static final int MAXLEGAL = 3999;

    private static final int[] NUMBERS = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_NUMERALS =
        {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static Map<Integer, String> convertTable = null;

    private static void initializeTable() {
        if (convertTable != null) {
            return;
        }
        convertTable = new LinkedHashMap<>(NUMBERS.length);
        for (int i = 0; i < NUMBERS.length; i++) {
            convertTable.put(NUMBERS[i], ROMAN_NUMERALS[i]);
        }
    }

    static String convertToRoman(int number) {
        if (number < MINLEGAL || number > MAXLEGAL) {
            throw new IllegalArgumentException(String.format("Expected %d .. %d", MINLEGAL, MAXLEGAL));
        }
        initializeTable();
        int remainder = number;
        StringBuilder stringBuilder = new StringBuilder();
        for (var entry : convertTable.entrySet()) {
            while (remainder >= entry.getKey()) {
                remainder -= entry.getKey();
                stringBuilder.append(entry.getValue());
            }
        }
        return stringBuilder.toString();

    }
}
