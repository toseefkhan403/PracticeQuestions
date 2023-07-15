package com.practice.sorting;

import java.util.ArrayList;
import java.util.List;

public class ReversePairs {
    // merge sort - similar to count inversions - sorted arrays - compare with right
    // arr and add all till right if condn matches - dont check inside left array -
    // O(nlogn+n), O(n)
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    public int mergeSort(int[] nums, int low, int high) {
        int cnt = 0;
        if (low >= high)
            return cnt;

        int mid = (low + high) / 2;
        cnt += mergeSort(nums, low, mid);
        cnt += mergeSort(nums, mid + 1, high);
        cnt += countPairs(nums, low, mid, high);
        merge(nums, low, mid, high);

        return cnt;
    }

    // add all till right everytime - O(n)
    private int countPairs(int[] nums, int low, int mid, int high) {
        int cnt = 0;
        int j = mid + 1;

        for (int i = low; i <= mid; i++) {
            // cast to long to avoid int overflow
            while (j <= high && (long) nums[i] > (long) 2 * nums[j])
                j++;

            // need brackets - else wrong ans
            cnt += (j - (mid + 1));
        }

        return cnt;
    }

    private void merge(int[] nums, int low, int mid, int high) {
        // list required
        List<Integer> temp = new ArrayList<>();
        // just take the starting indices and iterate
        int i = low;
        int j = mid + 1;

        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) {
                temp.add(nums[i++]);
            } else {
                temp.add(nums[j++]);
            }
        }

        while (i <= mid) {
            temp.add(nums[i++]);
        }

        while (j <= high) {
            temp.add(nums[j++]);
        }

        // loop from low to high - not 0 to n
        for (int k = low; k <= high; k++) {
            // k-low -> 0,1,2
            nums[k] = temp.get(k - low);
        }
    }

    // brute - two nested loops finding rev pairs - gives tle - O(n^2)
    public int reversePairsBrute(int[] nums) {
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long) nums[i] > (long) 2 * nums[j]) {
                    res++;
                }
            }
        }

        return res;
    }

}
