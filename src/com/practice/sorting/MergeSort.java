package com.practice.sorting;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    // break at mid - sort and merge the halves
    // O(nlogn), O(n[temp arr])
    public static void mergeSort(int arr[], int l, int r) {
        if (l >= r)
            return;

        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    // 2 pointers - take small guy and put it in temp arraylist
    private static void merge(int[] arr, int l, int mid, int r) {
        // dont use array - have to take arr.length everytime - adds unnecessary space
        List<Integer> temp = new ArrayList<>();
        int i = l;
        int j = mid + 1;

        while (i <= mid && j <= r) {
            if (arr[i] < arr[j]) {
                temp.add(arr[i++]);
            } else {
                temp.add(arr[j++]);
            }
        }

        // add the leftover guys
        while (i <= mid) {
            temp.add(arr[i++]);
        }

        while (j <= r) {
            temp.add(arr[j++]);
        }

        // copy back to arr
        for (int k = l; k <= r; k++) {
            // temp stores as 0,1,2 - k-l gives 0,1,2
            arr[k] = temp.get(k - l);
        }
    }

}
