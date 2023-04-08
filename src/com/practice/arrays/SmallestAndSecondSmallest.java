package com.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallestAndSecondSmallest {
    public static void main(String[] args) {
        System.out.println(minAnd2ndMin(6, Arrays.asList(1, 2, 1, 3, 6, 7)));
    }

    // loop thru - update min2 with min value
    public static List<Integer> minAnd2ndMin(int n, List<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < min) {
                min2 = min;
                min = arr.get(i);
            }
            // else if required - incase min stays big - also check if element not equal to
            // min - as min and min2 can't be same
            else if (arr.get(i) < min2 && min != arr.get(i)) {
                min2 = arr.get(i);
            }
        }

        res.add(min);
        res.add(min2);

        return res;
    }
}
