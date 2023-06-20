package com.practice.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeftNRightView {
    public static void main(String[] args) {

    }

    // recursion - preorder - the first node of each level
    // O(n), O(h) - except skew trees - O(n) in that case
    public static void leftView(TreeNode node, List<Integer> ds, int level) {
        if (node == null)
            return;

        // clever trick - makes sure that ds contains first node of each level
        if (level == ds.size()) {
            ds.add(node.data);
        }

        leftView(node.left, ds, level + 1);
        // for right view - right first - left later
        leftView(node.right, ds, level + 1);
    }

    // first node on each level - level order traversal
    // O(n), O(n)[all nodes of last level, O(2^h)]
    ArrayList<Integer> leftViewI(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            // size = 1->2->4->8 - iterates level by level
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.remove();
                if (i == 0) // size - 1 for right view
                    result.add(temp.data);
                // adds current node's children to the queue - 2->4->8
                if (temp.left != null)
                    q.add(temp.left);
                if (temp.right != null)
                    q.add(temp.right);
            }
        }

        return result;
    }

}
