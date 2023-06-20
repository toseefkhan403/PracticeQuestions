package com.practice.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NodesAtKDistanceLeaf {
    // store the path in preorder - when reaches leaf, put size-kth node in set(to
    // avoid duplicates) - O(n), O(3n)
    int printKDistantfromLeaf(TreeNode root, int k) {
        // type is treenode to handle duplicate data in the tree
        List<TreeNode> path = new ArrayList<>();
        Set<TreeNode> set = new HashSet<>();

        kDistantFromLeafUtil(root, k, path, set);
        // path size will become 0 after the recursion

        return set.size();
    }

    private void kDistantFromLeafUtil(TreeNode node, int k, List<TreeNode> path, Set<TreeNode> set) {
        if (node == null)
            return;

        // Do NOT add leaf node to the path
        if (isLeaf(node) && path.size() - k >= 0) {
            set.add(path.get(path.size() - k));
            return;
        }

        // IMPORTANT - add to path AFTER checking for leaf
        path.add(node);
        kDistantFromLeafUtil(node.left, k, path, set);
        kDistantFromLeafUtil(node.right, k, path, set);
        // IMPORTANT - remove the node from the current path when you come back from
        // recursion
        path.remove(path.size() - 1);
    }

    // add parent pointers - store leaf nodes
    // O(n+(n/2)*k), O(4n)
    int printKDistantfromLeafI(TreeNode root, int k) {
        List<TreeNode> leafTreeNodes = new ArrayList<>();
        Map<TreeNode, TreeNode> parent_track = addParents(root, leafTreeNodes);
        Set<TreeNode> set = new HashSet<>();
        // go k times up for each leaf - and add to set
        for (TreeNode leaf : leafTreeNodes) {
            goKUp(leaf, k, parent_track, set);
        }

        return set.size();
    }

    void goKUp(TreeNode node, int k, Map<TreeNode, TreeNode> parent_track, Set<TreeNode> set) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);

        int current_level = 0;

        while (!q.isEmpty()) {
            if (current_level == k) {
                set.add(q.peek());
                break;
            }

            TreeNode temp = q.poll();

            if (parent_track.get(temp) != null) {
                q.offer(parent_track.get(temp));
                current_level++;
            }
        }
    }

    // LO traversal to add parents and get leaf nodes
    Map<TreeNode, TreeNode> addParents(TreeNode node, List<TreeNode> leafTreeNodes) {
        Map<TreeNode, TreeNode> res = new HashMap<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (isLeaf(temp))
                leafTreeNodes.add(temp);

            if (temp.left != null) {
                res.put(temp.left, temp);
                q.offer(temp.left);
            }
            if (temp.right != null) {
                res.put(temp.right, temp);
                q.offer(temp.right);
            }
        }

        return res;
    }

    boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

}
