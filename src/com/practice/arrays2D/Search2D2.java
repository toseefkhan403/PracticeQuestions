package com.practice.arrays2D;

public class Search2D2 {
    // brute: linear search

    // optimal: start at matrix[0][n-1] - if smaller go down - if larger go left -
    // O(m+n), O(1) - not starting at matrix[0][0] -> illogical
    public boolean searchMatrix(int[][] matrix, int target) {
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

    // better: binary search for each row - O(m*logn), O(1)
    public boolean searchMatrixBetter(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            if (binarySearch(matrix[i], 0, n - 1, target))
                return true;
        }

        return false;
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

}
