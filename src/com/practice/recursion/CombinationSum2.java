package com.practice.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // need to sort first
        Arrays.sort(candidates);
        combRecur(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    // O(2^n*k[generating subsequences*avg length of a combination]), O(k*x)
    // add to res if t=0 - loop thru ahead - if there are duplicates, only pick for
    // the first time
    public void combRecur(int[] candidates, int idx, int target, List<Integer> ds, List<List<Integer>> result) {
        if (target == 0) {
            // not a constant operation - *k in TC
            result.add(new ArrayList<>(ds));
            return;
        }

        // for loop - iterate thru ahead elements - 1 recursion call
        for (int i = idx; i < candidates.length; i++) {
            // avoid target overflow
            if (candidates[i] > target)
                break;
            // to avoid skipping first element of recursion || skip repeated elements
            if (i == idx || candidates[i - 1] != candidates[i]) {
                // pick
                ds.add(candidates[i]);
                combRecur(candidates, i + 1, target - candidates[i], ds, result);
                ds.remove(ds.size() - 1);
                // no need for this recursion
                // combRecur(candidates, i + 1, target, ds, result);
            }
        }
    }

    // same as comb sum - just use set to avoid duplicates
    // O(2^t*k*log(set size)[log cuz using Set]), O(k*x) - gives tle
    public List<List<Integer>> combinationSumBrute(int[] candidates, int target) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(candidates);
        combRecurBrute(candidates, 0, target, new ArrayList<>(), result);

        // convert set to list of list
        return new ArrayList<>(result);
    }

    // pick and not pick
    public void combRecurBrute(int[] candidates, int idx, int target, List<Integer> ds, Set<List<Integer>> result) {
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
        // move idx - not taking repeated elements
        combRecurBrute(candidates, idx + 1, target - candidates[idx], ds, result);

        // not pick
        ds.remove(ds.size() - 1);
        // move idx
        combRecurBrute(candidates, idx + 1, target, ds, result);
    }

}
