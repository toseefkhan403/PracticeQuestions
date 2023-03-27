package com.practice.sliding_window;

public class LongestSubstringWORepeatingChar {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcde"));   
    }

    public static int lengthOfLongestSubstring(String s) {
        String res = "";
        int maxLength = 1;

        int i = 0;
        int j = 0;

        if(s.equals("")) {
            return 0;
        }

        while(i<s.length()) {
            if(j < s.length() && !res.contains(s.charAt(j)+"")) {
                res+=s.charAt(j);
                j++;
            } else {
                // reset
                if(maxLength<res.length()) {
                    maxLength = res.length();
                }
                i++;
                res="";
                j = i;
            }
        }

        return maxLength;
    }

    // sliding window probably
    public static int lengthOfLongestSubstringSlid(String s) {
        String res = "";
        int maxLength = 1;

        int i = 0;
        int j = 0;

        if(s.equals("")) {
            return 0;
        }

        while(i<s.length()) {
            if(j < s.length() && !res.contains(s.charAt(j)+"")) {
                res+=s.charAt(j);
                j++;
            } else {
                // reset
                if(maxLength<res.length()) {
                    maxLength = res.length();
                }
                // if j is over, no point iterating
                if(j >= s.length()-1) {
                    return maxLength;
                }

                i++;
                res=s.substring(i, j);
            }
        }

        return maxLength;
    }
    
}
