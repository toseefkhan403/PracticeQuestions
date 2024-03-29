package com.practice.recursion;

// Function to determine if graph can be coloured with at most M colours such
// that no two adjacent vertices of graph are coloured with same colour
public class MColoringGraph {
    // for every node try to paint it all possible colors - if at any point
    // recursion reaches the end (valid color) - return true - O(N^M[nodes^colors]),
    // O(n[color arr]+n[aux])
    public boolean graphColoring(boolean graph[][], int m, int n) {
        // node -> color
        int[] color = new int[n];

        // start with 0 - no need to start from every node
        return colorRecur(0, graph, color, m, n);
    }

    public boolean colorRecur(int node, boolean graph[][], int[] color, int m, int n) {
        // reached end - graph colored fully
        if (node == n) {
            return true;
        }

        // trying all colors - 1 to m
        for (int i = 1; i <= m; i++) {
            if (canColor(node, i, graph, color)) {
                color[node] = i;
                if (colorRecur(node + 1, graph, color, m, n))
                    return true;
                // backtracking step
                color[node] = 0;
            }
        }

        return false;
    }

    public boolean canColor(int node, int col, boolean[][] graph, int[] color) {
        // look at the neighbors - adj matrix
        for (int i = 0; i < graph[node].length; i++) {
            // not itself, edge exists, same color neighbor
            if (node != i && graph[node][i] && color[i] == col) {
                return false;
            }
        }

        return true;
    }

}
