package com.practice.trees;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ConstructBSTPreorder {
    // brute: for every element, search the BST and insert at the suitable position
    // - O(n^2)

    // better: need preorder and inorder - sort preorder to get inorder - then the
    // usual method - O(nlogn), O(3n)
    public TreeNode bstFromPreorderBetter(int[] preorder) {
        int n = preorder.length;
        int[] inorder = new int[n];
        for (int i = 0; i < n; i++) {
            inorder[i] = preorder[i];
        }

        Arrays.sort(inorder);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return bstBuild(inorder, 0, n - 1, preorder, 0, n - 1, map);
    }

    public TreeNode bstBuild(int[] inorder, int is, int ie, int[] preorder, int ps, int pe, Map<Integer, Integer> map) {
        if (is > ie || ps > pe) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[ps]);
        int rootIndex = map.get(node.data);
        int length = rootIndex - is;

        node.left = bstBuild(inorder, is, rootIndex - 1, preorder, ps + 1, ps + length, map);
        node.right = bstBuild(inorder, rootIndex + 1, ie, preorder, ps + length + 1, pe, map);

        return node;
    }

    // optimal: keep the upperbound for every guy - lowerbound not needed - return
    // null if breaches ub - will get attached in the right call - O(3n), O(n[aux])
    int i = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        // ub max initially
        return bstRecur(preorder, Integer.MAX_VALUE);
    }

    // standard tree builder method with index and ub
    private TreeNode bstRecur(int[] preorder, int upperbound) {
        if (i == preorder.length || preorder[i] > upperbound)
            return null;

        TreeNode node = new TreeNode(preorder[i++]);
        // left children smaller than the node - ub changed
        node.left = bstRecur(preorder, node.data);
        node.right = bstRecur(preorder, upperbound);

        return node;
    }

    // optimal: pass index by reference instead of class variable
    public TreeNode bstFromPreorderRef(int[] preorder) {
        return bstRecur(preorder, Integer.MAX_VALUE, new int[1]);
    }

    private TreeNode bstRecur(int[] preorder, int upperbound, int[] i) {
        if (i[0] == preorder.length || preorder[i[0]] > upperbound)
            return null;

        TreeNode node = new TreeNode(preorder[i[0]++]);
        node.left = bstRecur(preorder, node.data, i);
        node.right = bstRecur(preorder, upperbound, i);

        return node;
    }

}
