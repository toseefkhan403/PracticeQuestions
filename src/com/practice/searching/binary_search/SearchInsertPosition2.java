package com.practice.searching.binary_search;

import java.util.List;

public class SearchInsertPosition2 {
    public static void main(String[] args) {
        System.out.println(searchInsertOptimized(new int[]{1,3,5,6}, 2));
    }

    public static int search_pos(List<Integer> arr, int target) {
        int pos = 0;
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i) > target) {
                return pos;
            } else if(arr.get(i) == target) {
                return i;
            } else if(arr.get(i) < target) {
                pos++;
            }
        }

        return pos;
    }

    // brute force - go through the array - return if equal - inc pos if less - return pos if greater
    public static int searchInsert(int[] nums, int target) {
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > target) {
                return pos;
            } else if(nums[i] == target) {
                return i;
            } else if(nums[i] < target) {
                pos++;
            }
        }

        return pos;
    }

    // binary search approach - halving search space each iteration
    public static int searchInsertOptimized(int[] nums, int target) {
        int l = 0;
        int r = nums.length -1;
        int mid = 0;

        while(l<=r) {
            mid = (l+r)/2;
            if(nums[mid] < target) {
                // move to the right
                l = mid+1;
            } else if(nums[mid] > target) {
                // move to the left
                r = mid-1;
            } else if(nums[mid] == target) {
                return mid;
            }
        }

        // return left not mid - as left one gets in position magically - mid stays behind
        return l;
    }
    
}
