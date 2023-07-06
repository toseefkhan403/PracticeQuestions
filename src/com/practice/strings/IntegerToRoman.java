package com.practice.strings;

import java.util.ArrayList;
import java.util.List;

class IntegerToRoman {
    // map the values with the exceptions - keep it reversed - O(num ~ 1), O(1)
    public String intToRoman(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        StringBuilder res = new StringBuilder();

        // no need for / or % - just subtract
        for (int i = 0; i < strs.length; i++) {
            if (num == 0)
                break;

            // subtract and repeat if char found
            while (num >= values[i]) {
                res.append(strs[i]);
                num -= values[i];
            }
        }

        return res.toString();
    }

    // add the exceptions to the map - iterate and add from the end - check digit by
    // digit - O(num ~ 1), O(1)
    public String intToRomanI(int num) {
        List<Pair> list = new ArrayList<>();

        list.add(new Pair("I", 1));
        list.add(new Pair("IV", 4));
        list.add(new Pair("V", 5));
        list.add(new Pair("IX", 9));
        list.add(new Pair("X", 10));
        list.add(new Pair("XL", 40));
        list.add(new Pair("L", 50));
        list.add(new Pair("XC", 90));
        list.add(new Pair("C", 100));
        list.add(new Pair("CD", 400));
        list.add(new Pair("D", 500));
        list.add(new Pair("CM", 900));
        list.add(new Pair("M", 1000));

        String res = "";

        for (int i = list.size() - 1; i >= 0; i--) {
            Pair p = list.get(i);

            if (num / p.value != 0) {
                res += p.roman;
                // subtract and repeat if char found
                num -= p.value;
                i++;
                continue;
            }

            num = num % p.value;
            if (num == 0)
                break;
        }

        return res;
    }

}

class Pair {
    String roman;
    int value;

    public Pair(String r, int v) {
        roman = r;
        value = v;
    }
}