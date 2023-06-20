package com.practice.trees;

public class MinDistance2Nodes {
    // brute: same approach as nodes at k distance - add parent pointers - keep
    // going up,left and right
    // and inc k - when found, k is the ans - O(2n),O(4n)

    // optimal: find LCA - min dist = dist(lca,a) + dist(lca,b) or dist(root,a) +
    // dist(root,b) - 2*dist(root,lca) - O(2n), O(n[aux space])
    int findDist(TreeNode root, int a, int b) {
        TreeNode lca = findLCA(root, a, b);
        int dist = findLevelFromLCA(lca, a, 0) + findLevelFromLCA(lca, b, 0);
        return dist;
    }

    // use level from lca to find distance
    private int findLevelFromLCA(TreeNode root, int a, int level) {
        if (root == null)
            return -1;

        if (root.data == a)
            return level;

        int left = findLevelFromLCA(root.left, a, level + 1);
        if (left != -1)
            return left;

        // no need for right calls if guy found in the left calls
        int right = findLevelFromLCA(root.right, a, level + 1);

        // can be -1 or level
        return right;
    }

    private TreeNode findLCA(TreeNode node, int a, int b) {
        if (node == null || node.data == a || node.data == b)
            return node;

        TreeNode left = findLCA(node.left, a, b);
        TreeNode right = findLCA(node.right, a, b);

        if (left != null && right != null) {
            // found the lca
            return node;
        }

        // return the non null guy
        if (left != null)
            return left;
        if (right != null)
            return right;

        return null;
    }
}
