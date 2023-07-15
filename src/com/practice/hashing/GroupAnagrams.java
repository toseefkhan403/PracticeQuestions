package com.practice.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    // map with freq arr as the key - will get grouped together - O(m[word
    // size]*n*26), O(n)
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        // O(1) space
        int[] alphabets = new int[26];

        for (int i = 0; i < strs.length; i++) {
            for (char ch : strs[i].toCharArray()) {
                alphabets[ch - 'a']++;
            }

            // dont use alphabet.toString() - gives same address
            String key = Arrays.toString(alphabets);
            List<String> group = map.getOrDefault(key, new ArrayList<>());

            group.add(strs[i]);
            map.put(key, group);

            // clear alphabets
            Arrays.fill(alphabets, 0);
        }

        List<List<String>> res = new ArrayList<>();
        res.addAll(map.values());

        return res;
    }

    // add to res - if it matches with anyone inside - add - else add as list -
    // O(m[word size]*n^2), O(1)
    public List<List<String>> groupAnagramsI(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0)
            return res;

        for (int i = 0; i < strs.length; i++) {
            String word = strs[i];

            boolean anaExists = false;
            // find if can group
            for (int j = 0; j < res.size(); j++) {
                List<String> group = res.get(j);

                if (isAnagram(group.get(0), word)) {
                    // add
                    group.add(word);
                    res.set(j, group);
                    anaExists = true;
                    break;
                }
            }

            if (!anaExists) {
                List<String> temp = new ArrayList<>();
                temp.add(word);
                res.add(temp);
            }
        }

        return res;
    }

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

}
