package com.practice.recursion;

public class SudokuSolver {
    public static char[][] solveSudoku(char[][] board) {
        solveSudokuRecur(board);
        return board;
    }

    // O(9^(n^2))[9 possible numbers in each cell], O(n^2)[board]
    public static boolean solveSudokuRecur(char[][] board) {
        // IMPORTANT - cant do i=row, j=col - have to go through the entire board each
        // time
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    // try to put 1-9
                    for (char c = '1'; c <= '9'; c++) {
                        if (isSafe(board, i, j, c)) {
                            board[i][j] = c;
                            if (solveSudokuRecur(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    }

                    // couldnt put 1-9 - cant be solved
                    return false;
                }
            }
        }

        // board completely filled - solved
        return true;
    }

    private static boolean isSafe(char[][] board, int row, int col, char c) {
        // single loop
        for (int i = 0; i < board.length; i++) {
            // check row
            if (board[row][i] == c)
                return false;

            // check col
            if (board[i][col] == c)
                return false;

            // check 3*3 - remember the formula
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
                return false;
        }

        return true;
    }

}
