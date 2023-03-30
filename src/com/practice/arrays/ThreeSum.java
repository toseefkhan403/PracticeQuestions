package com.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(threeSumBetter(new int[] { -1, 0, 1, 0 }));
    }

    // find all triplets - sort and add to hashset to avoid repetitions - return set
    // as list - O(n^3logm) - m is the no of triplets
    public static List<List<Integer>> threeSumBrute(int[] nums) {
        HashSet<List<Integer>> result = new HashSet<List<Integer>>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> x = Arrays.asList(nums[i], nums[j], nums[k]);
                        // have to sort to avoid duplicates
                        Collections.sort(x);
                        result.add(x);
                    }
                }
            }
        }

        return new ArrayList<List<Integer>>(result);
    }

    // loops for a and b - map for checking c - O(n^2logm),O(n)
    public static List<List<Integer>> threeSumBetter(int[] nums) {
        HashSet<List<Integer>> result = new HashSet<List<Integer>>();
        HashMap<Integer, Integer> map = new HashMap<>();

        if (nums.length < 3) {
            return new ArrayList<List<Integer>>(result);
        }

        // filling map for c
        for (int x : nums) {
            if (map.get(x) != null) {
                int freq = map.get(x);
                // should be pre fix increment operator not post fix
                map.put(x, ++freq);
            } else {
                map.put(x, 1);
            }
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int freqa = map.get(nums[i]);
            map.put(nums[i], freqa - 1);

            for (int j = i + 1; j < nums.length; j++) {
                // can't use a and b - remove them from the map
                int freqb = map.get(nums[j]);
                map.put(nums[j], freqb - 1);

                int sum = 0 - (nums[i] + nums[j]);
                if ((map.get(sum) != null) && (map.get(sum) > 0)) {
                    List<Integer> x = Arrays.asList(nums[i], nums[j], sum);
                    // have to sort to avoid duplicates
                    Collections.sort(x);
                    result.add(x);
                }

                // add it back - NOT freqb+1 - just put the original value - freqb
                map.put(nums[j], freqb);

            }
            // add it back - NOT freqa+1 - just put the original value - freqa
            map.put(nums[i], freqa);
        }

        return new ArrayList<List<Integer>>(result);
    }

    /*
     * a,b,c triplet - sort the array - pick a - search for rest using 2 pointer
     * approach - O(n^2)
     * pick a - skip to unique a(by comparing with prev value) - search in rest of
     * the array - if triplet found - add to result - low++ and high-- till next
     * uniques
     * if x<sum - just low++ - no need to skip to unique - above while loop will
     * take care of it
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;

        // till the third last element - pick a
        for (int i = 0; i <= nums.length - 3; i++) {
            int sum = 0 - nums[i];
            low = i + 1;
            high = nums.length - 1;

            // skipping to unique a
            if (i == 0 || nums[i - 1] != nums[i]) {

                // search in the rest of the array
                while (low < high) {
                    int x = nums[low] + nums[high];
                    if (x == sum) {
                        // triplet found - add to result - low++ and high-- till next uniques
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        // go till next unique low & high to avoid repetition
                        while (low < high && nums[low + 1] == nums[low])
                            low++;
                        // further optimisation - low<high instead of high>0 - avoid going till the end
                        // of the array in case of all equal elements
                        while (low < high && nums[high - 1] == nums[high])
                            high--;

                        // don't be afraid to do this thinking of edge cases - just do it
                        low++;
                        high--;
                    } else if (x < sum) {
                        // no need to skip to unique element here - above while loop will take care of
                        // it
                        low++;
                    } else {
                        // here as well
                        high--;
                    }
                }
            }
        }

        return result;
    }

    // gives tle
    public static List<List<Integer>> threeSumFirstTry(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> x = twoSum(nums, 0 - nums[i]);
            for (List<Integer> list : x) {
                if (i != list.get(0) && i != list.get(1) && list.get(0) != list.get(1)) {
                    List<Integer> internal = Arrays.asList(nums[i], nums[list.get(0)], nums[list.get(1)]);
                    Collections.sort(internal);
                    if (!res.contains(internal))
                        res.add(internal);
                }
            }
        }

        return res;
    }

    public static List<List<Integer>> twoSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        HashMap<Integer, Integer> res = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (res.get(complement) != null && res.get(complement) != i) {
                List<Integer> internal = Arrays.asList(res.get(complement), i);
                Collections.sort(internal);
                if (!result.contains(internal))
                    result.add(internal);
            }
            res.put(nums[i], i);
        }

        return result;
    }

}
