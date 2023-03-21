package com.practice.hashmap;

import java.util.HashMap;

public class RomanToInteger {
    
    public static void main(String[] args) {
        System.out.println(romanToInteger("MCMXCIV"));  // 1994
    }

    public static int romanToInteger(String s) {
        int sum = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            sum+=map.get(curr);
            if(i == 0) continue;
            char prev = s.charAt(i-1);
            if(map.get(prev) < map.get(curr)) {
                sum-= 2*map.get(prev);
            }
        }

        return sum;
    }
    
}
