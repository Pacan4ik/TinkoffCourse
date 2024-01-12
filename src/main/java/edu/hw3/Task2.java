package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    static List<String> clusterize(String bracketsStr) {
        if (bracketsStr.charAt(0) != '(' || bracketsStr.charAt(bracketsStr.length() - 1) != ')') {
            return null;
        }

        int bracketsAmount = 0;
        List<String> clusters = new ArrayList<>();
        int previousIndex = -1;
        for (int i = 0; i < bracketsStr.length(); i++) {
            switch (bracketsStr.charAt(i)) {
                case '(':
                    bracketsAmount++;
                    break;
                case ')':
                    bracketsAmount--;
                    break;
                default:
                    throw new IllegalArgumentException("The string can not contain anything other than parentheses");
            }
            if (bracketsAmount == 0) {
                clusters.add(bracketsStr.substring(previousIndex + 1, i + 1));
                previousIndex = i;
            }
            if (bracketsAmount < 0) {
                return null;
            }
        }
        if (bracketsAmount != 0) {
            return null;
        }
        return clusters;
    }
}
