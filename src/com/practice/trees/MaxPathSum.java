package com.practice.trees;

public class MaxPathSum {
    public static void main(String[] args) {

    }

    public int maxPathSum(TreeNode node) {
        int[] maxi = new int[1];
        // initialize with min val as single node can be -ve
        maxi[0] = Integer.MIN_VALUE;
        maxPathSumRecur(node, maxi);
        return maxi[0];
    }

    // calc max path sum in the height method - O(n),O(n) | O(h) avg
    public int maxPathSumRecur(TreeNode node, int[] maxi) {
        if (node == null)
            return 0;

        int leftSum = Math.max(0, maxPathSumRecur(node.left, maxi));
        // dont take -ve path sum
        int rightSum = Math.max(0, maxPathSumRecur(node.right, maxi));
        maxi[0] = Math.max(maxi[0], node.data + leftSum + rightSum);

        // can take only one path at a time - otherwise double trace - take one with the
        // max sum
        return node.data + Math.max(leftSum, rightSum);
    }

}
