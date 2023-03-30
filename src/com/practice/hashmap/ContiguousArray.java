package com.practice.hashmap;

import java.util.HashMap;

public class ContiguousArray {

    public static void main(String[] args) {
        System.out.println(findMaxLength(new int[]{0,0,1,0,0,0,1,1}));
    }

    // find all subarrays with equal no of 0 and 1
    public static int findMaxLengthBrute(int[] nums) {
        int count0 = 0; int count1 = 0; int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            count0 = 0; count1 = 0;
            for (int j = i; j < nums.length; j++) {
                if(nums[j]==0) {
                    count0++;
                } else {
                    count1++;
                }
                
                if(count0 == count1) {
                    maxLength = Math.max(maxLength, j-i+1);
                }
            }
        }

        return maxLength;
    }

    // does not work - lots of repeating elements in currsum - messes up hashmap -> ONE ELSE SAVES THE DAY!
    // replace 0 with -1 - longest subarray with sum 0 - can't use sliding window soln as it cannot deal with negative nos
    // similar to subarraywithgivensum - O(N), O(N)
    public static int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int currSum = 0; int maxLength = 0;
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            currSum+=nums[i];

            if(currSum==0) {
                maxLength = Math.max(maxLength, i+1);
            }

            if(map.containsKey(currSum)) {
                maxLength = Math.max(maxLength, i-map.get(currSum));
            } 
            // only diff - this else statement - avoids repetition - makes ALL the difference
            else {
                map.put(currSum, i);
            }
        }

        return maxLength;
    }
}
