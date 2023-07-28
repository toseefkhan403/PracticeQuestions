package com.practice.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset2 {
    // with duplicates pattern -> for loop with 1 recursion call
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        // duplicates -> sort first
        Arrays.sort(nums);
        subsetRecur(nums, 0, ds, res);
        return res;
    }

    // Add to res at every step - O(2^n*n), O(2^n*k)
    public void subsetRecur(int[] nums, int index, List<Integer> ds, List<List<Integer>> result) {
        result.add(new ArrayList<>(ds));
        // for loop - iterate thru ahead elements - 1 recursion call
        for (int i = index; i < nums.length; i++) {
            // if there are duplicates, only pick for the first time
            if (i == index || nums[i] != nums[i - 1]) {
                ds.add(nums[i]);
                subsetRecur(nums, i + 1, ds, result);
                ds.remove(ds.size() - 1);
            }
        }
    }

    // brute: use a set - pick and not pick - sort and add to set

    public void subsetRecurI(int[] nums, int index, List<Integer> ds, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> toAdd = new ArrayList<>(ds);
            // can use hashset as well - then turn it into list
            if (!result.contains(toAdd)) {
                result.add(toAdd);
            }

            return;
        }

        ds.add(nums[index]);
        subsetRecurI(nums, index + 1, ds, result);

        // can't do nums[index] as it removes from index not value
        // therefore remove the last added element
        ds.remove(ds.size() - 1);
        subsetRecurI(nums, index + 1, ds, result);
    }

}
