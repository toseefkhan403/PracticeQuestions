package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

public class PermSeq {
    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3));
    }

    // brute - calc all permutations - sort them - get kth - O(n!*n[getting
    // perm]+nlogn[sorting]), O(n)

    // todo ask doubt - math - fill result one by one - reduce fact and k in each iteration
    // in the group of n, find the kth permutation - O(n^2[constructing the perm &
    // removing from list]), O(n[nums array])
    public static String getPermutation(int n, int k) {
        int fact = 1;
        List<Integer> nums = new ArrayList<>(n);

        for (int i = 1; i < n; i++) {
            fact *= i;
            nums.add(i);
        }
        nums.add(n);

        // 0-based indexing so fact and k remains one behind
        k = k - 1;
        String result = "";
        while (true) {
            // math - k/fact gives index of the result character
            // add to result and remove from nums
            result = result + nums.get(k / fact);
            // can't use map cuz need to maintain indexing - O(n)
            nums.remove(k / fact);

            // nothing left to add to res
            if (nums.size() == 0) {
                break;
            }

            // k gives which perm to choose next
            k = k % fact;
            // 3!->2!->1!
            fact = fact / nums.size();
        }

        return result;
    }

}
