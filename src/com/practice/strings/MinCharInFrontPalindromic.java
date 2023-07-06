package com.practice.strings;

public class MinCharInFrontPalindromic {

    // todo using KMP algorithm

    // brute - start from end - check palindrome at each step - when found, the no
    // of chars at the end = ans - O(n^2), O(1)
    public static int minChar(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (checkPalin(0, i, str)) {
                return str.length() - i - 1;
            }
        }

        return -1;
    }

    private static boolean checkPalin(int l, int r, String str) {
        while (l < r) {
            if (str.charAt(l++) != str.charAt(r--)) {
                return false;
            }
        }

        return true;
    }

}
