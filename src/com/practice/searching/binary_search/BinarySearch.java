package com.practice.searching.binary_search;

public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[] { 1, 2, 3, 4, 5, 5, 6 }, 4));
    }

    public static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                // right hand side
                low = mid + 1;
            } else if (nums[mid] > target) {
                // left hand side
                high = mid - 1;
            }
        }

        return -1;
    }

}
