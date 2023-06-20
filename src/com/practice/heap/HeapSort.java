package com.practice.heap;

public class HeapSort {
    // build a max heap from arr - delete root one by one and store in result -
    // O(nlogn),O(logn)
    public static int[] sortArrayMax(int[] nums) {
        int n = nums.length;
        buildHeap(nums, n);
        for (int i = n - 1; i > 0; i--) {
            // push first guy[max] to the end
            swap(nums, 0, i);
            // n is exclusive here - thus not i-1
            heapifyMax(nums, i, 0);
        }

        return nums;
    }

    // convert arr to heap - step-up - O(logn),O(logn)
    public static void heapifyMax(int[] a, int n, int i) {
        int largestIndex = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && a[l] > a[largestIndex]) {
            largestIndex = l;
        }
        if (r < n && a[r] > a[largestIndex]) {
            largestIndex = r;
        }

        if (largestIndex != i) {
            swap(a, largestIndex, i);
            // convert the tree below to heap
            heapifyMax(a, n, largestIndex);
        }
    }

    // build a min heap from arr - delete root one by one and store in result
    public static int[] sortArray(int[] nums) {
        int n = nums.length;
        buildHeap(nums, n);
        // no need for extra space for result
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = nums[0];
            swap(nums, 0, n - i - 1);
            heapify(nums, n - i - 1, 0);
        }
        return res;
    }

    // start from end - can start this method from n/2 -> 1 as leaf nodes are heaps
    // already - O(n)
    public static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 0; i--) {
            heapify(a, n, i);
        }
    }

    // brute: insert elements one by one and swap to follow heap property - O(logn)
    // optimized: convert arr to heap - step-up
    public static void heapify(int[] a, int n, int i) {
        int smallestIndex = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && a[l] < a[smallestIndex]) {
            smallestIndex = l;
        }
        if (r < n && a[r] < a[smallestIndex]) {
            smallestIndex = r;
        }

        if (smallestIndex != i) {
            swap(a, smallestIndex, i);
            // convert the tree below to heap
            heapify(a, n, smallestIndex);
        }
    }

    private static void swap(int[] a, int larger, int i) {
        int temp = a[i];
        a[i] = a[larger];
        a[larger] = temp;
    }

}
