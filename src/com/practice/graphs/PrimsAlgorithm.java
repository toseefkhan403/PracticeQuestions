package com.practice.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithm {
    // Prim's algorithm - use PQ to go to the less weight edges first - intuition is
    // Greedy(takes the local shortest path first always) - O(2*E*logE[two loops -
    // going thru the edges - PQ]), O(V+E[visited+PQ])
    static int spanningTree(int V, int E, int edges[][]) {
        List<List<Pair>> adjList = getGraph(edges, V);
        int sum = 0;

        boolean[] visited = new boolean[V];

        // can also take parent if MST is asked and put node,parent in a mstList
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.offer(new Pair(0, 0));

        // ElogE
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int node = p.node;
            int wt = p.weight;

            // already visited with a shorter path
            if (visited[node]) {
                continue;
            }

            // mark as visited and add to MST
            visited[node] = true;
            sum += wt;

            // ElogE
            for (Pair adj : adjList.get(node)) {
                // DONT mark the nodes as visited here
                int adjNode = adj.node;
                int adjWeight = adj.weight;

                // dont go back where u came from - else takes unnecessary longer time
                if (!visited[adjNode])
                    pq.offer(new Pair(adjNode, adjWeight));
            }
        }

        return sum;
    }

    public static List<List<Pair>> getGraph(int[][] edges, int V) {
        List<List<Pair>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] list : edges) {
            int u = list[0];
            int v = list[1];
            int wt = list[2];

            adjList.get(u).add(new Pair(v, wt));
            adjList.get(v).add(new Pair(u, wt));
        }

        return adjList;
    }
}

// can also take parent if MST is asked
class Pair {
    int node;
    int weight;

    public Pair(int n, int w) {
        node = n;
        weight = w;
    }
}
