package com.practice.arrays;

public class CountInversions {

    // merge sort - todo
    static long inversionCount(long arr[], long N) {

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
