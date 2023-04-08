package com.practice.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    public static void main(String[] args) {
        System.out.println(totalFruit(new int[] { 1, 2, 3, 2, 2 }));
    }

    // same as longest substring with K distinct characters
    public static int totalFruit(int[] fruits) {
        int windowStart = 0, maxLength = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {
            freqMap.put(fruits[windowEnd], freqMap.getOrDefault(fruits[windowEnd], 0) + 1);

            // shrink window size if > 2 types of fruits are present
            while (freqMap.size() > 2) {
                int count = freqMap.get(fruits[windowStart]);
                if (count > 1) {
                    freqMap.put(fruits[windowStart], count - 1);
                } else {
                    freqMap.remove(fruits[windowStart]);
                }

                windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

}
