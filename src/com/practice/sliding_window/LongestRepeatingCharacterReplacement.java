package com.practice.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("abaabb", 2));
    }

    // if the count of non-repeating letters in a substring becomes >k, shrink the window
    public static int characterReplacement(String s, int k) {
        int windowStart = 0;
        int maxRepeatingLetter = 0;
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightChar = s.charAt(windowEnd);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            // count of repeating letters
            maxRepeatingLetter = Math.max(maxRepeatingLetter, map.get(rightChar));

            // count of non-repeating letters(=windowSize-maxRepeatingLetter)>k
            // shrink by 1 - no while loop required
            if (windowEnd - windowStart + 1 - maxRepeatingLetter > k) {
                char leftChar = s.charAt(windowStart);
                map.put(leftChar, map.get(leftChar) - 1);
                windowStart++;
            }

            // conrl <= k - update result with windowSize
            result = Math.max(result, windowEnd - windowStart + 1);
        }

        return result;
    }

    public static int characterReplacementI(String s, int k) {
        int windowStart = 0;
        int maxRepeatingLetter = 0;
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            map.put(s.charAt(windowEnd), map.getOrDefault(s.charAt(windowEnd), 0) + 1);

            int windowSize = windowEnd - windowStart + 1;
            maxRepeatingLetter = Math.max(maxRepeatingLetter, map.get(s.charAt(windowEnd)));

            if (windowSize - maxRepeatingLetter > k) {
                char leftChar = s.charAt(windowStart);
                map.put(leftChar, map.get(leftChar) - 1);
                windowStart++;
            } else {
                result = windowSize;
            }
        }

        return result;
    }

}
