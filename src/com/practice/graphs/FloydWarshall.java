package com.practice.graphs;

// multisource - shortest distances between every pair of vertices - weighted directed graph
public class FloydWarshall {
    // go via every node - add the shortest path in every iteration(will help in
    // next iterations) - O(n^3), O(1[since in-place])
    // negative cycle if mat[i][i] != 0 (dist to node itself shd always be 0 - if
    // not, negative cycle exists and will keep on dec forever)
    public void shortest_distance(int[][] matrix) {
        int n = matrix.length;

        // make -1(no direct path) weight inf - need to do comparisons later
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = (int) 1e9;
                }
            }
        }

        // go via every node - mat[i][j] = [i][0] + [0][j] (going via 0)
        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][via] + matrix[via][j]);
                }
            }
        }

        // make unreachable nodes -1 again
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == (int) 1e9) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

}
