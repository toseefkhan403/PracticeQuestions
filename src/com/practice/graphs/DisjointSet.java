package com.practice.graphs;

import java.util.ArrayList;
import java.util.List;

// to find if two nodes are in the same component or not - used in Dynamic[changing] graphs - O(4*alpha[nearly constant]), O(2V)
public class DisjointSet {
    List<Integer> parent = new ArrayList<>();
    List<Integer> rank = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int V) {
        // works for both 0 and 1-based indexing
        for (int i = 0; i < V + 1; i++) {
            // init parent with itself
            parent.add(i);
            rank.add(0);
            size.add(1);
        }
    }

    // 1. find the ult parents
    // 2. find rank/size of the ult parents
    // 3. connect smaller to larger - ELSE rank would inc and path compression would
    // take longer
    public void unionByRank(int u, int v) {
        // dealing with ult parents only - not u or v
        int upar = findUParent(u);
        int vpar = findUParent(v);

        // already connected
        if (upar == vpar)
            return;

        if (rank.get(upar) < rank.get(vpar)) {
            // attach upar to vpar
            parent.set(upar, vpar);
        } else if (rank.get(upar) > rank.get(vpar)) {
            parent.set(vpar, upar);
        } else {
            // attach to anyone and inc rank
            // inc rank ONLY when ranks of ult parents are equal
            parent.set(upar, vpar);
            // attached to vpar - inc its rank
            rank.set(vpar, rank.get(vpar) + 1);
        }
    }

    // 1. find the ult parents
    // 2. find rank/size of the ult parents
    // 3. connect smaller to larger - ELSE path compression would take longer
    public void unionBySize(int u, int v) {
        // dealing with ult parents only - not u or v
        int upar = findUParent(u);
        int vpar = findUParent(v);

        // already connected
        if (upar == vpar)
            return;

        // inc size whenever attached - more intuitive as rank gets distorted due to
        // path compression
        if (size.get(upar) < size.get(vpar)) {
            // attach upar to vpar
            parent.set(upar, vpar);
            // inc vpar size
            size.set(vpar, size.get(vpar) + size.get(upar));
        } else {
            parent.set(vpar, upar);
            size.set(upar, size.get(upar) + size.get(vpar));
        }
    }

    // find the ultimate parent - path compression as well - attaches every child to
    // the ultimate parent(to make it faster from logN)
    public int findUParent(int node) {
        if (node == parent.get(node)) {
            return node;
        }

        int ult = findUParent(parent.get(node));
        parent.set(node, ult);
        return parent.get(node);
    }

}
