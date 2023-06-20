package com.practice.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class VerticalOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = TreeBasics.testTree();
        System.out.println(verticalTraversal(root));
    }

    // get coordinates - do LO traversal - store in a custom map - traverse the
    // map to get the result
    // O(nlogn)[logn for PQ], O(n)
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        // store coordinates thru LO traversal in custom map
        // vertical -> level -> elements kept in sorted order[in PQ]
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        // Queue for LOT - special class to store the coordinates
        // can use DFS as well
        Queue<VOTreeNode> q = new LinkedList<>();
        q.offer(new VOTreeNode(root, 0, 0));

        while (!q.isEmpty()) {
            VOTreeNode temp = q.poll();
            TreeNode tempNode = temp.node;
            int vertical = temp.vertical;
            int level = temp.level;

            // need to add to avoid nullpointer exceptions
            if (!map.containsKey(vertical)) {
                map.put(vertical, new TreeMap<>());
            }
            if (!map.get(vertical).containsKey(level)) {
                map.get(vertical).put(level, new PriorityQueue<Integer>());
            }
            // add to the PQ
            map.get(vertical).get(level).offer(tempNode.data);

            // add children to q - LOT
            if (tempNode.left != null)
                q.offer(new VOTreeNode(tempNode.left, vertical - 1, level + 1));
            if (tempNode.right != null)
                q.offer(new VOTreeNode(tempNode.right, vertical + 1, level + 1));
        }

        // traverse map and add to result
        for (TreeMap<Integer, PriorityQueue<Integer>> level : map.values()) {
            // IMPORTANT - needs to be above pq loop as there can be multiple levels with
            // PQs for the same vertical - those needs to be merged
            result.add(new ArrayList<>()); // || temp = new ArrayList
            for (PriorityQueue<Integer> pq : level.values()) {
                // treat it like a queue - not a list
                while (!pq.isEmpty()) {
                    result.get(result.size() - 1).add(pq.poll()); // || add to temp
                }
            }
            // || add temp to result
        }

        return result;
    }

}

class VOTreeNode {
    TreeNode node;
    int vertical, level;

    public VOTreeNode(TreeNode node, int vertical, int level) {
        this.node = node;
        this.vertical = vertical;
        this.level = level;
    }

}
