package com.practice.trees;

import java.util.ArrayList;
import java.util.List;

public class BSTKthSmallestNLargest {
    // optimal: keep count to eliminate extra space - O(k),O(k)
    public int kthSmallestOpti(TreeNode root, int k) {
        int[] res = new int[1];
        // need to pass k and res by reference
        inOrderTraversal(root, new int[] { k }, res);
        return res[0];
    }

    public static void inOrderTraversal(TreeNode root, int[] k, int[] res) {
        if (root == null)
            return;

        inOrderTraversal(root.left, k, res);

        // using k as count
        if (--k[0] == 0) {
            res[0] = root.data;
            return;
        }

        inOrderTraversal(root.right, k, res);
    }

    public int kthSmallestI(TreeNode root, int k) {
        // passing Only k by reference
        return inOrderTraversal(root, new int[] { k });
    }

    // returns the kth smallest
    public static int inOrderTraversal(TreeNode root, int[] k) {
        if (root == null)
            return 0;

        int left = inOrderTraversal(root.left, k);
        if (left != 0)
            return left;

        if (--k[0] == 0)
            return root.data;

        int right = inOrderTraversal(root.right, k);
        if (right != 0)
            return right;

        return 0;
    }

    // brute: do any traversal - sort it[extra O(nlogn)] - return kth
    // better: do inorder traversal - already sorted - so return the kth element
    // O(n),O(n)
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrderTraversal(root, list);

        // 1-indexed
        return list.get(k - 1);
    }

    public static void inOrderTraversal(TreeNode root, List<Integer> res) {
        if (root == null)
            return;

        inOrderTraversal(root.left, res);
        res.add(root.data);
        inOrderTraversal(root.right, res);
    }

    /*
     * K-th Largest in BST
     * (n-k)th smallest = kth largest
     * O(2n-k),O(h+(n-k))
     */
    public static int KLargestElement(TreeNode node, int k) {
        int[] l = new int[1];
        // O(n) extra time
        calcLength(node, l);
        k = l[0] - k;

        // find kth smallest now
        int[] res = new int[1];
        kthSmallest(node, new int[] { k }, res);
        return res[0];
    }

    // inorder - sorted - stop and return when kth smallest found
    public static void kthSmallest(TreeNode node, int[] k, int[] res) {
        if (node == null)
            return;

        kthSmallest(node.left, k, res);

        // using k as count
        if (k[0]-- == 0) {
            res[0] = node.data;
            return;
        }

        kthSmallest(node.right, k, res);
    }

    public static void calcLength(TreeNode node, int[] l) {
        if (node == null)
            return;

        calcLength(node.left, l);
        l[0]++;
        calcLength(node.right, l);
    }

}
