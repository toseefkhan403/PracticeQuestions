package com.practice.arrays2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args) {
        System.out.println(generate(6));
    }

    // more concise
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>(numRows);
        List<Integer> prevArr = new ArrayList<>();

        // two loops - 1-indexed - 1 till numRows
        for (int i = 1; i <= numRows; i++) {
            // i is the rowSize
            List<Integer> row = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                // if first or last -> 1
                if (j == 0 || j == i - 1) {
                    row.add(j, 1);
                } else {
                    // else sum of prevArr's prev two elements
                    row.add(j, prevArr.get(j) + prevArr.get(j - 1));
                }
            }

            // update prevArr and add to res
            prevArr = row;
            res.add(row);
        }

        return res;
    }

    public static List<List<Integer>> generateI(int numRows) {
        List<List<Integer>> res = new ArrayList<>(numRows);
        List<Integer> prevArr = Arrays.asList(1);
        res.add(prevArr);

        // two loops
        for (int i = 2; i <= numRows; i++) {
            // i is the rowSize
            List<Integer> row = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                // if first or last -> 1
                if (j == 0 || j == i - 1) {
                    row.add(j, 1);
                } else {
                    // else sum of prevArr's prev two elements
                    row.add(j, prevArr.get(j) + prevArr.get(j - 1));
                }
            }

            // update prevArr and add to res
            prevArr = row;
            res.add(row);
        }

        return res;
    }

}
