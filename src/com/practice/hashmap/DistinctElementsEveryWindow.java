package com.practice.hashmap;

import java.util.HashMap;

public class DistinctElementsEveryWindow {
    public static void main(String[] args) {
        distinctElementsEveryWindow(new int[] { 1, 2, 2, 1, 3, 1, 1, 3 }, 4);
    }

    // going back n checking if duplicate in every window - O((n-k+1)*k^2)
    private static void distinctElementsEveryWindowBrute(int[] nums, int k) {
        for (int i = 0; i <= nums.length-k; i++) {
            int count = 1;
            // go k places and count distinct elements - loop k times - hence j<i+k - not j<k
            for (int j = i+1; j < i+k; j++) {
                // go till i and check if distinct
                int temp = j-1;
                boolean isDistinct = true;

                while(temp>=i) {
                    if(nums[temp] == nums[j]) {
                        isDistinct = false;
                        break;
                    }
                    temp--;
                }
                
                if(isDistinct) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    // in single pass - sliding window - prefill till k - then remove i-1 and add
    // i+k-1 to the map and print map size - O(n),O(k)
    private static void distinctElementsEveryWindow(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            // put values till k in the map
            map.put(nums[i], map.getOrDefault(map.get(nums[i]), 0) + 1);
        }

        System.out.println(map.size());

        // can go from 1 to n-k or from k to n-1
        for (int i = 1; i <= nums.length - k; i++) {
            // remove i-1th value
            if (map.get(nums[i - 1]) == 1) {
                map.remove(nums[i - 1]);
            } else {
                map.put(nums[i - 1], map.get(nums[i - 1])-1);
            }

            // add new value - i+k-1th value
            map.put(nums[i + k - 1], map.getOrDefault(nums[i + k - 1], 0)+1);

            System.out.println(map.size());
        }
    }
}
