package com.practice.stack;

import java.util.Stack;

public class NextGreaterElement2 {
    public static void main(String[] args) {

    }

    // create imaginary array copy in front of the original array - go from right to
    // left
    // algo: push arr elements to stack from the end - keep popping till you find
    // greater element - add it to ans if found - else add -1 - using LIFO property
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];

        // for circular array - the imaginary arr does nothing but fills the stack for
        // for the original arr
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                // inc order downwards - pop till you find greater element
                stack.pop();
            }

            // the values from the imaginary part gets overriden anyways, that's y
            if (i < n) {
                if (!stack.isEmpty()) {
                    res[i] = stack.peek();
                } else {
                    res[i] = -1;
                }
            }

            // O(2n), O(2n) - as 2n elements go in and out of the stack
            stack.push(nums[i % n]);
        }

        return res;
    }

    // search the left side for -1 elements later
    public static int[] nextGreaterElementsBrute(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int el = nums[i];
            boolean found = false;

            while (!stack.isEmpty()) {
                if (stack.peek() > el) {
                    res[i] = stack.peek();
                    found = true;
                    break;
                }

                stack.pop();
            }

            if (!found)
                res[i] = -1;

            stack.push(el);
        }

        // won't work if there is -1 in the array - and it is the nge for another
        // element
        for (int i = 0; i < res.length; i++) {
            if (res[i] == -1) {
                // go left to right and check
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] > nums[i]) {
                        res[i] = nums[j];
                        break;
                    }
                }
            }
        }

        return res;
    }

}
