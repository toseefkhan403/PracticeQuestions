package com.practice.math;

import java.util.Arrays;
import java.lang.Math;

public class MaxNoOfGroups {

    public static void main(String[] args) {
        
    }

    // does not work for some cases
    // can apply binary search on indices - if formula(on index) gives <= n go right and update length - if >n go left
    public static int maximumGroupsBin(int[] grades) {
        int n = grades.length;
        int low = 0;
        int high = n-1;
        int length = 1;

        while(low<=high) {
            int mid = (low+high)/2;
            // applying formula
            int l = mid*(mid+1)/2;

            if(l<=n) {
                // go right
                low = mid+1;
                // update length here - as this area only gives answer
                length = mid;
            } else {
                // go left
                high = mid-1;
            }
        }

        return length;
    }

    // l*(l+1)/2 = n should give the answer - approximation -> answer is sqrt(2n) or sqrt(2n-1)
    private static int maximumGroups(int[] grades) {
        int n = grades.length;
        int res = (int) Math.sqrt(n * 2);
        // if formula gives <= n - res is answer - else overflown - so res-1 is the answer
        if(res*(res+1)/2 <= n) {
            return res;
        } else {
            return res-1;
        }
    }

    // brute - sort & make groups of 1-2-3-4 - keep checking sum
    private static int maximumGroupsSort(int[] grades) {
        Arrays.sort(grades);

        int length = 0;
        int prevSum = 0;
        int insertIndex = 0;

        // 1 - 2 - 3 - 4
        for (int i = 0; i < grades.length; i++) {
            int currSum = 0;
            // go i iterations
            for (int j = 0; j <= i; j++) {
                if(insertIndex<grades.length) {
                    currSum+=grades[insertIndex];
                    insertIndex++;
                } else {
                    // going outofbounds
                    return length;
                }
            }
            if(currSum>prevSum) {
                prevSum=currSum;
                length++;
            } else {
                return length;
            }
        }

        return 1;
    }
    
}
