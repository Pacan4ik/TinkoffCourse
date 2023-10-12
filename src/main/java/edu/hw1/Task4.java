package edu.hw1;

final public class Task4 {
    private Task4() {
    }

    public static String fixString(String mixedStr) {
        StringBuilder fixedStr = new StringBuilder();
        for (int i = 0; i < mixedStr.length(); i += 2) {
            if (i == mixedStr.length() - 1) {
                fixedStr.append(mixedStr.charAt(i));
                break;
            }
            fixedStr.append(mixedStr.charAt(i + 1));
            fixedStr.append(mixedStr.charAt(i));
        }
        return fixedStr.toString();
    }
}
