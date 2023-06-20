package com.practice.trees;

public class BSTLCA {
    // brute: normal BT method

    // check if both on left or right - if not -> LCA is found
    // O(h),O(h[aux space])
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        int curr = root.data;
        // both exist on right
        if (curr < p.data && curr < q.data) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // both exist on left
        if (curr > p.data && curr > q.data) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // one exist on left and one on right - or root is p or q - found the LCA
        return root;
    }

}
