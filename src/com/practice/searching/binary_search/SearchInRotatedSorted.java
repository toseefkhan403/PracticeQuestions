package com.practice.searching.binary_search;

public class SearchInRotatedSorted {

    public static void main(String[] args) {
        System.out.println(search(new int[]{3,1}, 1));
    }

    // binary search - extra checks to see if left/right part is sorted or not - if target is between sorted left part - go left - else right - same for sorted right part
    private static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        
        while(low<=high) {
            int mid = (low+high)/2;

            if(nums[mid] == target) {
                return mid;
            }

            // left part sorted
            //= is important! - let this condition handle the equal case - not the else
            if(nums[low] <= nums[mid]) {
                // between the sorted part
                if(target<nums[mid] && target>=nums[low]) {
                    // on the left side
                    high = mid-1;
                } else {
                    // right
                    low = mid+1;
                }
            }
            // right part sorted
            else {
                // between the sorted part
                if(target>nums[mid] && target<=nums[high]) {
                    // on the right side
                    low = mid+1;
                } else {
                    // left
                    high = mid-1;
                }
            }
        }

        return -1;
    }
    
}
