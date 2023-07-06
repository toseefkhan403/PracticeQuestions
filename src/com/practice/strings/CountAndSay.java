package com.practice.strings;

public class CountAndSay {
    // for loop till n - count freq and build say string - O(n*x[avg length of the
    // seq]), O(1)
    public String countAndSay(int n) {
        String say = "1";

        for (int i = 2; i <= n; i++) {
            say = sayFun(say);
        }

        return say;
    }

    // count freq and build say string
    public String sayFun(String str) {
        // use stringbuilder in string problems - faster
        StringBuilder res = new StringBuilder();
        int n = str.length();

        int freq = 1;

        // can also add $ to str at the end for checking the last char - slower
        for (int i = 1; i <= n; i++) {
            if (i < n && str.charAt(i) == str.charAt(i - 1)) {
                freq++;
            } else {
                res.append(freq);
                res.append(str.charAt(i - 1));
                // reset freq
                freq = 1;
            }
        }

        return res.toString();
    }

    // count freq and build say string - same approach as above
    public String sayFunI(String str) {
        String res = "";
        int n = str.length();

        int i = 1;

        while (i <= n) {
            int freq = 1;
            while (i < n && str.charAt(i) == str.charAt(i - 1)) {
                freq++;
                i++;
            }

            res += String.valueOf(freq) + str.charAt(i - 1);
            i++;
        }

        return res;
    }

}
