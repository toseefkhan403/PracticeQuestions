package com.practice.arrays2D;

import java.util.ArrayList;
import java.util.List;

public class TransposeMatrix {
    public static void main(String[] args) {
        int[][] res = transpose(new int[][]{{1,2}, {7,3}, {5,6}});
        for (int[] is : res) {
            for (int is2 : is) {
                System.out.print(is2);
            }
            System.out.println();
        }
    }

    // copy directly to transpose matrix - ij to ji - O(r*c), O(c*r)
    public static int[][] transpose(int[][] matrix) {
        // c*r for r*c size
        int[][] res = new int[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[j][i] = matrix[i][j];
            }
        }

        return res;
    }

    public static List<List<Integer>> transpose_list(int n, int m, List<List<Integer>> arr) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> col = new ArrayList<>();
            // get col from arr -> index fixed - traversing through lists - gives col elements
            for (List<Integer> list : arr) {
                col.add(list.get(i));
            }

            res.add(col);
        }

        return res;
    }

}
