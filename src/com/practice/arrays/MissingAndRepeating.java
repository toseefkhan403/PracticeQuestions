package com.practice.arrays;

public class MissingAndRepeating {
    // xor all - left with 2 guys - 2^3 -> doesnt work - bit manip is usually a bad idea

    // optimal: use maths - sum of first n natural nos - sum of squares of first n
    // natural nos - make eqns - solve - use long to avoid int overflow - O(n), O(1)
    int[] findTwoElement(int arr[], int N) {
        // n has to be a long
        long n = N;
        long Sn = (n * (n + 1)) / 2;
        long Sn2 = (n * (n + 1) * ((2 * n) + 1)) / 6;

        long S = 0;
        long S2 = 0;

        for (int i = 0; i < n; i++) {
            S += arr[i];
            S2 += (long) arr[i] * (long) arr[i];
        }

        // x-y
        long val1 = S - Sn;
        // x^2 - y^2
        long val2 = S2 - Sn2;
        // x+y
        val2 = val2 / val1;

        long x = (val1 + val2) / 2;
        long y = x - val1;

        return new int[] { (int) x, (int) y };
    }

    // use hash arr for counting - O(2n), O(n)
    int[] findTwoElementBetter(int arr[], int n) {
        // 1-indexed
        int[] hash = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int it = arr[i];
            hash[it]++;
        }

        int missing = -1;
        int repeating = -1;

        for (int i = 1; i < hash.length; i++) {
            if (hash[i] == 0) {
                missing = i;
            } else if (hash[i] == 2) {
                repeating = i;
            }

            // both guys found - break
            if (missing != -1 && repeating != -1)
                break;
        }

        return new int[] { repeating, missing };
    }

    // brute: keep vars for missing and repeating - count 1 to n - if 0 -> missing,
    // if 2 -> repeating, O(n^2), O(1)
    int[] findTwoElementBrute(int arr[], int n) {
        int missing = -1;
        int repeating = -1;

        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] == i)
                    count++;
            }

            if (count == 0) {
                missing = i;
            } else if (count == 2) {
                repeating = i;
            }

            // both guys found - break
            if (missing != -1 && repeating != -1)
                break;
        }

        return new int[] { repeating, missing };
    }
}
