package com.practice.searching.binary_search;

public class PeakElement {
    
    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }

    // brute force - check with left and right neighbours - return if peak - check corner cases also
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

    // binary search - if mid's left neighbour is greater, then for sure there is peak in the left half(magic) - same for right half
    private static int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        
        // = for single element array
        while(low<=high) {
            int mid = (low+high)/2;

            // if greater than both neighbours -> peak
            // cool conditions - shortcircuit OR - only checks if mid-1 valid && only checks if mid+1 valid - prevents indexoutofbounds
            if((mid==0 || nums[mid-1] < nums[mid]) && (mid == nums.length-1 || nums[mid+1] < nums[mid])) {
                return mid;
            }

            if(mid>0 && nums[mid-1] > nums[mid]) {
                // peak in the left half
                high = mid - 1;
            } else if(mid<nums.length-1 && nums[mid+1] > nums[mid]) {
                // peak in the right half
                low = mid + 1;
            }
        }

        return -1;
    }
    
}
