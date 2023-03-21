package com.practice.arrays;

import java.util.List;

public class RemoveDuplicates {

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }

    // 2 indices - 1st for filling the array(insertIndex) - 2nd for checking duplicates(i)
    // start with 1 - check with the previous element - if not equal, copy element left and inc insertIndex by 1
    // return insertIndex in the end = first unique elements length
    public static int removeDuplicates(int[] nums) {
        // fills the starting of the array
        int insertIndex = 1;

        // no need for nums.length-1 - the loop will run till nums end only - no index out of bounds
        for (int i = 1; i < nums.length; i++) {
            if(nums[i-1] != nums[i]) {
                nums[insertIndex++] = nums[i];
            }                      
        }

        // only the start of the array matters - end does not

        // length of the output array is 1 if there are no duplicates - increases every time sth is inserted
        // first unique elements length
        return insertIndex;
    }

     public static int remove_dupli(List<Integer> arr) {
        // Write your code here
        int insertIndex = 1;
        for(int i=1; i<arr.size(); i++) {
            if(arr.get(i) != arr.get(i-1)) {
                arr.set(insertIndex++, arr.get(i));
            }
        }

        return insertIndex;
    }

}
