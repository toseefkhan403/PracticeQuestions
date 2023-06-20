package com.practice.graphs;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    // same as detect cycle in directed graph - invert the answer[if cycle
    // exists->can't take courses] - O(v+e), O(3v)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // convert pairs to adj list first
        List<List<Integer>> adj = buildGraph(numCourses, prerequisites);

        boolean[] vis = new boolean[numCourses];
        boolean[] pathVis = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!vis[i]) {
                if (dfsDirectedCycle(i, adj, vis, pathVis)) {
                    return !true;
                }
            }
        }

        return !false;
    }

    // if node visited again on the SAME PATH - cycle exists
    // dfs with vis and pathVis
    public boolean dfsDirectedCycle(int node, List<List<Integer>> adj, boolean[] vis, boolean[] pathVis) {
        vis[node] = true;
        pathVis[node] = true;

        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                if (dfsDirectedCycle(neighbour, adj, vis, pathVis))
                    return true;
            } else if (pathVis[neighbour]) {
                // cycle found
                return true;
            }
        }

        // remove node from the path when coming back from recursion
        // HAS to be on the same path for cycle to exist
        pathVis[node] = false;
        return false;
    }

    // u,v - v->u
    private List<List<Integer>> buildGraph(int V, int[][] prereq) {
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < prereq.length; i++) {
            int[] pair = prereq[i];

            // u comes after v - add u to v's adj list
            adj.get(pair[1]).add(pair[0]);
        }

        return adj;
    }
}
