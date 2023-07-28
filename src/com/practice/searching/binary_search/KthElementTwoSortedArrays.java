package com.practice.searching.binary_search;

public class KthElementTwoSortedArrays {
    // optimal: binary search on the small arr - find the partition - k elements on
    // one side, rest on the other side - O(log(min(m,n))), O(1)
    // similar to Median of two sorted arrays
    public long kthElementOpti(int arr1[], int arr2[], int n, int m, int k) {
        if (n > m) {
            return kthElement(arr2, arr1, m, n, k);
        }

        // edge cases: have to take some elements from arr1 if k > m
        int low = Math.max(0, k - m);
        // cant take all elements from arr1 if k < n
        int high = Math.min(n, k);

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = k - cut1;

            int l1 = cut1 > 0 ? arr1[cut1 - 1] : Integer.MIN_VALUE;
            int l2 = cut2 > 0 ? arr2[cut2 - 1] : Integer.MIN_VALUE;

            int r1 = cut1 < n ? arr1[cut1] : Integer.MAX_VALUE;
            int r2 = cut2 < m ? arr2[cut2] : Integer.MAX_VALUE;

            // valid partition
            if (l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            } else if (l1 > r2) {
                // go left
                high = cut1 - 1;
            } else {
                // go right
                low = cut1 + 1;
            }
        }

        return -1;
    }

    // brute: merge them - find the kth guy - dont use extra space - keep count and
    // return - O(k), O(1)
    public long kthElement(int arr1[], int arr2[], int n, int m, int k) {
        int cnt = 0;
        int res = 0;

        int l = 0;
        int r = 0;

        while (l < n && r < m) {
            if (arr1[l] <= arr2[r]) {
                res = arr1[l++];
            } else {
                res = arr2[r++];
            }

            cnt++;
            if (cnt == k) {
                return res;
            }
        }

        // add all arr1 guys
        while (l < n) {
            res = arr1[l++];
            cnt++;
            if (cnt == k) {
                return res;
            }
        }

        // add all arr2 guys
        while (r < m) {
            res = arr2[r++];
            cnt++;
            if (cnt == k) {
                return res;
            }
        }

        return res;
    }

}
