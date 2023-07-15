package com.practice.sorting;

public class Quicksort {
    public static void main(String[] args) {
        int[] nums = { 5, 4, 9, 2, 1, 100, 4, 99 };
        Qsort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    // Choose pivot - partition the array into two halves - one with elements
    // smaller than the pivot - other with greater
    // Recursively sort the two halves - O(nlogn), O(n)
    private static void Qsort(int[] nums, int low, int high) {
        if (low < high) {
            int pIndex = partition(nums, low, high);
            // left half
            Qsort(nums, low, pIndex - 1);
            // right half
            Qsort(nums, pIndex + 1, high);
        }
    }

    // ratta
    private static int partition(int[] nums, int low, int high) {
        // first element as pivot
        int pivot = nums[low];
        int i = low;
        int j = high;
        while (i < j) {
            // find i > pivot element
            while (nums[i] <= pivot && i <= high - 1) {
                i++;
            }
            // find j < pivot element
            while (nums[j] > pivot && j >= low + 1) {
                j--;
            }

            // to avoid swapping when j has crossed
            if (i < j) {
                swap(nums, i, j);
            }
        }
        // swap j with the pivot element - j has crossed
        swap(nums, j, low);

        return j; // pIndex
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
