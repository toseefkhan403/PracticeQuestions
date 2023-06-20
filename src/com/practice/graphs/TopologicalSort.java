package com.practice.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Only in DAG - in others dont make sense - no before/after
public class TopologicalSort {
    // normal dfs - just store nodes in stack - O(v+e), O(2v+v)
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        int[] result = new int[V];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }

        return result;
    }

    static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, Stack<Integer> stack) {
        vis[node] = true;

        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                dfs(neighbour, adj, vis, stack);
            }
        }

        // push the node AFTER the recursions
        stack.push(node);
    }

    // Kahn's algorithm - Topo sort using BFS
    // need indegree arr instead of visited arr - O(v+e), O(v+v)
    static int[] topoSortBFS(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] topo = new int[V];
        int[] indegree = new int[V];

        // fill indegree(no of incoming nodes) - ++ when found in adjList
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        // add all the 0 indegree to the queue
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int i = 0;
        // dec the indegree of neighbours - if 0 -> add to the queue
        while (!q.isEmpty()) {
            int temp = q.poll();
            // 0 indegree guy - nobody before it - can add to topo
            topo[i++] = temp;

            // remove temp from the indegree[from its neighbours]
            for (int it : adj.get(temp)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.offer(it);
                }
            }
        }

        return topo;
    }

}
