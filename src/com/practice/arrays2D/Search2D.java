package com.practice.arrays2D;

public class Search2D {
    // brute: linear search

    // binary search on whole matrix - imaginary 1D array - O(log(m*n)), O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m * n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            // formula for getting 2D coords from 1D array
            int cell = matrix[mid / n][mid % n];
            if (cell == target) {
                return true;
            }

            if (cell < target) {
                // go right
                start = mid + 1;
            } else {
                // go left
                end = mid - 1;
            }
        }

        return false;
    }

    // linear search down - then binary search across - O(m+logn), O(1)
    public boolean searchMatrixI(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        // looking for row which is just smaller than the target
        int row = 0;
        for (row = 0; row + 1 < m; row++) {
            if (matrix[row + 1][0] > target)
                break;
        }

        // binary search
        return binarySearch(matrix[row], 0, n - 1, target);
    }

    public boolean binarySearch(int[] row, int start, int end, int target) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (row[mid] == target) {
                return true;
            }
            if (row[mid] < target) {
                // go right
                start = mid + 1;
            } else {
                // go left
                end = mid - 1;
            }
        }

        return false;
    }

    // better: same as search2d2
    public boolean searchMatrixBetter(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int col = n - 1;

        // boundary checks
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }

            if (matrix[row][col] < target) {
                // go down
                row++;
            } else {
                // go left
                col--;
            }
        }

        return false;
    }

}
