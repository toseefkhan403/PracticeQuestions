package com.practice.recursion;

public class WordSearch {
    public static void main(String[] args) {

    }

    // find the first letter - go in four directions from there(4 recursion calls) -
    // continue if string matches
    // return true if idx reaches string length - return false if out of bounds or
    // doesnt match - O(m*n*4^k),O(k) - k is the length of the word
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (existRecur(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean existRecur(char[][] board, int row, int col, String word, int idx) {
        if (idx == word.length()) {
            return true;
        }

        // out of bounds or doesnt match
        if (row == -1 || col == -1 || row >= board.length || col >= board[0].length) {
            return false;
        }

        if (word.charAt(idx) != board[row][col]) {
            return false;
        }

        char c = board[row][col];
        // mark visited cell
        board[row][col] = '#';
        if (existRecur(board, row, col + 1, word, idx + 1) ||
                existRecur(board, row + 1, col, word, idx + 1) ||
                existRecur(board, row, col - 1, word, idx + 1) ||
                existRecur(board, row - 1, col, word, idx + 1)) {
            return true;
        } else {
            // unmark and backtrack if doesnt match
            board[row][col] = c;
            return false;
        }

    }

}
