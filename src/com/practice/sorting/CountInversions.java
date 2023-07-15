package com.practice.sorting;

import java.util.ArrayList;
import java.util.List;

public class CountInversions {
    // merge sort - intuition pro
    // sorted arrays - compare with right arr and add all if lesser - dont check in
    // array itself - O(nlogn), O(n)
    static long inversionCount(long arr[], long N) {
        return mergeSort(arr, 0, (int) N - 1);
    }

    public static long mergeSort(long arr[], int l, int r) {
        long cnt = 0;
        if (l >= r)
            return cnt;

        int mid = (l + r) / 2;
        cnt += mergeSort(arr, l, mid);
        cnt += mergeSort(arr, mid + 1, r);
        cnt += merge(arr, l, mid, r);
        return cnt;
    }

    private static long merge(long[] arr, int l, int mid, int r) {
        List<Long> temp = new ArrayList<>();
        int i = l;
        int j = mid + 1;

        long cnt = 0;
        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp.add(arr[i++]);
            }
            // right is smaller - count all left guys after i - all will be greater than the
            // right guy
            else {
                cnt += mid + 1 - i;
                temp.add(arr[j++]);
            }
        }

        while (i <= mid) {
            temp.add(arr[i++]);
        }

        while (j <= r) {
            temp.add(arr[j++]);
        }

        for (int k = l; k <= r; k++) {
            arr[k] = temp.get(k - l);
        }

        return cnt;
    }

    // brute: just count the inversions - O(n^2), O(1)
    static long inversionCountBrute(long arr[], long N) {
        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[i]) {
                    res++;
                }
            }
        }

        return res;
    }

}
