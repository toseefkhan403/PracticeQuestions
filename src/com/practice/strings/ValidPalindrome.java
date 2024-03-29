package com.practice.strings;

public class ValidPalindrome {
    // two pointers - O(n), O(1)
    public boolean isPalindrome(String s) {
        int r = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                continue;
            }

            while (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }

            if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(r))) {
                r--;
            } else {
                return false;
            }
        }

        return true;
    }

    // same approach with while loop
    public boolean isPalindromeWhile(String s) {
        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            while (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }

            while (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }

            if (Character.toLowerCase(s.charAt(l)) == Character.toLowerCase(s.charAt(r))) {
                l++;
                r--;
            } else {
                return false;
            }
        }

        return true;
    }
}
