package com.practice.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class HasCycle {
    // O(V+2E)[traversal]+O(N)[visited loop], O(2n[visited array, queue/aux space])
    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        // for disconnected components - +(not*)O(N)
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (hasCycleBfs(i, adj, visited))
                    return true;
            }
        }

        return false;
    }

    // normal dfs - but if you are visiting same guy twice(not a parent) - it has
    // cycle
    public static boolean hasCycleDfs(int src, int parent, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        visited[src] = true;

        for (int neighbour : adjList.get(src)) {
            if (!visited[neighbour]) {
                // src is the parent now
                if (hasCycleDfs(neighbour, src, adjList, visited))
                    return true;
            } else if (parent != neighbour) {
                // found cycle
                return true;
            }
        }

        return false;
    }

    // normal bfs - but if you are visiting same guy twice(not a parent) - it has
    // cycle
    public static boolean hasCycleBfs(int src, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(src, -1));
        visited[src] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int neighbour : adjList.get(p.node)) {
                if (!visited[neighbour]) {
                    q.offer(new Pair(neighbour, p.node));
                    visited[neighbour] = true;
                } else if (p.parent != neighbour) {
                    // cycle found
                    return true;
                }
            }
        }

        return false;
    }

}

class Pair {
    int node;
    int parent;

    Pair(int src, int parent) {
        this.node = src;
        this.parent = parent;
    }
}
