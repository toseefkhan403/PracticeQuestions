package com.practice.graphs;

import java.util.LinkedList;
import java.util.Queue;

// Bipartite graph: if can color graph with 2 colors - adjacent colors must be different
// linear and even length cycle graphs are always bipartite - odd length cycle graphs are not
public class IsBipartite {
    // dfs using color array - O(v+2e), O(v)
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];

        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }

        // for disconnected components
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!dfsBipartite(graph, i, 0, color)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean dfsBipartite(int[][] graph, int node, int toColor, int[] color) {
        color[node] = toColor;

        for (int neighbour : graph[node]) {
            // not colored yet
            if (color[neighbour] == -1) {
                // color with the opp color - if this recursion call is false, return false
                if (!dfsBipartite(graph, neighbour, 1 - toColor, color))
                    return false;
            }
            // neighbour is the same color as the node - not bipartite
            else if (color[neighbour] == toColor) {
                return false;
            }
            // neighbour is diff color as the node - continue the process
        }

        return true;
    }

    // bfs using color array - O(v+2e), O(v)
    public boolean bfsBipartite(int[][] graph, int node, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        color[node] = 0;
        q.add(node);

        while (!q.isEmpty()) {
            int temp = q.poll();

            for (int neighbour : graph[temp]) {
                // not colored yet
                if (color[neighbour] == -1) {
                    // toggle the color
                    color[neighbour] = 1 - color[temp];
                    q.add(neighbour);
                } else if (color[neighbour] == color[temp]) {
                    // not a bipartite graph
                    return false;
                }
                // neighbour has opp color - continue the process
            }
        }

        return true;
    }
}
