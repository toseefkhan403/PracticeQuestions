package com.practice.trees;

import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversal {
    public static void main(String[] args) {
        TreeNode root = TreeBasics.testTree();
        System.out.println(boundaryTraversal(root));
    }

    // left nodes + leaf nodes + right nodes in reverse
    public static List<Integer> boundaryTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        result.add(root.data);
        // single node - need this check else prints root twice - leafNodes() adds it again
        if (isLeafNode(root))
            return result;

        // left boundary exculding leaves
        leftNodes(root, result);
        // leaf nodes
        leafNodes(root, result);
        // right boundary in reverse excluding the leaf
        rightNodes(root, result);

        return result;
    }

    // preorder traversal - add to result and return if leaf node
    public static void leafNodes(TreeNode node, List<Integer> result) {
        if (isLeafNode(node)) {
            result.add(node.data);
            return;
        }

        leafNodes(node.left, result);
        leafNodes(node.right, result);
    }

    // go left till you cant - then go right - add non leaf nodes only
    public static void leftNodes(TreeNode node, List<Integer> result) {
        TreeNode curr = node.left;

        while (curr != null) {
            if (!isLeafNode(curr))
                result.add(curr.data);
            if (curr.left != null)
                curr = curr.left;
            else
                curr = curr.right;
        }
    }

    // go right till you cant - then go left - add non leaf nodes only - reverse and
    // add to result
    public static void rightNodes(TreeNode node, List<Integer> result) {
        TreeNode curr = node.right;
        List<Integer> temp = new ArrayList<>();

        while (curr != null) {
            if (!isLeafNode(curr))
                temp.add(curr.data);
            if (curr.right != null)
                curr = curr.right;
            else
                curr = curr.left;
        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
    }

    // leaf node if both children null
    public static boolean isLeafNode(TreeNode curr) {
        return curr.left == null && curr.right == null;
    }

}
