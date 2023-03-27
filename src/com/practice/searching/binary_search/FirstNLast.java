package com.practice.searching.binary_search;

public class FirstNLast {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(searchRangeOpti(nums,8)[0]);
    }

    // find through binarysearch - then move left and right - O(N)
    public static int[] searchRange(int[] nums, int target) {

        int pos = BinarySearch.binarySearch(nums, target);
        if(pos!=-1) {
            // go left and right
            int left = pos;
            int right = pos;

            // do left-1 in the while loop only - then do left-- - not in an additional if condition
            while(left>0 && nums[left-1] == target) {
                left--;
            }
            // do right+1 in the while loop only - then do right++ - not in an additional if condition
            while(right<nums.length-1 && nums[right+1] == target) {
                right++;
            }
            
            // dont do left+1 or right-1 - gives problem in edge cases
            return new int[]{left,right};
        }

        return new int[]{-1,-1};
    }

    // search for first and last occurrence separately using modified binary search - O(logN)+O(logN)->O(logN)
    public static int[] searchRangeOpti(int[] nums, int target) {
        int first = firstOccurrence(nums, 0, nums.length-1, target);
        int last = lastOccurrence(nums, 0, nums.length-1, target);

        return new int[]{first,last};
    }

    // modified binary search
    private static int firstOccurrence(int[] nums, int low, int high, int target) {
        if(low>high) return -1;

        int mid = (low+high)/2;

        // modification : check with the left element to make sure it's the first occurrence
        // shortcircuit OR for the edge case - preventing indexoutofbounds
        if(nums[mid] == target && (mid == 0 || nums[mid-1] != target)) {
            return mid;
        }else if(nums[mid] < target) {
            // right side
            return firstOccurrence(nums, mid+1, high, target);
        } else {
            // left side
            // pushing it to the left as the default case - is >= effectively
            return firstOccurrence(nums, low, mid-1, target);
        }
    }

    // modified binary search
    private static int lastOccurrence(int[] nums, int low, int high, int target) {
        if(low>high) return -1;

        int mid = (low+high)/2;

        // modification : check with the right element to make sure it's the last occurrence
        // shortcircuit OR for the edge case - preventing indexoutofbounds
        if(nums[mid] == target && (mid == nums.length-1 || nums[mid+1] != target)) {
            return mid;
        }else if(nums[mid] > target) {
            // left side
            return lastOccurrence(nums, low, mid-1, target);
        } else {
            // right side
            // pushing it to the right as the default case - is <= effectively
            return lastOccurrence(nums, mid+1, high, target);
        }
    }
    
}
