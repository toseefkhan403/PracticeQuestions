package com.practice.arrays;

public class TwoSum2 {
    public static void main(String[] args) {
        int[] res = twoSum(new int[] { 1, 2, 3, 4, 6 }, 9);
        System.out.println(res[0] + "," + res[1]);
    }

    // brute - find complement in the rest of the array - O(n^2)
    // better - find complement in the rest of the array using binary search - O(n*logn)
    // optimised - 2 pointer - return if sum== target - go right if sum<target - else left - O(n)
    public static int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;

        // not = as can't use same element
        while (low < high) {
            int sum = numbers[low] + numbers[high];

            if (sum == target) {
                // +1 as ans should be 1-indexed
                return new int[] { low + 1, high + 1 };
            } else if (sum < target) {
                // need to increase sum - go right
                low++;
            } else {
                // dec sum - go left
                high--;
            }
        }

        return new int[] { -1, -1 };
    }

}
