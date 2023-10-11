package edu.hw1;

final public class Task8 {
    private Task8() {
    }

    private static final int[][] MOVES = new int[][] {
        {-1, -2},
        {1, -2},
        {2, -1},
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, 1},
        {-2, -1}
    };
    private static final int DESKSIZE = 8;

    public static boolean knightBoardCapture(int[][] desk) {
        if (desk == null || desk.length != DESKSIZE) {
            return false;
        }
        for (int[] rows : desk) {
            if (rows == null || rows.length != DESKSIZE) {
                return false;
            }
        }
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                if (desk[i][j] == 0) {
                    continue;
                }
                for (int dir = 0, ii, jj; dir < DESKSIZE; dir++) {
                    ii = i + MOVES[dir][0];
                    jj = j + MOVES[dir][1];
                    if (!((ii > desk.length - 1 || ii < 0) || (jj > desk[j].length - 1 || jj < 0))
                        && desk[ii][jj] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
