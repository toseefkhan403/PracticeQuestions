package com.practice.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    // sort em - match em - O(2nlogn + n), O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        // cant sort strings
        char[] first = s.toCharArray();
        char[] second = t.toCharArray();

        Arrays.sort(first);
        Arrays.sort(second);

        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i])
                return false;
        }

        return true;
    }

    // use hashmap - freq map - 1 pass - O(2n), O(n)
    public boolean isAnagramMap1(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();

        // add and subtract freq to the map in the same loop
        for (int i = 0; i < s.length(); i++) {
            int freqA = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), freqA + 1);
            // get freqB AFTER put - else same char clash
            int freqB = map.getOrDefault(t.charAt(i), 0);
            map.put(t.charAt(i), freqB - 1);
        }

        // every value must be 0
        for (int f : map.values()) {
            if (f != 0)
                return false;
        }

        return true;
    }

    // brute use hashmap - freq map - 2 pass - O(3n), O(n)
    public boolean isAnagramMap2(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();

        // add to map
        for (int i = 0; i < s.length(); i++) {
            int freq = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), freq + 1);
        }

        // subtract from map
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                int freq = map.get(t.charAt(i));
                map.put(t.charAt(i), freq - 1);
            } else {
                // unknown char came
                return false;
            }
        }

        // every value must be 0
        for (int f : map.values()) {
            if (f != 0)
                return false;
        }

        return true;
    }

}
