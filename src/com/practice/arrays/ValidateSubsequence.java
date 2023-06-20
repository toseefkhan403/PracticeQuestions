package com.practice.arrays;

import java.util.List;

public class ValidateSubsequence {
    public static void main(String[] args) {
        System.out.println(isSubsequence("aaaaaa", "bbaaaa"));
    }

    // brute: calc all subsequences - match with the ans
    // traverse smaller arr - find element in the original arr - then match with
    // ahead elements only - as order needs to be maintained
    public static int validateSubsequence(int n1, List<Integer> arr, int n2, List<Integer> subSeq) {
        // needed because subseq needs to be in the same order
        int arrIndex = 0;
        for (int i = 0; i < subSeq.size(); i++) {
            int el = subSeq.get(i);
            boolean elFound = false;

            // find el in the arr ahead
            for (int j = arrIndex; j < arr.size(); j++) {
                if (arr.get(j) == el) {
                    // needs to be j+1 to move it ahead - else gets stuck at the same spot if subSeq
                    // elements are same
                    arrIndex = j + 1;
                    elFound = true;
                    break;
                }
            }

            if (!elFound)
                return 0;
        }

        return 1;
    }

    public static int validateSubsequence2(int n1, List<Integer> arr, int n2, List<Integer> subSeq) {
        int j = 0;

        // arr -> i , subSeq -> j
        for (int i = 0; i < arr.size() && j < subSeq.size(); i++) {
            if (arr.get(i) == subSeq.get(j)) {
                j++;
            }
        }

        return j == subSeq.size() ? 1 : 0;
    }

    // match one by one using two pointers
    public static boolean isSubsequence(String s, String t) {
        // for tracking s
        int j = 0;
        // single loop - inc smaller pointer only when match found
        for (int i = 0; i < t.length() && j < s.length(); i++) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
        }

        // if j reaches end - all chars of s found in t
        return j == s.length();
    }

    // top-down version
    public static boolean isSubsequenceRecur2(String s, String t, int i, int j) {
        // this check first - as both could be standing on 0
        if (j == 0) {
            return true;
        }
        if (i == 0) {
            return false;
        }

        // i-1 as length is passed - prevents indexoutofbounds
        if (t.charAt(i - 1) == s.charAt(j - 1)) {
            // dec both if found - else dec only t index
            return isSubsequenceRecur2(s, t, i - 1, j - 1);
        } else {
            return isSubsequenceRecur2(s, t, i - 1, j);
        }
    }

    // t -> i and s -> j - same approach as iterative
    public static boolean isSubsequenceRecur(String s, String t, int i, int j) {
        if (i == t.length() || j == s.length()) {
            return j == s.length();
        }

        if (t.charAt(i) == s.charAt(j)) {
            return isSubsequenceRecur(s, t, i + 1, j + 1);
        } else {
            return isSubsequenceRecur(s, t, i + 1, j);
        }
    }

    public static boolean isSubsequenceI(String s, String t) {
        int tIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            char toFind = s.charAt(i);
            boolean isFound = false;

            for (int j = tIndex; j < t.length(); j++) {
                if (t.charAt(j) == toFind) {
                    tIndex = j + 1;
                    isFound = true;
                    break;
                }
            }

            if (!isFound)
                return false;
        }

        return true;
    }

}
