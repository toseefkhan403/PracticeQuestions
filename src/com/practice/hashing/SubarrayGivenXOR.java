package com.practice.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubarrayGivenXOR {
    // x^k = XR - look for x(=XR^k) in the map - similar to subarray with sum k -
    // O(n), O(n)
    public int solve(ArrayList<Integer> A, int k) {
        // currXOR, count -> count cuz there can be >1 x values for a subarray - need to
        // add them all
        Map<Integer, Integer> map = new HashMap<>();
        // add 0,1 initially - if the reqd subarray starts at 0 - this will count it
        map.put(0, 1);

        int currXOR = 0;
        int res = 0;

        for (int i = 0; i < A.size(); i++) {
            currXOR ^= A.get(i);

            // look for x = XR^k in the map
            if (map.containsKey(currXOR ^ k)) {
                // inc res by count
                res += map.get(currXOR ^ k);
            }

            map.put(currXOR, map.getOrDefault(currXOR, 0) + 1);
        }

        return res;
    }

    // brute: gen all subarrays - cnt all with given xor - O(n^2), O(1)
    public int solveBrute(ArrayList<Integer> A, int B) {
        int xor = 0;
        int res = 0;

        for (int i = 0; i < A.size(); i++) {
            xor = 0;
            for (int j = i; j < A.size(); j++) {
                xor ^= A.get(j);
                if (xor == B)
                    res++;
            }
        }

        return res;
    }

}
