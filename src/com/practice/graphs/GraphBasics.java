package com.practice.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// can be done using adj matrix as well - same logic - just iterate adjMatrix[src][i] to get the neighbour elements
public class GraphBasics {
    // start from 0 - go thru each neighbour - keep a visited array
    // no. of nodes, adjacency list - 0-indexed[0 to v-1] - O(n),O(2n)
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        // initialize visited array
        for (int i = 0; i < V; i++) {
            visited.add(false);
        }

        dfs(0, adj, visited, result);
        return result;
    }

    private void dfs(int num, ArrayList<ArrayList<Integer>> adj, ArrayList<Boolean> visited,
            ArrayList<Integer> result) {
        result.add(num);
        visited.set(num, true);

        // add from the adjacency list of the element
        ArrayList<Integer> neighbourList = adj.get(num);

        for (int i = 0; i < neighbourList.size(); i++) {
            if (!visited.get(neighbourList.get(i))) {
                dfs(neighbourList.get(i), adj, visited, result);
            }
        }
    }

    // use a queue to print bfs - add neighbours to the q only if they are not
    // visited - O(n),O(n)
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        // initialize visited array
        for (int i = 0; i < V; i++) {
            visited.add(false);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited.set(0, true);

        while (!q.isEmpty()) {
            int num = q.poll();
            result.add(num);

            // add num's neighbours to the queue if they are not visited
            for (int neighbour : adj.get(num)) {
                if (!visited.get(neighbour)) {
                    q.offer(neighbour);
                    visited.set(neighbour, true);
                }
            }
        }

        return result;
    }

}
