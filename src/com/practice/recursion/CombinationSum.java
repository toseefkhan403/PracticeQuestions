package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {

    }

    // O(2^t*k), O(k*x)
    // t=target, k=avg length a combination - x=no. of combinations
    // 2^t*k as 2 ways to make target out of every 1[worst case] - k for copying the
    // ds to the result
    // unpredictable space complexity
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combRecur(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    // pick and not pick - can pick one index multiple times
    public void combRecur(int[] candidates, int idx, int target, List<Integer> ds, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(ds));
            return;
        }
        // added more elements than target - or reached the array end
        if (target < 0 || idx == candidates.length)
            return;

        // no for loop required - 2 recursion calls - pick and not pick
        // pick
        ds.add(candidates[idx]);
        // dont move idx - repeated elements are used - reduce target
        combRecur(candidates, idx, target - candidates[idx], ds, result);

        // not pick
        ds.remove(ds.size() - 1);
        // move idx as we are not picking
        combRecur(candidates, idx + 1, target, ds, result);
    }

}
