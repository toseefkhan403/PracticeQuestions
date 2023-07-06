package com.practice.trees;

import java.util.LinkedList;
import java.util.Queue;

public class NextRightNode {
    // simple BFS - return the next right node - O(n), O(n)
    TreeNode nextRightI(TreeNode root, int key) {
        if (root == null)
            return new TreeNode(-1);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        boolean isNext = false;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                if (isNext)
                    return temp;
                // if last guy on the level -> no next node
                if (temp.data == key && i != size - 1)
                    isNext = true;

                if (temp.left != null)
                    q.offer(temp.left);
                if (temp.right != null)
                    q.offer(temp.right);
            }
        }

        return new TreeNode(-1);
    }

    // doesnt traverse ahead if target is found - return ans or null there only
    TreeNode nextRight(TreeNode root, int key) {
        if (root == null)
            return new TreeNode(-1);

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();

                if (temp.data == key) {
                    // if last guy on the level -> no next node
                    if (i == size - 1)
                        return new TreeNode(-1);
                    else
                        // next node in the queue = next right node
                        return q.peek();
                }

                if (temp.left != null)
                    q.offer(temp.left);
                if (temp.right != null)
                    q.offer(temp.right);
            }
        }

        // target not found
        return new TreeNode(-1);
    }

}
