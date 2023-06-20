package com.practice.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShortestPathDAG {
    // O(2(v+e)[topo+dist thing]), O(3v) [ignoring adjList]
    public int[] shortestPath(int V, int M, int[][] edges) {
        List<List<Pair>> adjList = getGraph(V, edges);

        Stack<Integer> st = new Stack<>();
        boolean[] vis = new boolean[V];

        // 1. do topo sort
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfsTopo(i, adjList, st, vis);
            }
        }

        // 2. find shortest dist from topo sort
        int[] dist = new int[V];

        // fill dist with infinity - does not work with Integer.MAX_VALUE
        for (int i = 0; i < V; i++) {
            dist[i] = (int) 1e9;
        }

        // src = 0
        dist[0] = 0;

        while (!st.empty()) {
            int temp = st.pop();

            // reach temp first - then try to go to its neighbours - add the distance
            // topo ensures that this dist isn't infinity if you can reach the nodes
            int d = dist[temp];

            for (Pair p : adjList.get(temp)) {
                int v = p.node;
                int wt = p.weight;

                // relax the edges
                if (d + wt < dist[v]) {
                    dist[v] = d + wt;
                }
            }
        }

        // remove infinity - can't reach these nodes
        for (int i = 0; i < V; i++) {
            if (dist[i] == (int) 1e9) {
                dist[i] = -1;
            }
        }

        return dist;
    }

    // get adjList from the edges
    private List<List<Pair>> getGraph(int V, int[][] edges) {
        List<List<Pair>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            adjList.get(u).add(new Pair(v, wt));
        }

        return adjList;
    }

    // dfs topo sort - can use bfs as well
    private void dfsTopo(int node, List<List<Pair>> adjList, Stack<Integer> st, boolean[] vis) {
        vis[node] = true;

        for (Pair p : adjList.get(node)) {
            int neighbour = p.node;
            if (!vis[neighbour]) {
                dfsTopo(neighbour, adjList, st, vis);
            }
        }

        st.push(node);
    }

}

class Pair {
    int node;
    int weight;

    public Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
