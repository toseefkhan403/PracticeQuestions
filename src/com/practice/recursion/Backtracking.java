package com.practice.recursion;

public class Backtracking {

    final static int N = 4;

    public static void main(String[] args) {
        // n queen
        int[][] board = new int[N][N];
        nQueen(board, 0);
    }

    // not 2 for loops - jump directly to next row after you've put Q in one row
    private static boolean nQueen(int board[][], int row) {
        // board filled
        if(row == N) {
            for (int[] is : board) {
                for (int is2 : is) {
                    System.out.print(is2);
                }
                System.out.println();
            }
            return true;
        }

        for (int col = 0; col < N; col++) {
            if(isSafe(board, row, col)) {
                board[row][col] = 1;
                if(nQueen(board, row+1))
                    return true;
                board[row][col] = 0;
            }
        }

        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col) {

        for (int i = 0; i < N; i++) {
            if(board[i][col] == 1) return false; // check column

            if(row-1>=0 && col-1>=0 && board[row-1][col-1] == 1) return false; // check upper left diagonal
            if(row-1>=0 && col+1<N && board[row-1][col+1] == 1) return false; // check upper right diagonal

            // no need to check below for rows or diagonals - not filled yet - so safe
        }

        return true;
    }
    
}
