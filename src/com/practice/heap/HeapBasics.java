package com.practice.heap;

import java.util.List;

// imaginary heap - just playing with indices
public class HeapBasics {
    // complete binary tree with heap property -> all children nodes should be
    // lower/higher than their parent
    // 1-based indexing - floor(i/2) = parent, 2*i = left child, 2*i+1 = right child
    // leaf nodes = floor(n/2) + 1 to n
    public static void main(String[] args) {

    }

    // heap represented as array - max heap - O(logn),O(1)
    public static void insert(List<Integer> arr, int n, int value) {
        // insert at the end - keep swapping with the parent till heap property is
        // satisfied
        n = n + 1;
        arr.set(n, value);
        int i = n;

        // step-up
        while (i > 1) {
            int parent = i / 2;
            if (arr.get(parent) < arr.get(i)) {
                swap(arr, i, parent);
                // value at parent index now
                i = parent;
            } else {
                // correct heap - return
                return;
            }
        }
    }

    private static void swap(List<Integer> arr, int i, int parent) {
        int temp = arr.get(i);
        arr.set(i, arr.get(parent));
        arr.set(parent, temp);
    }

    // delete the root - replace with the last element - step-down for heap property
    // - O(logn),O(1)
    public static void delete(List<Integer> arr, int n) {
        arr.set(1, n);
        arr.remove(n);
        int i = 1;

        // step-down
        while (i < n) {
            // add 2*i+1 < n check to avoid indexoutofbounds
            int left = arr.get(2 * i);
            int right = arr.get(2 * i + 1);
            int larger = left < right ? 2 * i + 1 : 2 * i;

            if (arr.get(larger) > arr.get(i)) {
                swap(arr, larger, i);
                i = larger;
            } else {
                return;
            }
        }
    }

    // start from end - can start this method from n/2 -> 1 as leaf nodes are heaps
    // already - O(n)
    public static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i > 0; i--) {
            heapify(a, n, i);
        }
    }

    // brute: insert elements one by one and swap to follow heap property - O(logn)
    // optimized: convert arr to heap - step-up
    public static void heapify(int[] a, int n, int i) {
        int l = 2 * i;
        int r = 2 * i + 1;
        int larger = l < r ? 2 * i + 1 : 2 * i;

        if (larger < n && a[i] < a[larger]) {
            swap(a, larger, i);
            // convert the tree below to heap
            heapify(a, n, larger);
        }
    }

    private static void swap(int[] a, int larger, int i) {
        int temp = a[i];
        a[i] = a[larger];
        a[larger] = temp;
    }

}
