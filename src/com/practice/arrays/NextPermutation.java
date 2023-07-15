package com.practice.arrays;

public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = { 1, 5, 8, 4, 7, 6, 5, 3, 1 };
        nextPermutation(nums);
        for (int i : nums) {
            System.out.println(i + " ");
        }
    }

    // brute: generate all perms - sort em - give next perm - O(n!*n) - too high

    // Remember the algorithm!
    // find partition index from the end - if not found -> last perm - just reverse
    // else if found - swap with the just bigger number and reverse the remaining
    // part - O(2n), O(1)
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int pIndex = n - 2; // to avoid doing pIndex-- at the end
        while (pIndex >= 0 && nums[pIndex] >= nums[pIndex + 1])
            pIndex--;

        // if pIndex = -1 - just reverse the array
        if (pIndex >= 0) {
            int swapIndex = n - 1;
            // looking for just bigger number - will be the first > number from the end as
            // it is in descending order
            while (nums[pIndex] >= nums[swapIndex])
                swapIndex--;
            swap(nums, swapIndex, pIndex);
        }

        // need to reverse because right now the part after pIndex is in descending
        // order(the biggest perm after 0 to pIndex) - hence for the next permutation
        // need to reverse it into ascending order
        // this also takes care of pIndex = -1 part - thus in lexicographical order
        reverse(nums, pIndex + 1, n - 1);
    }

    public static void nextPermutationI(int[] nums) {
        int n = nums.length;
        int pIndex = n - 1;
        // find partition index from the end
        // if found proceed - else the permutation is the last one - just reverse it
        while (pIndex > 0 && nums[pIndex - 1] >= nums[pIndex]) {
            pIndex--;
        }
        // not recommended to do this
        pIndex--;

        if (pIndex >= 0) {
            // search for the just bigger number at the end of the array
            int swapIndex = n - 1;
            while (swapIndex > pIndex && nums[pIndex] >= nums[swapIndex]) {
                swapIndex--;
            }
            swap(nums, pIndex, swapIndex);
        }

        // reverse the rest of the array - takes care of pIndex = -1 case as well
        reverse(nums, pIndex + 1, n - 1);
    }

    private static void reverse(int[] nums, int low, int high) {
        while (low < high) {
            swap(nums, low, high);
            low++;
            high--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
