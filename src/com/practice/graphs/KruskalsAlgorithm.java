package com.practice.graphs;

import java.util.Arrays;

public class KruskalsAlgorithm {
    // sort the nodes by weight - use disjoint set - O(ElogE[sorting] +
    // E*4*alpha*2[2 times djset call in edge loop]), O(2V[djset])
    static int spanningTree(int V, int E, int edges[][]) {
        // if adjList is given - convert to edgeList(it will contain each edge
        // twice[undirected graph]) - doesnt matter as disjoint set gives the same
        // answer

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        int sum = 0;

        DisjointSet djSet = new DisjointSet(V);

        for (int[] arr : edges) {
            int u = arr[0];
            int v = arr[1];
            int wt = arr[2];

            // if connected already(same ult parents), dont do anything[a min edge already exists]
            // if not connected already(different ult parents), connect em & add to the MST
            if (djSet.findUParent(u) != djSet.findUParent(v)) {
                sum += wt;
                djSet.unionBySize(u, v);
            }
        }

        return sum;
    }

}
