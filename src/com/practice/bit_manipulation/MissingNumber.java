package com.practice.bit_manipulation;

import java.util.Arrays;
import java.util.HashMap;

public class MissingNumber {
    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
    }

    // optimized
    public static int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }

    // sort first - match index
    public static int missingNumberSort(int[] nums) {
        Arrays.sort(nums);   
        for(int i=0; i<nums.length; i++) {
            if(i!=nums[i]) {
                return i;
            }
        }

        return nums.length;
    }

    // fill map till length - remove all - left with answer
    public static int missingNumberMap(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i <= nums.length; i++) {
            map.put(i, i);
        }

        for (int i = 0; i < nums.length; i++) {
            map.remove(nums[i]);
        }

        return map.entrySet().iterator().next().getKey();
    }

    // calc sum of first n natural numbers - subtract from nums sum
    public static int missingNumberSum(int[] nums) {
        int n = nums.length;
        int sum = (n*(n+1))/2;
        int numsSum = 0;

        for(int i=0; i<n; i++) {
            numsSum += nums[i];
        }

        return sum - numsSum;
    }

    // n+1 array - -1 to all the elements same as nums - not -1 is the answer
    public static int missingNumberArr(int[] nums) {
        int[] arr = new int[nums.length+1];
        for (int i = 0; i <= nums.length; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]] = -1;
        }

        for (int i : arr) {
            if(i!=-1)
            return i;
        }

        return -1;
    }
}
