package com.practice.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {

    }

    // brute: calc all subsets' sum - sort it

    // pick/not pick - calc sum of all subset - sort em - O(2^n + nlogn) = O(2^n),
    // O(2^n)[result array]
    public static List<Integer> subsetSums(List<Integer> arr, int N) {
        List<Integer> result = new ArrayList<>();
        subsetSumRecur(arr, 0, 0, result);
        Collections.sort(result);
        return result;
    }

    // dont need ds - can keep a sum variable
    private static void subsetSumRecur(List<Integer> nums, int index, int sum, List<Integer> result) {
        if (index == nums.size()) {
            // add subset's sum to result
            result.add(sum);
            // no need to reset sum
            return;
        }

        sum += nums.get(index);
        // can add in this line as well
        subsetSumRecur(nums, index + 1, sum, result);
        sum -= nums.get(index);
        subsetSumRecur(nums, index + 1, sum, result);
    }

    private static void subsetSumRecurI(List<Integer> nums, int index, List<Integer> ds, List<Integer> result) {
        if (index == nums.size()) {
            // add subset's sum to result
            int sum = 0;
            for (Integer i : ds) {
                sum += i;
            }
            result.add(sum);

            return;
        }

        ds.add(nums.get(index));
        subsetSumRecurI(nums, index + 1, ds, result);
        ds.remove(ds.size() - 1);
        subsetSumRecurI(nums, index + 1, ds, result);
    }

}
