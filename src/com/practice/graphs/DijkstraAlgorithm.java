package com.practice.graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

// can also be implemented using queue and set
// queue goes on unnecessary paths - takes extra time
// cant use set in java - but the idea is to add pairs in set & delete if there is already one pair with longer dist in the set
// PQ is the most optimized - O(ElogV[see derivation]), O(V[PQ])
public class DijkstraAlgorithm {
    // Function to find the shortest distance of all the vertices
    // from the source vertex S
    // same as finding shortest path in DAG - just use PQ instead of topo sort -
    // PQ(min heap) always gives the smallest guy based on distance
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        int[] distance = new int[V];

        // init dist arr with big value
        for (int i = 0; i < distance.length; i++) {
            distance[i] = (int) 1e9;
        }

        // min heap on the basis of dist - can also be on the basis of node - but that
        // takes more time(as it explores unnecessary paths)
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.offer(new Pair(0, S));

        // make the source 0 distance
        distance[S] = 0;

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int dist = p.dist;
            int node = p.node;

            for (ArrayList<Integer> neighbour : adj.get(node)) {
                int adjNode = neighbour.get(0);
                int adjWeight = neighbour.get(1);

                if (dist + adjWeight < distance[adjNode]) {
                    distance[adjNode] = dist + adjWeight;
                    // add to PQ
                    pq.offer(new Pair(dist + adjWeight, adjNode));
                }
            }
        }

        return distance;
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
