package com.practice.trees;

public class SortedListToBST {
    // similar to construct BT from traversals - O(n), O(n)
    public TreeNode sortedArrayToBST(int[] nums) {
        return bstBuild(nums, 0, nums.length - 1);
    }

    // middle will be the root - left elements = left subtree, right elements = right subtree
    public TreeNode bstBuild(int[] nums, int is, int ie) {
        if (is > ie) {
            return null;
        }

        int rootIndex = (is + ie) / 2;
        TreeNode node = new TreeNode(nums[rootIndex]);
        node.left = bstBuild(nums, is, rootIndex - 1);
        node.right = bstBuild(nums, rootIndex + 1, ie);

        return node;
    }

}
