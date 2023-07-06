package com.practice.strings;

public class FirstIndexOfFirstOccurrence {

    // todo Z-Function
    // todo KMP algorithm

    // sliding window - O(n*m), O(1)
    public int strStr(String haystack, String needle) {
        int windowLen = needle.length();

        for (int i = 0; i < haystack.length() - windowLen + 1; i++) {
            if (haystack.substring(i, i + windowLen).equals(needle)) {
                // string matched
                return i;
            }
        }

        return -1;
    }

    // brute - search for the first char - then compare substring - O(n*m), O(1)
    public int strStrBrute(String haystack, String needle) {
        for (int i = 0; i < haystack.length(); i++) {
            // matching the first char
            if (haystack.charAt(i) == needle.charAt(0)) {
                int len = i + needle.length();
                if (len <= haystack.length() && haystack.substring(i, len).equals(needle)) {
                    // string matched
                    return i;
                }
            }
        }

        return -1;
    }
}
