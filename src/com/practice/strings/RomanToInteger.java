package com.practice.strings;

import java.util.HashMap;

public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV")); // 1994
    }

    // 2 pointers curr and prev - loop thru - add everything - subtract 2x if prev
    // val < curr val - O(n), O(1)
    public static int romanToInt(String s) {
        int sum = 0;
        int curr = 0;
        int prev = 0;

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    curr = 1;
                    break;
                case 'V':
                    curr = 5;
                    break;

                case 'X':
                    curr = 10;
                    break;

                case 'L':
                    curr = 50;
                    break;

                case 'C':
                    curr = 100;
                    break;

                case 'D':
                    curr = 500;
                    break;

                case 'M':
                    curr = 1000;
                    break;
            }

            sum += curr;

            // i=0 doesnt have a prev
            if (i != 0 && prev < curr)
                sum -= 2 * prev;

            prev = curr;
        }

        return sum;
    }

    // use a map - add everything - subtract 2x if prev val < curr val - O(n), O(n)
    public static int romanToInteger(String s) {
        int sum = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            sum += map.get(curr);
            if (i == 0)
                continue;
            char prev = s.charAt(i - 1);
            if (map.get(prev) < map.get(curr)) {
                sum -= 2 * map.get(prev);
            }
        }

        return sum;
    }

}
