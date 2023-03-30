package com.practice.sliding_window;

public class LongestSubstringWORepeatingChar {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcde"));   
    }

    // add to res each character if it isn't already present - if present, update maxLength & reset res,i,j - O(n^2)
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

    // sliding window probably - similar to above - but when repeating char found, don't inc j - inc i and res = substr - next iteration will check automatically
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
                // if j is over, no point iterating - O(n)
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
