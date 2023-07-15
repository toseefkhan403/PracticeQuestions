package com.practice.arrays;

import java.util.HashMap;
import java.util.Map;

public class SortColors {
    public static void main(String[] args) {
        int[] nums = { 2, 0, 2, 1, 1, 0 };
        sortColors(nums);

        for (int j : nums) {
            System.out.print(j);
        }
    }

    // brute - sort it - O(nlogn), O(n)
    // better - count sort - O(2n), O(1)

    // single pass - Dutch national flag algorithm - use striver's diagram to remember
    // 0 to low should be 0s and high to n-1 should be 2s
    // 3 steps - use mid to traverse - if 0 swap with low - if 2 swap with high(but
    // not mid++ here) - if 1 only mid++ - O(n), O(1)
    public static void sortColors(int[] nums) {
        // 3 pointers with swap
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(low, mid, nums);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2) {
                swap(high, mid, nums);
                high--;
            }
        }
    }

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 2 pass - counting sort
    // count the number of 0 and 1 - fill the array with it
    public static void sortColors2(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count0++;
            } else if (nums[i] == 1) {
                count1++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (count0 > 0) {
                nums[i] = 0;
                count0--;
            } else if (count1 > 0) {
                nums[i] = 1;
                count1--;
            } else {
                nums[i] = 2;
            }
        }
    }

    // make freq map - fill array using map values - worse than previous solution
    // because of the space complexity
    public static void sortColorsSpace(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // frequency array
            int element = nums[i];
            if (map.containsKey(element)) {
                int freq = map.get(element);
                map.put(element, freq + 1);
            } else {
                map.put(element, 1);
            }
        }

        int count0 = map.getOrDefault(0, 0);
        int count1 = map.getOrDefault(1, 0);

        for (int i = 0; i < nums.length; i++) {
            if (count0 > 0) {
                nums[i] = 0;
                count0--;
            } else if (count1 > 0) {
                nums[i] = 1;
                count1--;
            } else {
                nums[i] = 2;
            }
        }
    }

}
