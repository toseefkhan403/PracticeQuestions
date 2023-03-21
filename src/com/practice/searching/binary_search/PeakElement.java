package com.practice.searching.binary_search;

public class PeakElement {
    
    public static void main(String[] args) {
        
    }

    // brute force
    private static int findPeakElementBrute(int[] nums) {
        int n = nums.length;
        if(n==1) return 0;
        if(n>1 && nums[0] > nums[1])
            return 0;
        if(n>1 && nums[n-1] > nums[n-2])
            return n-1;

        for (int i = 1; i <= n-2; i++) {
            if(nums[i] > nums[i-1] && nums[i] > nums[i+1]){
                return i;
            }
        }

        return -1;
    }

    // TODO
    private static int findPeakElement(int[] nums, int l, int r) {

        int mid = (l+r)/2;
        if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]){
            return mid;
        }

        findPeakElement(nums, l, r);
    }
    
}
