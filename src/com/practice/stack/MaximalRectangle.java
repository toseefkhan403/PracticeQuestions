package com.practice.stack;

import java.util.Stack;

public class MaximalRectangle {
    public static void main(String[] args) {
        
    }

    // go row by row - keep adding row's value to prev row - find maxRect for row - find maxRect for matrix
    public static int maximalRectangle(char[][] matrix) {
        int[] row = new int[matrix[0].length];
        int maxArea = 0;

        // reuse the space later
        int[] ps = new int[row.length];
        int[] ns = new int[row.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // no need to change char matrix to int matrix - binary elements so can add 1 or 0
                if(matrix[i][j] == '0') {
                    // not += as 0 breaks the rectangle - reset it to 0
                    row[j] = 0;
                } else {
                    row[j] += 1;
                }
            }

            int maxRectRow = findMaxRect(row, ps, ns, stack);
            maxArea = Math.max(maxArea, maxRectRow);
        }

        return maxArea;
    }

    private static int findMaxRect(int[] row, int[] ps, int[] ns, Stack<Integer> stack) {
        int maxArea = 0;
        // clear any elements from previous iteration
        stack.clear();

        // fill previous smaller array
        for (int i = 0; i < row.length; i++) {
            while(!stack.isEmpty() && row[stack.peek()] >= row[i])
                stack.pop();

            if(stack.isEmpty()) {
                ps[i] = -1;
            } else {
                ps[i] = stack.peek();
            }

            stack.push(i);
        }

        // clear the stack and reuse
        stack.clear();

        // fill next smaller array
        for (int i = row.length-1; i >= 0; i--) {
            while(!stack.isEmpty() && row[stack.peek()] >= row[i])
                stack.pop();

            if(stack.isEmpty()) {
                // for easier calc later
                ns[i] = row.length;
            } else {
                ns[i] = stack.peek();
            }

            stack.push(i);
        }

        // apply formula
        for (int i = 0; i < row.length; i++) {
            int area = (ns[i] - ps[i] - 1) * row[i];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
    
}
