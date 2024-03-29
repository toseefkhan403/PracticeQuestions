package com.practice.searching.binary_search;

public class MinInRotatedSortedArray {
    // brute: linear search - O(n)
    // optimal: binary search - the ans may or may not be in the sorted part - so
    // keep the min from the sorted part and discard it - then go to the unsorted
    // part - O(logn), O(1)
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int res = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;

            // left part sorted
            if (nums[low] <= nums[mid]) {
                res = Math.min(res, nums[low]);
                // discard this part - already taken ans from it
                low = mid + 1;
            } else {
                // right part sorted - mid is the min element in this part
                res = Math.min(res, nums[mid]);
                // discard this part - already taken ans from it
                high = mid - 1;
            }
        }

        return res;
    }

}
