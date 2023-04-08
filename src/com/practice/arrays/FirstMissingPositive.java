package com.practice.arrays;

import java.util.List;

public class FirstMissingPositive {
    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[] { -1, 4, 2, 1, 9, 10 }));
    }

    // todo do brute and better

    // put elements in correct order - 1 2 3 4 5 - check at the end
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int element = nums[i];

            if (element > 0 && element <= n) {
                // put the element in its correct position
                int correctPosition = element - 1;

                // swap it only if its not at its correct place - else stuck in infinite loop
                if (element != nums[correctPosition]) {
                    int temp = nums[i];
                    nums[i] = nums[correctPosition];
                    nums[correctPosition] = temp;
                    // i-- to make sure new element get at its correct position
                    i--;
                }
            }
        }

        // return first element which is not at its correct position
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // everybody at their correct position - next number is the answer
        return n + 1;
    }

    // put elements in correct order - 1 2 3 4 5 - check at the end
    public static int first_missing_positive(int n, List<Integer> arr) {
        for (int i = 0; i < n; i++) {
            int element = arr.get(i);

            if (element > 0 && element <= n) {
                // put the element in its correct position
                int correctPosition = element - 1;

                // swap it only if its not at its correct place - else stuck in infinite loop
                if (element != arr.get(correctPosition)) {
                    int temp = arr.get(i);
                    arr.set(i, arr.get(correctPosition));
                    arr.set(correctPosition, temp);
                    // i-- to make sure new element get at its correct position
                    i--;
                }
            }
        }

        // return first element which is not at its correct position
        for (int i = 0; i < n; i++) {
            if (arr.get(i) != i + 1) {
                return i + 1;
            }
        }

        // everybody at their correct position - next number is the answer
        return n + 1;
    }
}
