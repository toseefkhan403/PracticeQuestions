package com.practice.strings;

public class RepeatedStringMatch {

    // todo rabin karp algorithm

    // first repeat a till = length b - check if cointains - if doesn't, repeat once
    // more and check again - if still doesnt -> not possible - O(b+2(a*b)), O(1)
    public int repeatedStringMatch(String a, String b) {
        int res = 1;
        String origA = a;

        while (a.length() < b.length()) {
            a += origA;
            res++;
        }

        // check if substring - a.contains(b)
        if (checkSubstring(a, b)) {
            return res;
        }

        a += origA;
        res++;

        if (checkSubstring(a, b)) {
            return res;
        }

        return -1;
    }

    // sliding window - O(n*m), O(1)
    public boolean checkSubstring(String a, String b) {
        int windowLen = b.length();

        for (int i = 0; i < a.length() - windowLen + 1; i++) {
            if (a.substring(i, i + windowLen).equals(b)) {
                // string matched
                return true;
            }
        }

        return false;
    }
}
