package com.practice.math;

public class Pow {
    // take examples to understand the formula
    // O(logn), O(1)
    public double myPow(double x, int n) {
        double res = 1;

        // long as n can overflow if it is -Integer.min_val
        long nn = n;

        if (nn < 0) {
            nn = -nn;
        }

        while (nn > 0) {
            // nn is odd
            if ((nn & 1) != 0) {
                // last power will always be 1 - thus res will contain the ans
                res = res * x;
                nn -= 1;
            }
            // nn is even
            else {
                x = x * x;
                nn = nn >> 1;
            }
        }

        // nn has changed - check n
        if (n < 0) {
            res = (double) 1.0 / (double) res;
        }

        return res;
    }

    // brute - O(n), O(1) - will overflow
    public double myPowBrute(double x, int n) {
        for (int i = 0; i < n; i++) {
            x *= x;
        }

        return x;
    }

}
