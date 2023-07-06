package com.practice.strings;

public class LongestPalindromicSubstring {
    // expand OUTWARDS to check the palindrome - O(2n^2), O(1)
    public String longestPalindrome(String s) {
        String res = "";
        int l = 0, r = 0;

        for (int i = 0; i < s.length(); i++) {
            // check odd length palin
            l = i;
            r = i;

            while (l >= 0 && r < s.length()) {
                if (s.charAt(l) != s.charAt(r))
                    break;
                l--;
                r++;
            }

            // length is not r-l+1
            if (r - l - 1 > res.length()) {
                // take example - l gets one behind
                res = s.substring(l + 1, r);
            }

            // check even length palin - just start r pointer 1 ahead - everything else same
            l = i;
            r = i + 1;

            while (l >= 0 && r < s.length()) {
                if (s.charAt(l) != s.charAt(r))
                    break;
                l--;
                r++;
            }

            if (r - l - 1 > res.length()) {
                res = s.substring(l + 1, r);
            }
        }

        return res;
    }

    // almost brute - two pointer - start at end cuz need longest
    // check palin everytime - O(n^3), O(1)
    public String longestPalindromeBrute(String s) {
        int l = 0;
        int r = s.length() - 1;

        String res = "";

        for (int i = 0; i < s.length(); i++) {
            // reset l and r
            l = i;
            r = s.length() - 1;

            // slight optimization - wont find any longer palins now - break
            if (res.length() > r - l + 1) {
                break;
            }

            // move the end closer
            while (l <= r) {
                if (isPalin(s, l, r)) {
                    String temp = s.substring(l, r + 1);
                    if (temp.length() > res.length())
                        res = temp;
                    // can break as next palins will be smaller
                    break;
                }
                r--;
            }
        }

        return res;
    }

    public boolean isPalin(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--))
                return false;
        }

        return true;
    }

}
