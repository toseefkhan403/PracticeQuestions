package com.practice.arrays2D;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

    // implementation based - 4 pointers - RBLT - O(n*m),O(n*m)
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int r = matrix.length;
        int c = matrix[0].length;
        int left = 0;
        int right = c - 1;
        int top = 0;
        int bottom = r - 1;

        // && not ||
        while (left <= right && top <= bottom) {
            // go right - top constant
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            // inc or dec whatever is constant in the prev loop
            top++;
            // go bottom - right constant
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;

            // for single right to left case - prevents repeating - already checking for
            // left in the loop - so check for top and bottom
            if (top <= bottom) {
                // go left - bottom constant
                for (int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // for single bottom to top case - prevents repeating - already checking for top
            // in the loop - so check for left and right
            if (left <= right) {
                // go top - left constant
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }

        return res;
    }

}
