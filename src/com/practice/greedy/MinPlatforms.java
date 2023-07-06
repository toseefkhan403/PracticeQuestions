package com.practice.greedy;

import java.util.Arrays;

// find the minimum number of platforms required at the railway station such that no train waits.
public class MinPlatforms {
    // NOT same as n meetings(just count the no of rooms)
    // sort arrival and dep separately - order doesnt matter - track arr and dep of
    // the trains on platforms manually - O(2nlogn+2n), O(1)
    static int findPlatform(int arr[], int dep[], int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int res = 1;
        int platforms = 1;

        int i = 1, j = 0;

        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                // arrive the trains on new platforms
                platforms++;
                i++;
                res = Math.max(res, platforms);
            } else {
                // depart the trains
                platforms--;
                j++;
            }
        }

        return res;
    }

    static int findPlatformI(int arr[], int dep[], int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int res = 0;
        int platforms = 0;

        int p1 = 0, p2 = 0;

        while (p1 < n) {
            if (arr[p1] <= dep[p2]) {
                // arrive the trains on new platforms
                platforms++;
                p1++;
                res = Math.max(res, platforms);
            } else {
                // depart the trains - optional while loop
                while (arr[p1] > dep[p2]) {
                    platforms--;
                    p2++;
                }
            }
        }

        return res;
    }
}
