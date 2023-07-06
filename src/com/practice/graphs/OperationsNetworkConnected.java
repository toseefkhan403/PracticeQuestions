package com.practice.graphs;

import java.util.ArrayList;
import java.util.List;

public class OperationsNetworkConnected {
    // no need to calc extra edges separately - no of components-1 ops needed to
    // connect the graph - dfs - O(V+2E), O(2V[visited arr+aux space])
    public int makeConnected(int n, int[][] connections) {
        // need atleast n-1 edges to make the graph connected
        if (connections.length < n - 1)
            return -1;

        List<List<Integer>> adjList = getGraph(n, connections);
        boolean[] vis = new boolean[n];

        int res = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, adjList, vis);
                res++;
            }
        }

        return res - 1;
    }

    private void dfs(int i, List<List<Integer>> adjList, boolean[] vis) {
        vis[i] = true;

        // go thru the neighbours if havent visited em already
        for (int neighbour : adjList.get(i)) {
            if (!vis[neighbour]) {
                dfs(neighbour, adjList, vis);
            }
        }
    }

    // no of extra edges >= no of components(no of ult parents) - 1 (n-1 ops needed
    // to connect n components) - O(2n*4alpha), O(2n)
    public int makeConnectedDS(int n, int[][] connections) {
        // edge list given - u,v
        DisjointSet djSet = new DisjointSet(n);

        int extraEdges = 0;
        int compCount = 0;

        for (int[] arr : connections) {
            int u = arr[0];
            int v = arr[1];

            // same ult parent = already connected = extra edge
            if (djSet.findUParent(u) == djSet.findUParent(v))
                extraEdges++;
            else
                // not connected - connect em
                djSet.unionBySize(u, v);
        }

        // counting no of components
        for (int i = 0; i < n; i++) {
            if (djSet.parent.get(i) == i)
                compCount++;
        }

        int res = compCount - 1;

        if (extraEdges >= res) {
            return res;
        }

        // not possible to connect the components
        return -1;
    }

    public int makeConnectedDS2(int n, int[][] connections) {
        if (connections.length < n - 1)
            return -1;

        // edge list given - u,v
        DisjointSet djSet = new DisjointSet(n);

        int compCount = 0;

        for (int[] arr : connections) {
            int u = arr[0];
            int v = arr[1];

            djSet.unionBySize(u, v);
        }

        // counting no of components
        for (int i = 0; i < n; i++) {
            if (djSet.parent.get(i) == i)
                compCount++;
        }

        return compCount - 1;
    }

    private List<List<Integer>> getGraph(int n, int[][] connections) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] arr : connections) {
            int u = arr[0];
            int v = arr[1];

            // bidirectional graph
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }
}
