package com.practice.trees;

public class BalancedBT {
    public static void main(String[] args) {
        TreeNode root = TreeBasics.testTree();
        System.out.println(isBalancedOpti(root) != -1);
    }

    // check for every node recursively that its left-right<=1 - O(n^2),O(n)
    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        int lh = heightTree(root.left);
        int rh = heightTree(root.right);

        if (Math.abs(lh - rh) > 1)
            return false;

        // check for every node - recursively
        boolean left = isBalanced(root.left);
        boolean right = isBalanced(root.right);

        // not balanced if any node is not balanced
        if (!left || !right)
            return false;

        return true;
    }

    public static int heightTree(TreeNode node) {
        if (node == null)
            return 0;

        return 1 + Math.max(heightTree(node.left), heightTree(node.right));
    }

    // can check in the heights method itself - as it goes thru every node already - O(n),O(n)
    public static int isBalancedOpti(TreeNode node) {
        if (node == null)
            return 0;

        int lh = isBalancedOpti(node.left);
        // check early - so we dont have to check the right tree if not balanced
        if (lh == -1)
            return -1;
        int rh = isBalancedOpti(node.right);
        if (rh == -1)
            return -1;

        // trick - return -1 if not balanced - else return height of the tree
        if (Math.abs(lh - rh) > 1)
            return -1;

        return 1 + Math.max(lh, rh);
    }

}
