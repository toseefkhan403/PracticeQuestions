package com.practice.strings;

import java.util.Arrays;

public class LongestCommonPrefix {
    // sort the strs - will be grouped together with prefixes - match first and last
    // str - O(nlogn+m), O(1)
    public String longestCommonPrefix(String[] strs) {
        String res = "";

        if (strs.length == 0)
            return res;

        Arrays.sort(strs);

        String first = strs[0];
        // sorted so the strings in b/w will have the same common prefix as first and last
        String last = strs[strs.length - 1];

        // no need for boundary checks
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) == last.charAt(i)) {
                res += first.charAt(i);
            } else {
                // lcp found
                break;
            }
        }

        return res;
    }

    // match one by one with every string - keep the common prefix - O(n*m(avg length of the string)), O(1)
    public String longestCommonPrefixI(String[] strs) {
        String res = "";

        if (strs.length == 0)
            return res;

        res = strs[0];

        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];

            // match one by one
            int ind = 0;

            while (ind < res.length() && ind < str.length() && res.charAt(ind) == str.charAt(ind)) {
                ind++;
            }

            // lcp this iteration
            res = str.substring(0, ind);

            // no common prefix found - return
            if (res == "")
                return res;
        }

        return res;
    }

}
