package com.practice.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        levelOrderTraversal(root);
    }

    // add to queue - remove and add node's children to the queue - go level by level
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int size = q.size();
            // size = 1->2->4->8 - iterates level by level
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.remove();
                currentLevel.add(temp.data);
                // adds current node's children to the queue - 2->4->8
                if (temp.left != null)
                    q.add(temp.left);
                if (temp.right != null)
                    q.add(temp.right);
            }
            result.add(currentLevel);
        }

        return result;
    }

    // using queue cuz of FIFO property
    public static void levelOrderTraversal(TreeNode node) {
        if (node == null)
            return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(node);

        // remove node and add its children to the queue
        while (!q.isEmpty()) {
            TreeNode temp = q.remove();
            System.out.print(temp.data + " ");
            if (temp.left != null)
                q.add(temp.left);
            if (temp.right != null)
                q.add(temp.right);
        }
    }

}
