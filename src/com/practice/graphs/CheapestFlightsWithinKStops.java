package com.practice.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsWithinKStops {
    // Dijkstra on the basis of stops(not distance) - stops inc by 1 thus PQ not
    // needed - if on the basis of dist, can go beyond stops - another guy could go
    // longer but be under stops - thus order on the basis of stops - O(E[logV
    // avoided - not PQ]), O(2V)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Pair>> adjList = getGraph(n, flights);

        Queue<Triplet> q = new LinkedList<>();

        int[] distance = new int[n];
        Arrays.fill(distance, (int) 1e9);

        distance[src] = 0;

        q.offer(new Triplet(new Pair(0, src), 0));

        while (!q.isEmpty()) {
            Triplet t = q.poll();
            int d = t.pair.dist;
            int node = t.pair.node;
            int stops = t.stops;

            for (Pair p : adjList.get(node)) {
                int adjNode = p.node;
                int adjDist = p.dist;

                // relax the nodes - add only if under the stops limit
                if (d + adjDist < distance[adjNode] && stops <= k) {
                    // dest can be k+1
                    q.offer(new Triplet(new Pair(d + adjDist, adjNode), stops + 1));
                    distance[adjNode] = d + adjDist;
                }
            }
        }

        // unreachable within k stops
        if (distance[dst] == (int) 1e9)
            return -1;

        return distance[dst];
    }

    public List<List<Pair>> getGraph(int n, int[][] flights) {
        List<List<Pair>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {
            int u = flights[i][0];
            int v = flights[i][1];
            int cost = flights[i][2];

            // u->v with cost
            adjList.get(u).add(new Pair(cost, v));
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

class Triplet {
    Pair pair;
    int stops;

    public Triplet(Pair p, int st) {
        pair = p;
        stops = st;
    }
}
