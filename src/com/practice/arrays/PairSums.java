package com.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairSums {
    public static void main(String[] args) {
        largestValue(Arrays.asList(-3, 7, -2, 3, 5, -2));
    }

    // brute force - tle in some cases
    public static long largestValue(List<Integer> arr) {
        // find all subarrays - calc values for all subarrays - return max;
        List<List<Integer>> subarrays = new ArrayList<>();
        long result = Integer.MIN_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            List<Integer> x = new ArrayList<>();
            for (int j = i; j < arr.size(); j++) {
                x.add(arr.get(j));
                subarrays.add(new ArrayList<>(x));
            }
        }

        for (List<Integer> sub : subarrays) {
            // calc pairs and value
            int subarraySum = 0;
            for (int i = 0; i < sub.size() - 1; i++) {
                for (int j = i + 1; j < sub.size(); j++) {
                    subarraySum += sub.get(i) * sub.get(j);
                }
            }
            result = Math.max(subarraySum, result);
        }

        return result;
    }

}
