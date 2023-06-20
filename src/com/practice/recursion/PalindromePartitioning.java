package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    // O((2^n)[check all substrings(power set)] * k[ds->result(avg length of palin
    // list)] * (n/2)[checking palin])
    // O(k[avg length of palin list] * x[no of lists] + n[aux space])
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partRecur(s, 0, new ArrayList<>(), result);
        return result;
    }

    // pick and not pick - partition one by one - go ahead only if left side is a
    // palindrome
    public static void partRecur(String s, int idx, List<String> ds, List<List<String>> result) {
        // idx reaches the end - valid partition found
        if (idx == s.length()) {
            result.add(new ArrayList<>(ds));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            // check if left substring is a palindrome
            // idx[begin] till i[end]
            String left = s.substring(idx, i + 1); // end is exclusive
            if (isPalin(left)) {
                ds.add(left);
                // IMPORTANT! - its i+1 - not idx+1
                partRecur(s, i + 1, ds, result);
                // remove when coming back from recursion
                ds.remove(ds.size() - 1);
            }
        }
    }

    public static boolean isPalin(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }
}
