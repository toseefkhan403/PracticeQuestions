package com.practice.sorting;

import java.util.List;

public class SelectionSort {

    public static void main(String[] args) {
        
    }

    // with swapping
    public static List<Integer> insertion_sort(List<Integer> arr) {

        for (int i = 0; i < arr.size(); i++) {
            int j = i;
            // keep shifting to the left
            while(j>0 && arr.get(j-1) > arr.get(j)) {
                int temp = arr.get(j-1);
                arr.set(j-1, arr.get(j));
                arr.set(j, temp);
                j--;
            }
        }

        return arr;
    }

    // find min in the rest of the array - put it in front - repeat
    public static List<Integer> selection_sort(List<Integer> arr) {
        // no need to check the last element because the last element is already sorted if n-1 elements are sorted
        for (int i = 0; i < arr.size()-1; i++) {
            int min = i;
            for (int j = i+1; j < arr.size(); j++) {
                if(arr.get(j) < arr.get(min)) {
                    min = j;
                }
            }
            
            // swap i with min
            int temp = arr.get(i);
            arr.set(i, arr.get(min));
            arr.set(min, temp);
        }
        
        return arr;
    }
    
}
