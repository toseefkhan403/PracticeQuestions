package com.practice.trees;

import java.util.*;

public class ConstructBT {
    public static void main(String[] args) {

    }

    // O(n),O(n)
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // inorder -> index - reduce search time
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return bTreeHelper(0, preorder.length - 1, preorder, 0, inorder.length - 1, inorder, map);
    }

    // take an example and get indices from there
    // for inorder - start to rootIndex -> left subtree and rootIndex to end ->
    // right subtree
    // thus inorder is needed to construct a unique BT
    public TreeNode bTreeHelper(int ps, int pe, int[] preorder, int is, int ie, int[] inorder,
            Map<Integer, Integer> map) {
        if (is > ie || ps > pe)
            return null;

        // first node of the preorder = root
        TreeNode root = new TreeNode(preorder[ps]);
        int rootIndex = map.get(preorder[ps]);
        int leftLength = rootIndex - is;

        root.left = bTreeHelper(ps + 1, ps + leftLength, preorder, is, rootIndex - 1, inorder, map);
        root.right = bTreeHelper(ps + leftLength + 1, pe, preorder, rootIndex + 1, ie, inorder, map);

        return root;
    }

    // O(n),O(n)
    public TreeNode buildTreePost(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return bTreePost(0, inorder.length - 1, inorder, 0, postorder.length - 1, postorder, map);
    }

    // take an example and get indices from there
    // for inorder o- start to rotIndex -> left subtree and rootIndex to end ->
    // right subtree
    // thus inorder is needed to construct a unique BT
    private TreeNode bTreePost(int is, int ie, int[] inorder, int ps, int pe, int[] postorder,
            Map<Integer, Integer> map) {
        if (is > ie || ps > pe)
            return null;

        // last node of preorder = root
        TreeNode root = new TreeNode(postorder[pe]);
        int rootIndex = map.get(root.data);
        int leftLength = rootIndex - is;

        root.left = bTreePost(is, rootIndex - 1, inorder, ps, ps + leftLength - 1, postorder, map);
        root.right = bTreePost(rootIndex + 1, ie, inorder, ps + leftLength, pe - 1, postorder, map);

        return root;
    }

}
