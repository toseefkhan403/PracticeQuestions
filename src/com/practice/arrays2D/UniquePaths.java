package com.practice.arrays2D;

public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePathsMath(5, 5));
    }

    // 8C4 8*7*6*5/4*3*2*1
    public static int uniquePathsMath(int m, int n) {
        // m+n-2 C n-1 will give the answer
        int up = n + m - 2;
        int down = m - 1;
        // to avoid overflow
        double res = 1;

        // go from the end - not from the start - as harder to manage
        for (int i = 1; i <= down; i++)
            // does not like shorthand operator
            res = res * (up - down + i) / i;

        return (int) res;
    }

    // recursion - go top down always(end to start here) - 1,1 = 1,0 + 0,1 = 2 ways
    public static int uniquePathsRecur(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        if (m == 1 && n == 1) {
            return 1;
        }

        // left + up
        return uniquePathsRecur(m, n - 1) + uniquePathsRecur(m - 1, n);
    }

    // tip: draw recursive tree for better understanding
    public static int uniquePaths(int m, int n) {
        // if (m <= 0 || n <= 0) {
        //     return 0;
        // }
        if (m == 1 || n == 1) {
            return 1;
        }

        return uniquePaths(m, n - 1) + uniquePaths(m - 1, n);
    }

}
