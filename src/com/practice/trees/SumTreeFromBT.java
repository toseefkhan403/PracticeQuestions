package com.practice.trees;

public class SumTreeFromBT {
    // calc left n right subtree sum - assign it to node - BUT return diff value(along
    // with the node value) - else everything will become 0 - O(n), O(h[n for skew tree])
    public static int sumTree(TreeNode node) {
        if (node == null)
            return 0;

        int left = sumTree(node.left);
        int right = sumTree(node.right);

        // keep the old value
        int val = node.data + left + right;
        node.data = left + right;

        return val;
    }

}
