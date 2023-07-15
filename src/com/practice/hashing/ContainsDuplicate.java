package com.practice.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public static void main(String[] args) {
        System.out.println(containsDuplicateSet(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 1 }));
    }

    // search for every element in the rest of the array - O(n^2), O(1)
    public static boolean containsDuplicateBrute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    return true;
                }
            }
        }

        return false;
    }

    // sort - check adjacent elements - O(nlogn + n), O(n[sorting])
    public static boolean containsDuplicateSort(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }

    // map - put value if null - return true if not null(contains duplicate)
    public static boolean containsDuplicateMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                return true;
            }
        }

        return false;
    }

    // set does not contain duplicate elements - put elements into a set - O(n), O(n)
    public static boolean containsDuplicateSet(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i : nums) {
            set.add(i);
        }

        // if nums.length is greater, it means there was a duplicate element
        return nums.length > set.size();
    }

}
