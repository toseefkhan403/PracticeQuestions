package com.practice.arrays2D;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle2 {
    public static void main(String[] args) {
        System.out.println(getRowMath(5));
    }

    // same logic as calculating every row of pascal's triangle - returning last row - O(n^2),O(n)
    public List<Integer> getRow(int rowIndex) {
        List<Integer> prevArray = new ArrayList<>();

        // rowIndex+1 as it is 0-indexed
        for (int i = 1; i <= rowIndex+1; i++) {
            // i is the rowSize
            List<Integer> row = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                if(j==0 || j==i-1) {
                    row.add(j,1);
                } else {
                    row.add(j, prevArray.get(j)+prevArray.get(j-1));
                }  
            }

            // update
            prevArray = row;
        }

        return prevArray;
    }

    // element is given by -> r-1 C c-1 - do this for whole row - O(n),O(1)
    public static List<Integer> getRowMath(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex+1);

        int prevElement = 1;
        res.add(prevElement);

        // integer overflows in some cases
        for (int i = 1; i < rowIndex+1; i++) {
            // using shortcut to calculate r-1 Combination c-1 - multiply and divide prevElement
            prevElement *= rowIndex-i+1;
            prevElement /= i;
            res.add(prevElement);
        }

        return res;
    }
    
}
