package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
    }

    // todo do a dry run - try to understand this
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        recurPermute(0, nums, ans);
        return ans;
    }

    private static void recurPermute(int index, int[] nums, List<List<Integer>> ans) {
        if(index == nums.length) {
            // add nums to ans
            List<Integer> ds = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                ds.add(nums[i]);
            }
            ans.add(new ArrayList<>(ds));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            recurPermute(index+1, nums, ans);
            swap(i, index, nums);
        }
    }

    // doesn't work with xor as xor can't swap same numbers - gives 0
    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // uses more space
    public static List<List<Integer>> permuteI(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];

        recurPermuteI(nums,ans,ds,freq);
        return ans;
    }

    private static void recurPermuteI(int[] nums, List<List<Integer>> ans, List<Integer> ds, boolean[] freq){
        if(ds.size() == nums.length) {
            ans.add(new ArrayList<>(ds));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(!freq[i]) {
                freq[i] = true;
                ds.add(nums[i]);
                recurPermuteI(nums, ans, ds, freq);
                ds.remove(ds.size()-1);
                freq[i] = false;

            }
        }
    }



}