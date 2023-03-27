package com.practice.searching.binary_search;

public class SingleElementSorted {

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
    }

    // binary search - check neighbours of mid - check if mid is even or odd - even = same side - odd = opposite side
    public static int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length-1;

        // not = as it doesn't work for single element
        while(low<high) {
            int mid = (low+high)/2;

            // check left
            if(nums[mid-1] == nums[mid]) {
                // which way to go? if mid is even - go where the element is found - go left
                if((mid&1) == 0) {
                    high = mid-2;
                } else {
                    // if mid is odd - go the opposite of where the element is found - go right
                    low = mid+1;
                }
            } 
            // check right
            else if(nums[mid+1] == nums[mid]){
                // if mid is even - go where the element is found - go right
                if((mid&1) == 0) {
                    low = mid+2;
                } else {
                    // if mid is odd - go the opposite of where the element is found - go left
                    high = mid-1;
                }
            } else {
                // single element
                return nums[mid];
            }

        }

        // low = high right now - can do (low+high)/2 as well
        return nums[low];
    }
    
}
