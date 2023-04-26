package com.practice.arrays;

public class SquaresofSortedArray {
    public static void main(String[] args) {
        int[] res = sortedSquares(new int[] { -2, -1, 0, 1, 2 });
        for (int i : res) {
            System.out.println(i + " ");
        }
    }

    // 2 pointer - square and check and fill the result array from the back
    public static int[] sortedSquares(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        // can't do in-place
        int[] result = new int[nums.length];
        int insertIndex = nums.length - 1;

        while (low <= high) {
            int leftSq = nums[low] * nums[low];
            int rightSq = nums[high] * nums[high];

            if (leftSq < rightSq) {
                // whichever is bigger, put it at the end of the result array
                result[insertIndex] = rightSq;
                high--;
            } else {
                // takes care of the >= case
                result[insertIndex] = leftSq;
                low++;
            }

            insertIndex--;
        }

        return result;
    }

}
