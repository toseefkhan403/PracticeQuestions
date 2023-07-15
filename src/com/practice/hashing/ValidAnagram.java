package com.practice.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    // only 26 letters - make freq arr - inc then dec - O(n), O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] alphabet = new int[26];

        for (int i = 0; i < s.length(); i++) {
            // trick for indexing
            alphabet[s.charAt(i) - 'a']++;
            alphabet[t.charAt(i) - 'a']--;
        }

        // everyone must be 0
        for (int it : alphabet) {
            if (it != 0)
                return false;
        }

        return true;
    }

    // sort em - match em - O(2nlogn + n), O(n)
    public boolean isAnagramSort(String s, String t) {
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

    // brute use hashmap - freq map
    public boolean isAnagramSpace2(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            int freq = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), freq + 1);
        }

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

    // use hashmap - freq map - 1 pass - O(2n), O(n)
    public boolean isAnagramSpace1(String s, String t) {
        if (s.length() != t.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();

        // add and subtract freq to the map in the same loop
        for (int i = 0; i < s.length(); i++) {
            int freqA = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), freqA + 1);
            // freqB after first map.put
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
}
