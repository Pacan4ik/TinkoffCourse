package edu.hw1;

public class Task8 {

    private static final int[][] moves = new int[][] {
        {-1, -2},
        {1, -2},
        {2, -1},
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, 1},
        {-2, -1}
    };

    public static boolean knightBoardCapture(int[][] desk) {
        if (desk == null || desk.length != 8) {
            return false;
        }
        for (int i = 0; i < desk.length; i++) {
            if (desk[i] == null || desk[i].length != 8) {
                return false;
            }
        }
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                if (desk[i][j] == 0) {
                    continue;
                }
                for (int dir = 0, ii, jj; dir < 8; dir++) {
                    ii = i + moves[dir][0];
                    jj = j + moves[dir][1];
                    if (!((ii > desk.length - 1 || ii < 0) || (jj > desk[j].length - 1 || jj < 0)) &&
                        desk[ii][jj] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
