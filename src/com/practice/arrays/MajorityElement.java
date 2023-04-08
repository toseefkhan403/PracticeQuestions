package com.practice.arrays;

import java.util.HashMap;
import java.util.Map.Entry;

public class MajorityElement {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2 }));
    }

    private static int majorityElementBrute(int nums[]) {
        int maxElement = nums[0];
        int maxCount = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int freq = 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == num) {
                    freq++;
                }
            }

            if (freq > maxCount) {
                maxCount = freq;
                maxElement = num;
            }
        }

        return maxCount > nums.length / 2 ? maxElement : -1;
    }

    private static int majorityElementMap(int nums[]) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                int co = map.get(nums[i]);
                map.put(nums[i], ++co);
            }
        }

        int maj = nums.length / 2;
        for (Entry<Integer, Integer> i : map.entrySet()) {
            if (i.getValue() > maj) {
                return i.getKey();
            }
        }

        return -1;
    }

    // Moore's Voting Algorithm
    // inc count for element when found - dec when not found - if count=0, change
    // the element and make count 1 - repeat - in the end left with the probable
    // answer
    private static int majorityElement(int[] nums) {
        int ansIndex = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[ansIndex]) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                ansIndex = i;
                count = 1;
            }
        }

        // check if i really gives the majority element
        return nums[ansIndex];
    }
}
