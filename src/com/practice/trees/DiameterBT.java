package com.practice.trees;

public class DiameterBT {
    public static void main(String[] args) {

    }

    public int diameterOfBinaryTree(TreeNode root) {
        // need to do this as cant pass int pointers to methods in java - but for
        // arrays we can
        int[] maxi = new int[1];
        diameterRecurOpti(root, maxi);
        return maxi[0];
    }

    // can do in the height method itself - O(n),O(n)
    // get height - keep track of max path [lh+rh]
    public int diameterRecurOpti(TreeNode node, int[] maxi) {
        if (node == null)
            return 0;

        int lh = diameterRecurOpti(node.left, maxi);
        int rh = diameterRecurOpti(node.right, maxi);
        maxi[0] = Math.max(maxi[0], lh + rh);

        return 1 + Math.max(lh, rh);
    }

    // go to every node check max path - O(n^2),O(n)
    public static void diameterRecur(TreeNode node, int[] maxi) {
        if (node == null)
            return;

        int lh = heightTree(node.left);
        int rh = heightTree(node.right);

        maxi[0] = Math.max(maxi[0], lh + rh);

        diameterRecur(node.left, maxi);
        diameterRecur(node.right, maxi);
    }

    private static int heightTree(TreeNode node) {
        if (node == null)
            return 0;

        return 1 + Math.max(heightTree(node.left), heightTree(node.right));
    }

}
