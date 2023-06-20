package com.practice.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopNBottomView {
    public static void main(String[] args) {
        TreeNode root = TreeBasics.testTree();
        System.out.println(topView(root));
        System.out.println(bottomView(root));
    }

    // first node in every vertical order traversal - O(n),O(n)
    // can use DFS - but then have to keep track of level also - as in DFS nodes are
    // not in order
    static ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;

        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(root, 0));

        // vertical, node data
        Map<Integer, Integer> map = new TreeMap<>();

        while (!q.isEmpty()) {
            Tuple temp = q.poll();
            TreeNode tNode = temp.node;
            int vertical = temp.vertical;

            // first entries only
            if (!map.containsKey(vertical)) {
                map.put(vertical, tNode.data);
            }

            if (tNode.left != null)
                q.offer(new Tuple(tNode.left, vertical - 1));
            if (tNode.right != null)
                q.offer(new Tuple(tNode.right, vertical + 1));
        }

        // map the map to result
        for (int i : map.values()) {
            result.add(i);
        }

        return result;
    }

    // last node in every vertical order traversal - O(n),O(n)
    // can use DFS - but then have to keep track of level also - as in DFS nodes are
    // not in order
    static ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;

        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(root, 0));

        // vertical, data
        Map<Integer, Integer> map = new TreeMap<>();

        while (!q.isEmpty()) {
            Tuple temp = q.poll();
            TreeNode tNode = temp.node;
            int vertical = temp.vertical;

            // need last entry - keep overriding
            map.put(vertical, tNode.data);

            if (tNode.left != null)
                q.offer(new Tuple(tNode.left, vertical - 1));
            if (tNode.right != null)
                q.offer(new Tuple(tNode.right, vertical + 1));
        }

        // map the map to result
        for (int i : map.values()) {
            result.add(i);
        }

        return result;
    }
}

class Tuple {
    TreeNode node;
    int vertical;
    // dont need level here

    public Tuple(TreeNode node, int vertical) {
        this.node = node;
        this.vertical = vertical;
    }
}
