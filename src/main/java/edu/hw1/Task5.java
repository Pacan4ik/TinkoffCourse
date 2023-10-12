package edu.hw1;

final public class Task5 {
    private Task5() {
    }

    private static final int MIN_TWO_DIGITS_NUMBER = 10;

    private static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindromeDescendant(String s) {
        if (s.length() <= 1) {
            return false;
        }
        if (isPalindrome(s)) {
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i += 2) {
            stringBuilder.append(Character.getNumericValue(s.charAt(i))
                + Character.getNumericValue(s.charAt(i + 1)));
        }
        return isPalindromeDescendant(stringBuilder.toString());
    }

    public static boolean isPalindromeDescendant(int num) {
        int possiblePal = num > 0 ? num : -num;
        if (possiblePal < MIN_TWO_DIGITS_NUMBER) {
            return false;
        }
        String palindromeStr = Integer.toString(possiblePal);
        return isPalindromeDescendant(palindromeStr);
    }
}
