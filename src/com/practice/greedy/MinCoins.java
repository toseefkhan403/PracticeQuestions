package com.practice.greedy;

import java.util.ArrayList;
import java.util.List;

public class MinCoins {
    // start from the end - take as many high denominations as possible to minimize
    // the no of coins - O(V), O(1)
    static List<Integer> minPartition(int V) {
        List<Integer> res = new ArrayList<>();

        // greedy doesnt work if den are random numbers
        int[] den = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000 };

        int currVal = 0;

        for (int i = den.length - 1; i >= 0; i--) {
            if (den[i] + currVal <= V) {
                res.add(den[i]);
                currVal += den[i];
                // can choose the same coin again
                i++;
            }
        }

        return res;
    }

}
