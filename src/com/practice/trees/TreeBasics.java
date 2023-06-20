package com.practice.trees;

public class TreeBasics {
    public static void main(String[] args) {
        TreeNode root = testTree();
        System.out.println(heightTree(root));
        printLevelI(root, 0, 2);
        System.out.println();
        printLevel(root, 2);
    }

    public static TreeNode testTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    public static int nodeSum(TreeNode node) {
        if (node == null)
            return 0;

        return node.data + nodeSum(node.left) + nodeSum(node.right);
    }

    public static int levelDiffOddEven(TreeNode node) {
        if (node == null)
            return 0;

        return node.data - levelDiffOddEven(node.left) - levelDiffOddEven(node.right);
    }

    public static int noOfLeafNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        return noOfLeafNodes(node.left) + noOfLeafNodes(node.right);
    }

    // depth/height - O(n),O(h)[auxiliary space - recursion stack] | O(n) in case of
    // skewed trees
    public static int heightTree(TreeNode node) {
        if (node == null) {
            // needs to be -1 as for the leaf node h=0 - -1+1=0
            return -1;
        }

        // +1 for inc. height
        return 1 + Math.max(heightTree(node.left), heightTree(node.right));
    }

    public static void printLevelI(TreeNode node, int level, int input) {
        if (node == null)
            return;

        if (level == input) {
            System.out.print(node.data + " ");
            return;
        }

        printLevelI(node.left, level + 1, input);
        printLevelI(node.right, level + 1, input);
    }

    public static void printLevel(TreeNode node, int level) {
        if (node == null)
            return;

        // make level 0 - no. of steps to go down = provided level
        if (level == 0) {
            System.out.print(node.data + " ");
            return;
        }

        printLevel(node.left, level - 1);
        printLevel(node.right, level - 1);
    }

    // preorder traversal for both the trees - return false if data doesnt match or
    // left subtree or right subtree returns false
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // clever statement
        if (p == null || q == null)
            return p == q;

        boolean dataMatch = p.data == q.data;
        boolean leftMatch = isSameTree(p.left, q.left);
        boolean rightMatch = isSameTree(p.right, q.right);

        return dataMatch && leftMatch && rightMatch;
    }

}
