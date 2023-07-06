package com.practice.graphs;

import java.util.ArrayList;
import java.util.List;

// useful if graph has negative weights - but can't handle negative path cycle(cyle path sum = -ve)
public class BellmanFordAlgorithm {
    // do the relax edges thing N-1 times - no need for PQ - go thru the
    // edgeList(dont convert to adjList) - o(V*E[Quadratic - worse than Dijkstra]),
    // O(V[result])
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int[] distance = new int[V];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = (int) 1e8;
        }

        distance[S] = 0;

        // n-1 times relaxations to all the edges[intuition: will give min dist once
        // each iteration(for a single element) in a straight graph]
        for (int i = 0; i < V - 1; i++) {
            for (List<Integer> p : edges) {
                int u = p.get(0);
                int v = p.get(1);
                int wt = p.get(2);

                if (distance[u] + wt < distance[v]) {
                    distance[v] = distance[u] + wt;
                }
            }
        }

        // after n-1 relaxations should have the min dist - if you can still reduce it,
        // the graph has a negative cycle - will keep on reducing forever
        // N-th relaxation check for negative cycle
        for (List<Integer> p : edges) {
            int u = p.get(0);
            int v = p.get(1);
            int wt = p.get(2);

            if (distance[u] + wt < distance[v]) {
                return new int[] { -1 };
            }
        }

        return distance;
    }

}
