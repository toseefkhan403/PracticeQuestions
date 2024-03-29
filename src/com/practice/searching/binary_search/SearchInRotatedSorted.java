package com.practice.searching.binary_search;

public class SearchInRotatedSorted {
    public static void main(String[] args) {
        System.out.println(search(new int[] { 3, 1 }, 1));
    }

    // brute: linear search - O(n)
    // optimal: binary search - O(logn)
    // find the sorted part first - search in that if target is b/w it - else move
    // to the other sorted part

    // binary search - extra checks to see if left/right part is sorted or not - if
    // target is between sorted left part - go left - else right - same for sorted
    // right part
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // left part sorted
            // = sign everywhere - let this condition handle the equal case - not the else
            if (nums[low] <= nums[mid]) {
                // between the sorted part
                if (target <= nums[mid] && target >= nums[low]) {
                    // on the left side
                    high = mid - 1;
                } else {
                    // right
                    low = mid + 1;
                }
            }
            // right part sorted
            else {
                // between the sorted part
                if (target >= nums[mid] && target <= nums[high]) {
                    // on the right side
                    low = mid + 1;
                } else {
                    // left
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

}
