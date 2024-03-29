package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        System.out.println(permute(new int[] { 1, 2, 3 }));
    }

    // O(n![no of perms]*n[for looping thru & adding nums to ans]), O(n[aux space])
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        recurPermute(0, nums, ans);
        return ans;
    }

    // keep index - swap with AHEAD elements - do recursion with index+1 - unswap
    // when you come back
    private static void recurPermute(int index, int[] nums, List<List<Integer>> ans) {
        // if index reaches nums end - perm found
        // can be nums.length-1 also
        if (index == nums.length) {
            // add nums to ans
            List<Integer> ds = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                ds.add(nums[i]);
            }
            ans.add(ds);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            recurPermute(index + 1, nums, ans);
            swap(i, index, nums);
        }
    }

    // doesn't work with xor as xor can't swap same numbers - gives 0
    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // uses more space - O(n![no of perms]*n[for looping thru]), O(2*n[freq,ds])
    public static List<List<Integer>> permuteI(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];

        recurPermuteI(nums, ans, ds, freq);
        return ans;
    }

    // pick and not pick - if picked, mark it and add to ds - go ahead with the
    // recursion - when returning, unmark it and remove from ds
    private static void recurPermuteI(int[] nums, List<List<Integer>> ans, List<Integer> ds, boolean[] freq) {
        if (ds.size() == nums.length) {
            // permutation found
            ans.add(new ArrayList<>(ds));
            return;
        }

        // can pick any element - looping thru
        for (int i = 0; i < nums.length; i++) {
            if (!freq[i]) {
                freq[i] = true;
                ds.add(nums[i]);
                recurPermuteI(nums, ans, ds, freq);
                ds.remove(ds.size() - 1);
                freq[i] = false;
            }
        }
    }

}
