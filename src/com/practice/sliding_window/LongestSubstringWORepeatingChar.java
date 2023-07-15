package com.practice.sliding_window;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWORepeatingChar {
    // sliding window with set - after each iteration check the max length -
    // O(2n), O(n)
    public int lengthOfLongestSubstring(String s) {
        // dont need stringbuilder or string - use SET
        Set<Character> set = new HashSet<>();
        int wS = 0;
        int res = 0;

        for (int wE = 0; wE < s.length(); wE++) {
            char ch = s.charAt(wE);
            // shrink the window till the repeating char is not removed
            while (set.contains(ch)) {
                set.remove(s.charAt(wS++));
            }

            res = Math.max(res, wE - wS + 1);
            // add to the set at the end
            set.add(ch);
        }

        return res;
    }

    // brute: check for every char the longest substring - use set to check for
    // repeating chars - O(n^3), O(n)
    public int lengthOfLongestSubstringBrute(String s) {
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String subString = s.substring(i, j + 1);
                if (!containsRepeating(subString)) {
                    res = Math.max(res, subString.length());
                }

            }
        }

        return res;
    }

    // using set length concept - O(n), O(n)
    private boolean containsRepeating(String s) {
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }

        return set.size() < s.length();
    }

}
