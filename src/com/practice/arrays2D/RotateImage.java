package com.practice.arrays2D;

import java.util.List;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        rotate(matrix);
        for (int[] is : matrix) {
            for (int is2 : is) {
                System.out.print(is2);
            }
            System.out.println();
        }
    }

    // transpose - then reverse each row - O(2n^2), O(1)
    public static void rotate(int[][] matrix) {
        // transpose the matrix - inplace
        for (int i = 0; i < matrix.length; i++) {
            // start j with i - if with 0, it swaps twice - or check i<j before swapping
            for (int j = i; j < matrix[0].length; j++) {
                // swap ij with ji
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // reverse each row
        for (int i = 0; i < matrix.length; i++) {
            // two pointer for reversing each row
            int low = 0;
            int high = matrix[i].length - 1;
            while (low < high) {
                int temp = matrix[i][low];
                matrix[i][low] = matrix[i][high];
                matrix[i][high] = temp;
                low++;
                high--;
            }
        }
    }

    // brute - new array - fill in rotated manner - copy it to the original array
    public static void rotateBrute(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix[0].length];
        int co = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // filling column wise - right to left - column constant in inner loop
                result[j][co] = matrix[i][j];
            }
            co--;
        }

        // copy to the original array
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = result[i][j];
            }
        }
    }

    // transpose then reverse each row
    public static List<List<Integer>> rotateMatrix(List<List<Integer>> matrix, int n) {
        for (int i = 0; i < matrix.size(); i++) {
            // start j with i - if with 0, it swaps twice
            for (int j = i; j < matrix.get(0).size(); j++) {
                // swap ij with ji
                int temp = matrix.get(i).get(j);
                matrix.get(i).set(j, matrix.get(j).get(i));
                matrix.get(j).set(i, temp);
            }
        }

        // reverse each row
        for (int i = 0; i < matrix.size(); i++) {
            int low = 0;
            int high = matrix.get(i).size() - 1;

            while (low < high) {
                // swap low with high
                int temp = matrix.get(i).get(low);
                matrix.get(i).set(low, matrix.get(i).get(high));
                matrix.get(i).set(high, temp);
                low++;
                high--;
            }
        }

        return matrix;
    }
}
