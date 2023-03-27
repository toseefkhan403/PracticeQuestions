package com.practice.hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class MissingNumbers {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(11 ,4, 11, 7, 13 ,4, 12 ,11, 10, 14));
        List<Integer> brr = new ArrayList<>(Arrays.asList(11 ,4, 11 ,7, 3 ,7, 10 ,13 ,4, 8, 12, 11, 10, 14, 12));

        System.out.println(missingNumbersMap(arr, brr));
    }

    // brute - flag the ones present in both - scan in the end - left with the answer
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            int index = brr.indexOf(arr.get(i));
            brr.set(index, -1);
        }

        // whatever is left in the brr is the answer
        for (int i = 0; i < brr.size(); i++) {
            if(!brr.get(i).equals(-1)) {
                res.add(brr.get(i));
            }
        }

        Collections.sort(res);

        return res;
    }

    // using treemap - have frequencies for brr - remove for arr - left with the answer
    // treemap stores in sorted order by default - O(N),O(N)
    public static List<Integer> missingNumbersMap(List<Integer> arr, List<Integer> brr) {
        // element,frequency
        TreeMap<Integer,Integer> res = new TreeMap<>();

        // add elements
        for (int i = 0; i < brr.size(); i++) {
            int element = brr.get(i);
            if(res.get(element) == null) {
                res.put(element, 1);
            } else {
                int count = res.get(element);
                res.put(element, ++count);
            }
        }

        // remove elements
        for (int i = 0; i < arr.size(); i++) {
            int element = arr.get(i);
            int count = res.get(element);
            count--;
            if(count <= 0) {
                // remove from the treemap
                res.remove(element);
            } else {
                res.put(element, count);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Integer integer : res.keySet()) {
            result.add(integer); 
        }

        return result;
    }
    
}
