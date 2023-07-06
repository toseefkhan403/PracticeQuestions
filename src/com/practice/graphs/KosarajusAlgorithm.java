package com.practice.graphs;

import java.util.ArrayList;
import java.util.Stack;

// SCC exists in Undirected graphs only
public class KosarajusAlgorithm {
    // No of strongly connected components in Undirected graph - O(3(V+E)), O(3V+E)
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        // 1. sort the nodes from first to finish using dfs[to get the starting point]
        // (if not sorted, the reversed graph will still have weakly connected comp)
        for (int i = 0; i < vis.length; i++) {
            if (!vis[i]) {
                dfs(i, vis, adj, stack);
            }
        }

        // 2. reverse/transpose all the graph edges[so that weakly connected comp are broken]
        ArrayList<ArrayList<Integer>> adjRev = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjRev.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            // reset vis arr
            vis[i] = false;

            for (int nei : adj.get(i)) {
                // i to nei -> nei to i
                adjRev.get(nei).add(i);
            }
        }

        int scc = 0;

        // 3. count the no of disconnected comp using dfs - using the sorted nodes
        while (!stack.isEmpty()) {
            int src = stack.pop();

            if (!vis[src]) {
                dfs(src, vis, adjRev);
                scc++;
            }
        }

        return scc;
    }

    // can print here to print the scc
    private void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;

        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                dfs(neighbour, vis, adj);
            }
        }
    }

    private void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {
        vis[node] = true;

        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                dfs(neighbour, vis, adj, stack);
            }
        }

        // push to stack in the end - last node stays at the bottom
        stack.push(node);
    }

}
