package com.practice.graphs;

import java.util.ArrayList;
import java.util.List;

public class NumberOfProvinces {
    // traversal using adj matrix - O(V+2E), O(2N[visited arr+aux space])
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int res = 0;

        // count the number of disconnected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                res++;
                dfs(i, isConnected, visited);
            }
        }

        return res;
    }

    // normal dfs using adj matrix
    private void dfs(int src, int[][] isConnected, boolean[] visited) {
        visited[src] = true;

        // go to the neighbours if they are not already visited
        // row/column gives the neighbours(if it is 1)
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[src][i] == 1 && !visited[i]) {
                // i is the neighbour
                dfs(i, isConnected, visited);
            }
        }
    }

    // if we ignore making the adjList -> O(2n[visited+dfs]), O(2n[visited array+dfs
    // aux space])
    // else O(n^2+2n), O(n^2+2n)
    public int findCircleNumList(int[][] isConnected) {
        int n = isConnected.length;
        List<List<Integer>> adjList = new ArrayList<>();
        // initialize the adjList
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[n];
        int res = 0;

        // need to get adj list from the given adj matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // means i and j are connected - add them to the adjList
                if (isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        // count the number of disconnected components(for loop on visited array with a
        // counter)
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                res++;
                dfs(i, adjList, visited);
            }
        }

        return res;
    }

    // normal dfs using adjList
    private void dfs(int num, List<List<Integer>> adjList, boolean[] visited) {
        visited[num] = true;

        // go to the neighbours only if they are not already visited
        for (int neighbour : adjList.get(num)) {
            if (!visited[neighbour]) {
                dfs(neighbour, adjList, visited);
            }
        }
    }

}
