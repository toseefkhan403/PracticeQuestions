package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public static List<List<String>> solveNQueensOpti(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        // hash arrays instead of isSafe method
        int[] leftSafe = new int[n];
        // size for diagonal hash array = 2n-1
        int[] upperLeftSafe = new int[2 * n - 1];
        int[] lowerLeftSafe = new int[2 * n - 1];

        // prefill the board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        nQueensRecurOpti(0, board, result, leftSafe, upperLeftSafe, lowerLeftSafe);
        return result;
    }

    // uses hasharrays for isSafe rather than traversing O(3n)
    private static void nQueensRecurOpti(int col, char[][] board, List<List<String>> result, int[] leftSafe,
            int[] upperLeftSafe, int[] lowerLeftSafe) {
        // board filled
        if (col == board.length) {
            result.add(constructList(board));
            return;
        }

        // fill each row[same col] one by one
        for (int row = 0; row < board.length; row++) {
            if (leftSafe[row] == 0 && lowerLeftSafe[row + col] == 0
                    && upperLeftSafe[board.length - 1 + col - row] == 0) {
                board[row][col] = 'Q';
                // row here, not col
                leftSafe[row] = 1;
                // row + col for lower diagonal
                lowerLeftSafe[row + col] = 1;
                // n-1+col-row - this formula for upper diagonal - basically lower array upside
                // down
                upperLeftSafe[board.length - 1 + col - row] = 1;
                nQueensRecurOpti(col + 1, board, result, leftSafe, upperLeftSafe, lowerLeftSafe);
                // backtracking step - reset posn when you come back from recursion
                // so we can check for other possibilities
                board[row][col] = '.';
                leftSafe[row] = 0;
                lowerLeftSafe[row + col] = 0;
                upperLeftSafe[board.length - 1 + col - row] = 0;
            }
        }
    }

    // unoptimized approach //

    // O(N! * N), O(N^2)
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        // prefill the board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        nQueensRecur(0, board, result);
        return result;
    }

    // fill each row one by one - carry on with col+1 recursion - get all possibile
    // boards
    private static void nQueensRecur(int col, char[][] board, List<List<String>> result) {
        // board filled
        if (col == board.length) {
            result.add(constructList(board));
            return;
        }

        // fill each row[same col] one by one
        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                nQueensRecur(col + 1, board, result);
                // backtracking step - reset posn when you come back from recursion
                // so we can check for other possibilities
                board[row][col] = '.';
            }
        }
    }

    // get list from char board
    private static List<String> constructList(char[][] board) {
        List<String> res = new ArrayList<>();

        for (char[] arr : board) {
            res.add(new String(arr));
        }

        return res;
    }

    // check left, upper left and lower left - others not filled yet so safe
    // O(3n) - can be optimized using hashing
    private static boolean isSafe(char[][] board, int row, int col) {
        int origRow = row;
        int origCol = col;

        while (col >= 0) {
            if (board[row][col] == 'Q')
                return false;
            col--;
        }

        col = origCol;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q')
                return false;
            col--;
            row--;
        }

        row = origRow;
        col = origCol;
        while (row < board.length && col >= 0) {
            if (board[row][col] == 'Q')
                return false;
            col--;
            row++;
        }

        return true;
    }

}