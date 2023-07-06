package com.practice.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPathDijkstra {
    // 1 to n path using Dijkstra - remember where you came from(parents) -
    // backtrack to it in the end - reverse it - O(ElogV+2V), O(4V)
    public static List<Integer> shortestPath(int V, int m, int edges[][]) {
        List<List<Pair>> adjList = getGraph(V, edges);

        // Dijkstra on the basis of dist - faster
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);

        // 1-indexed - thus V+1 size
        int[] distance = new int[V + 1];
        int[] parent = new int[V + 1];
        List<Integer> path = new ArrayList<>();

        for (int i = 1; i < distance.length; i++) {
            distance[i] = (int) 1e8;
            // need to init parent as well
            parent[i] = i;
        }

        distance[1] = 0;
        pq.offer(new Pair(0, 1));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int d = p.dist;
            int node = p.node;

            for (Pair adj : adjList.get(node)) {
                int adjNode = adj.node;
                int adjDist = adj.dist;

                if (d + adjDist < distance[adjNode]) {
                    distance[adjNode] = d + adjDist;
                    pq.offer(new Pair(d + adjDist, adjNode));
                    // add parent
                    parent[adjNode] = node;
                }
            }
        }

        // unreachable - path doesnt exist
        if (distance[V] == (int) 1e8) {
            path.add(-1);
            return path;
        }

        // backtrack to the path
        int curr = V;
        // parent of src = src || curr!=1
        while (parent[curr] != curr) {
            path.add(curr);
            curr = parent[curr];
        }
        path.add(curr); // 1(src)

        reverse(path);

        return path;
    }

    private static void reverse(List<Integer> path) {
        int l = 0;
        int r = path.size() - 1;

        while (l < r) {
            int temp = path.get(l);
            path.set(l, path.get(r));
            path.set(r, temp);
            l++;
            r--;
        }
    }

    // get adjList from edgeList
    private static List<List<Pair>> getGraph(int V, int[][] edges) {
        List<List<Pair>> adjList = new ArrayList<>();

        for (int i = 0; i < V + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            // undirected graph - add links both ways
            adjList.get(v).add(new Pair(wt, u));
            adjList.get(u).add(new Pair(wt, v));
        }

        return adjList;
    }

}

class Pair {
    int dist;
    int node;

    public Pair(int d, int n) {
        dist = d;
        node = n;
    }
}
