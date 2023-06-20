package com.practice.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule2 {
    // return topo sort for DAG - can't do if cycle exists - basically detect graph
    // cycle using BFS - O(v+e), O(3v)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // convert pairs to adj list first
        List<List<Integer>> adj = buildGraph(numCourses, prerequisites);

        int[] indegree = new int[numCourses];

        // fill indegree(no of incoming nodes) - ++ when found in adjList
        for (int i = 0; i < numCourses; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        // add all the 0 indegree to the queue
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] topo = new int[numCourses];
        int index = 0;

        // dec the indegree of neighbours - if 0 -> add to the queue
        while (!q.isEmpty()) {
            int temp = q.poll();
            topo[index++] = temp;

            // remove temp from the indegree[from its neighbours]
            for (int it : adj.get(temp)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.offer(it);
                }
            }
        }

        // topo successful
        if (index == numCourses) {
            // reverse(topo);
            return topo;
        } else {
            // cycle exists - impossible to take all courses
            return new int[] {};
        }
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
