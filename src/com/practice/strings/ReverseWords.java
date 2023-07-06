package com.practice.strings;

import java.util.ArrayList;
import java.util.List;

public class ReverseWords {
    // iterate from the start - put it before res
    // O(n), O(1)
    public String reverseWords(String s) {
        String res = "";
        String word = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (word.length() > 0) {
                    // first word - dont add the space
                    if (res.length() == 0) {
                        res += word;
                    } else {
                        // keep adding before res
                        res = word + " " + res;
                    }

                    // reset
                    word = "";
                }
                continue;
            }

            word += s.charAt(i);
        }

        // last word
        if (word.length() > 0) {
            // first word - dont add the space
            if (res.length() == 0) {
                res += word;
            } else {
                // keep adding before res
                res = word + " " + res;
            }
        }

        return res;
    }

    // iterate from the end - reverse and put in res - reverse takes extra time -
    // avoid
    public String reverseWordsBetter(String s) {
        String res = "";
        String word = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (word.length() > 0) {
                    res += reverse(word);
                    // reset
                    word = "";
                }
                continue;
            }

            word += s.charAt(i);
        }

        if (word.length() > 0) {
            res += reverse(word);
        }

        // ignore the last space char
        return res.substring(0, res.length() - 1);
    }

    private String reverse(String word) {
        int l = 0;
        int r = word.length() - 1;

        char[] arr = word.toCharArray();

        while (l < r) {
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }

        return new String(arr) + " ";
    }

    // make a list of the words - add to res in reverse order
    // O(n+no. of words), O(n)
    public String reverseWordsBrute(String s) {
        List<String> words = new ArrayList<>();

        String word = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (word.length() > 0) {
                    words.add(word);
                    // reset
                    word = "";
                }
                continue;
            }

            word += s.charAt(i);
        }

        // add the last word
        if (word.length() > 0) {
            words.add(word);
        }

        System.out.println(words);

        // add to res in reverse order
        String res = "";
        for (int i = words.size() - 1; i >= 0; i--) {
            res += words.get(i);
            // add spaces - but not after the last guy
            if (i != 0) {
                res += " ";
            }
        }

        return res;
    }

}
