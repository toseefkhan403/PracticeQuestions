package com.practice.bit_manipulation;

import java.util.HashMap;

public class SingleNumberI {
    public static void main(String[] args) {
        int[] nums = new int[]{4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }

    // brute force
    public static int singleNumberBrute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            boolean isFound = false;
            for (int j = 0; j < nums.length; j++) {
                if(x == nums[j] && i!=j) {
                    isFound = true;
                }
            }

            if(!isFound) return x;
        }

        return 0;
    }

    // using O(N) space
    public static int singleNumberMap(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.get(nums[i]) == null) {
                map.put(nums[i],i);
            } else {
                map.remove(nums[i]);
            }
        }

        for (int i : map.keySet()) {
            return i;
        }

        return 0;
    }

    // using linear time and constant space
    public static int singleNumber(int[] nums) {
        int unique = 0;
        for (int i = 0; i < nums.length; i++) {
            unique ^= nums[i];
        }

        return unique;
    }
    
}
