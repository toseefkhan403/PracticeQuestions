package com.practice.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathUnitDistance {
    // bfs with queue - relax nodes(Dijkstra without the PQ and pair) - O(V+2E), O(2V)
    public int[] shortestPath(int[][] edges, int V, int m, int src) {
        List<List<Integer>> adjList = getGraph(edges, V);

        int[] distance = new int[V];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = (int) 1e9;
        }

        // can also take a node,dist pair here - but dist=distance[node] - thus not
        // taking it
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);

        distance[src] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neighbour : adjList.get(node)) {
                // relax the nodes - each edge has 1 weight
                if (distance[node] + 1 < distance[neighbour]) {
                    distance[neighbour] = distance[node] + 1;
                    q.offer(neighbour);
                }
            }
        }

        for (int i = 0; i < distance.length; i++) {
            // unreachable node
            if (distance[i] == (int) 1e9) {
                distance[i] = -1;
            }
        }

        return distance;
    }

    // get adjList from edgeList
    private List<List<Integer>> getGraph(int[][] edges, int V) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            // undirected graph
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }
}
