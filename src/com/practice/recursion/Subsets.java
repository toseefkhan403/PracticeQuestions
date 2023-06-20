package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    // without duplicates pattern -> 2 recursion calls
    // O(2^n), O(2^n)[result array]
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        subsetRecur(nums, 0, ds, res);
        return res;
    }

    public void subsetRecur(int[] nums, int index, List<Integer> ds, List<List<Integer>> result) {
        // add to res when index reaches nums length
        if (index == nums.length) {
            result.add(new ArrayList<>(ds));
            return;
        }

        // no for loop required - 2 recursion calls - pick and not pick
        ds.add(nums[index]);
        subsetRecur(nums, index + 1, ds, result);
        // can't do nums[index] as it removes from index not value
        // therefore remove the last added element
        ds.remove(ds.size() - 1);
        subsetRecur(nums, index + 1, ds, result);
    }

}
