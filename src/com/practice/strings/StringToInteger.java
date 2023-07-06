package com.practice.strings;

public class StringToInteger {
    // follow steps - take care of edge cases - O(n[at most 11 times - int limit =
    // -2^31, 2^31-1]), O(1)
    public int myAtoi(String s) {
        if (s.length() == 0)
            return 0;

        int res = 0;

        int sign = 1;
        int i = 0;

        // ignore leading whitespaces ONLY
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        // check sign only ONCE - if it comes again -> return 0
        if (i < s.length() && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        for (int j = i; j < s.length(); j++) {
            int digit = s.charAt(j) - '0';
            // alphabet check - cant proceed further if any alphabet is present
            if (digit < 0 || digit > 9)
                break;

            // overflow check - move vars to left side of the eqn to check
            if ((Integer.MAX_VALUE - digit) / 10 < res) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            // build the number
            res = res * 10 + digit;
        }

        // multiply the ans with sign
        return res * sign;
    }

}
