package edu.hw1;

final public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1 == null || a2 == null || a1.length == 0 || a2.length == 0) {
            return false;
        }
        int mina1 = a1[0];
        int maxa1 = a1[0];
        for (int i = 1; i < a1.length; i++) {
            if (a1[i] < mina1) {
                mina1 = a1[i];
                continue;
            }
            if (a1[i] > maxa1) {
                maxa1 = a1[i];
            }
        }
        int mina2 = a2[0];
        int maxa2 = a2[0];
        for (int i = 1; i < a2.length; i++) {
            if (a2[i] < mina2) {
                mina2 = a2[i];
                continue;
            }
            if (a2[i] > maxa2) {
                maxa2 = a2[i];
            }
        }
        return (mina1 > mina2) && (maxa1 < maxa2);
    }
}
