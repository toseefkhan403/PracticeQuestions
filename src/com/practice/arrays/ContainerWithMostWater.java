package com.practice.arrays;

class ContainerWithMostWater {
    // need to find left longest and right longest - 2 ptrs - calc water and keep
    // max - move the lower guy ONLY - O(n), O(1)
    public int maxAreaI(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int res = 0;
        int water = 0;

        while (low < high) {
            if (nums[low] < nums[high]) {
                // lower bar * width
                water = nums[low] * (high - low);
                low++;
            } else {
                water = nums[high] * (high - low);
                high--;
            }

            res = Math.max(res, water);
        }

        return res;
    }

    // brute: for every element, calc water with ahead elements - keep max - O(n^2),
    // O(1)
    public int maxAreaBrute(int[] nums) {
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // lower bar * width
                int water = Math.min(nums[i], nums[j]) * (j - i);
                res = Math.max(res, water);
            }
        }

        return res;
    }
}
