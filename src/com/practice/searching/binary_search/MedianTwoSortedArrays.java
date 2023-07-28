package com.practice.searching.binary_search;

public class MedianTwoSortedArrays {
    // merge them - find the median - O(n+m), O(1) - dont store the merged arr -
    // just keep a count and stop at the median

    // binary search on the smaller arr - try to find the correct partition - when
    // found, mid elements is the median - O(log(min(m,n))), O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // nums1 is the smaller guy
        int low = 0;
        int high = m;

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = ((m + n + 1) / 2) - cut1;

            int l1 = cut1 > 0 ? nums1[cut1 - 1] : Integer.MIN_VALUE;
            int l2 = cut2 > 0 ? nums2[cut2 - 1] : Integer.MIN_VALUE;

            int r1 = cut1 < m ? nums1[cut1] : Integer.MAX_VALUE;
            int r2 = cut2 < n ? nums2[cut2] : Integer.MAX_VALUE;

            // valid partition
            if (l1 <= r2 && l2 <= r1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                // go back l1 - go left
                high = cut1 - 1;
            } else {
                // go right
                low = cut1 + 1;
            }
        }

        return -1.0;
    }

}
