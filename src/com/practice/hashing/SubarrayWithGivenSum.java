package com.practice.hashing;

import java.util.HashMap;

public class SubarrayWithGivenSum {
    public static void main(String[] args) {
        int[] res = subarrayWithGivenSum(new int[] { 10, 15, -5, 15, -10, 5 }, 20);
        System.out.println(res[0] + " " + res[1]);
    }

    // usually for subarrays, the brute force is to find all the subarrays and check
    // the conditions - O(n^2)
    private static int[] subarrayWithGivenSumBrute(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k) {
                return new int[] { i, i };
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (sum + nums[j] == k) {
                    return new int[] { i, j };
                }

                sum += nums[j];
            }
        }

        return new int[] { -1, -1 };
    }

    // store currsum with index in hashmap - in the loop look for currsum-sum -
    // answer is index+1 till i
    private static int[] subarrayWithGivenSum(int[] nums, int k) {
        int start = 0;
        int end = -1;
        int currSum = 0;

        // currsum, index
        HashMap<Integer, Integer> map = new HashMap<>();

        // can do in a single loop
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];

            // checking special condition - answer starts with 0
            if (currSum == k) {
                start = 0;
                end = i;
                break;
            }

            if (map.containsKey(currSum - k)) {
                start = map.get(currSum - k) + 1;
                end = i;
                break;
            }

            map.put(currSum, i);
        }

        return new int[] { start, end };
    }

}
