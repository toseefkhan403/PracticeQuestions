package com.practice.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKdistinct {
    public static void main(String[] args) {
        System.out.println(longestSubstringKdistinct("araaci", 2));
        System.out.println(longestSubstringKdistinct("cbbebi", 3));
    }

    // frequency map's size gives the count of distinct elements - shrink window if
    // map size > k - update maxlength with windowsize as now the count of distinct
    // chars is <=k
    private static int longestSubstringKdistinct(String str, int k) {
        if (str == null || str.length() == 0 || str.length() < k) {
            throw new IllegalArgumentException();
        }

        int windowStart = 0;
        int maxLength = 0;
        Map<Character, Integer> freqMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            freqMap.put(rightChar, freqMap.getOrDefault(rightChar, 0) + 1);

            // shrink the window until we are left with K distinct characters
            while (freqMap.size() > k) {
                char leftChar = str.charAt(windowStart);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                if (freqMap.get(leftChar) == 0) {
                    freqMap.remove(leftChar);
                }
                windowStart++;
            }

            // <=k distinct characters - update maxlength
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    private static int longestSubstringKdistinctT(String str, int k) {
        int windowStart = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxLength = 0;

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            freqMap.put(str.charAt(windowEnd), freqMap.getOrDefault(str.charAt(windowEnd), 0) + 1);

            if (freqMap.size() <= k) {
                maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
            } else {
                // shrink the window
                while (freqMap.size() > k) {
                    int count = freqMap.get(str.charAt(windowStart));
                    if (count > 1) {
                        freqMap.put(str.charAt(windowStart), count - 1);
                    } else {
                        freqMap.remove(str.charAt(windowStart));
                    }
                    windowStart++;
                }
            }
        }

        return maxLength;
    }

}
