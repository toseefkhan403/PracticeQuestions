package com.practice.stack;

import java.util.Stack;

public class LargestRectangleHistogram {
    public static void main(String[] args) {

    }

    // make the left and right search for smaller element easier by using stack -
    // then apply formula
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        // add indices to arr - easier for calc ahead
        int[] previousSmaller = findPreviousSmaller(heights);
        // can reuse the same stack to save space
        int[] nextSmaller = findNextSmaller(heights);

        // apply formula for each index
        for (int i = 0; i < heights.length; i++) {
            int area = (nextSmaller[i] - previousSmaller[i] - 1) * heights[i];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static int[] findNextSmaller(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();

            if (stack.isEmpty()) {
                // IMPORTANT - for easier calc later
                res[i] = nums.length;
            } else {
                res[i] = stack.peek();
            }

            // push the index in the stack
            stack.push(i);
        }

        return res;
    }

    public static int[] findPreviousSmaller(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i])
                stack.pop();

            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = stack.peek();
            }

            // push the index in the stack
            stack.push(i);
        }

        return res;
    }

    // for each index, go left and right till you find smaller element - calc area
    // via formula and check max area
    // O(n^2) - gives tle
    public static int largestRectangleAreaBrute(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;
            while (left >= 0 && heights[left] >= heights[i])
                left--;

            while (right < heights.length && heights[right] >= heights[i])
                right++;

            int area = (right - left - 1) * heights[i];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

}
