package com.practice.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MajorityElement2 {
    // optimal: ans length cannot exceed 2 - apply Moore's Voting Algorithm but
    // for 2 elements - O(2n), O(1)
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;

        // no need to check if these are different - just start everything from 0
        int number1 = nums[0];
        int number2 = nums[0];

        int count1 = 0;
        int count2 = 0;

        // if-else ladder
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == number1) {
                count1++;
            } else if (nums[i] == number2) {
                count2++;
            } else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // check if number1 and number2 are really maj guys
        int maj = nums.length / 3;
        count1 = 0;
        count2 = 0;

        // count freq first - add to res LATER - or else adds value multiple times
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == number1) {
                count1++;
            }
            // IMPORTANT! - this else if ensures that number1 and 2 are different in the res
            else if (nums[i] == number2) {
                count2++;
            }
        }

        if (count1 > maj) {
            res.add(number1);
        }
        if (count2 > maj) {
            res.add(number2);
        }

        return res;
    }

    // better: hashing - freq map - add to res if majority element - O(2n), O(n)
    public List<Integer> majorityElementBetter(int[] nums) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1 + map.getOrDefault(nums[i], 0));
        }

        int maj = nums.length / 3;

        for (int key : map.keySet()) {
            if (map.get(key) > maj)
                res.add(key);
        }

        return res;
    }

    // brute: count each element - if its freq is > n/3 - add to ans list
    // O(n^2), O(1)
    public List<Integer> majorityElementBrute(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        int majority = n / 3;

        for (int i = 0; i < n; i++) {
            int el = nums[i];
            // if el is already in the ans list, skip it
            if (res.contains(el)) {
                continue;
            }

            // single el edge case - start from 0 not 1
            int count = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == el) {
                    count++;
                }

                if (count > majority) {
                    res.add(el);
                    break;
                }
            }
        }

        return res;
    }

}
