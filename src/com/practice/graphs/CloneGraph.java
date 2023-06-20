package com.practice.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// O(v+2e), O(v+v[map])
public class CloneGraph {
    // map old nodes to new ones - bfs
    public Node cloneGraphBFS(Node node) {
        if (node == null) {
            return null;
        }

        HashMap<Node, Node> mp = new HashMap<>();

        Node first = new Node(node.val);
        mp.put(node, first);

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // dont create new node here - create in neighbours loop

            for (Node adj : curr.neighbors) {
                if (!mp.containsKey(adj)) {
                    // add to map and q
                    mp.put(adj, new Node(adj.val));
                    queue.offer(adj);
                }

                // add the links for ALL the neighbours
                mp.get(curr).neighbors.add(mp.get(adj));
            }
        }

        return first;
    }

    // map old nodes to new ones - add links using dfs
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        // no neighbours
        if (node.neighbors.isEmpty()) {
            return new Node(node.val);
        }

        HashMap<Node, Node> map = new HashMap<>();
        return dfsMap(node, map);
    }

    // create the copy, put in the map and add neighbours
    public Node dfsMap(Node node, HashMap<Node, Node> map) {
        Node copy = new Node(node.val);
        map.put(node, copy);

        // add links to the neighbors
        for (Node it : node.neighbors) {
            if (map.get(it) == null) {
                copy.neighbors.add(dfsMap(it, map));
            } else {
                copy.neighbors.add(map.get(it));
            }
        }

        return copy;
    }

    // map old nodes to new ones
    public Node cloneGraphI(Node node) {
        if (node == null)
            return null;

        HashMap<Node, Node> map = new HashMap<>();
        Node newNode = dfsMap(node, map);

        return newNode;
    }

    public Node dfsMapI(Node node, HashMap<Node, Node> map) {
        if (map.get(node) == null) {
            Node copy = new Node(node.val);
            map.put(node, copy);

            // add links to the neighbors
            for (Node it : node.neighbors) {
                if (map.get(it) == null) {
                    copy.neighbors.add(dfsMapI(it, map));
                } else {
                    copy.neighbors.add(map.get(it));
                }
            }

            return copy;
        } else {
            return map.get(node);
        }
    }

}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}