package com.practice.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class HasCycleDirected {
    // only visited arr is not enough - need pathVisited as well
    // if node visited again on the SAME PATH - cycle exists
    // dfs with vis and pathVis - O(v+e), O(2v+v)
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        boolean[] pathVis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (dfsDirectedCycle(i, adj, vis, pathVis)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfsDirectedCycle(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, boolean[] pathVis) {
        vis[node] = true;
        pathVis[node] = true;

        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                if (dfsDirectedCycle(neighbour, adj, vis, pathVis))
                    return true;
            } else if (pathVis[neighbour]) {
                // cycle found
                return true;
            }
        }

        // remove node from the path when coming back from recursion
        // HAS to be on the same path for cycle to exist
        pathVis[node] = false;
        return false;
    }

    // BFS - try doing a topo sort - if can't -> has a cycle
    // Kahn's algorithm - Topo sort using BFS
    // need indegree arr instead of visited arr - O(v+e), O(v+v)
    public boolean isCyclicBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] indegree = new int[V];

        // fill indegree(no of incoming nodes) - ++ when found in adjList
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        // add all the 0 indegree to the queue
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // count sufficient - no need to give actual topo sort
        int topoCount = 0;

        // dec the indegree of neighbours - if 0 -> add to the queue
        while (!q.isEmpty()) {
            int temp = q.poll();
            topoCount++;

            // remove temp from the indegree[from its neighbours]
            for (int it : adj.get(temp)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.offer(it);
                }
            }
        }

        return !(topoCount == V);
    }

}
