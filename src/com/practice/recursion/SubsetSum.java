package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

// can't apply dp to these recursion problems as there are no Overlapping Subproblems
public class SubsetSum {
    // brute: calc all subsets' sum - sort it

    // pick/not pick - calc sum of all subset - O(2^n), O(2^n)[result array]
    public static List<Integer> subsetSums(List<Integer> arr, int N) {
        List<Integer> result = new ArrayList<>();
        subsetSumRecur(arr, 0, 0, result);
        // dont need to sort it - do notPick call first - then pick call
        // Collections.sort(result);
        return result;
    }

    // dont need ds - can keep a sum variable
    private static void subsetSumRecur(List<Integer> arr, int ind, int sum, List<Integer> res) {
        if (ind == arr.size()) {
            // add subset's sum to result
            res.add(sum);
            // no point to reset sum - not passed by reference
            return;
        }

        // not pick
        subsetSumRecur(arr, ind + 1, sum, res);
        // pick
        subsetSumRecur(arr, ind + 1, arr.get(ind) + sum, res);
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
