package com.practice.trees;

public class ValidateBST {
    public static void main(String[] args) {

    }

    public boolean isValidBST(TreeNode root) {
        // long values due to the constraints of leetcode
        return isValidBSTRecur(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // give lower and upper bounds - every node should be in the range
    // DFS - O(n),O(h)
    private boolean isValidBSTRecur(TreeNode root, long lb, long ub) {
        if (root == null)
            return true;

        if (lb < root.data && ub > root.data) {
            boolean left = isValidBSTRecur(root.left, lb, root.data);
            boolean right = isValidBSTRecur(root.right, root.data, ub);
            return left && right;
        }

        return false;
    }

}