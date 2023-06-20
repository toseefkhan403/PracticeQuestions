package com.practice.trees;

public class SymmetricTree {
    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        return isSymRecur(root.left, root.right);
    }

    // preorder traversal - check left side with right and vice versa - mirror image
    // O(n),O(n)[skew tree - otherwise O(h) auxiliary space]
    public boolean isSymRecur(TreeNode p, TreeNode q) {
        // same trick as tree same or not problem
        if (p == null || q == null)
            return p == q;

        if (p.data != q.data)
            return false;

        return isSymRecur(p.left, q.right) && isSymRecur(p.right, q.left);
    }
}
