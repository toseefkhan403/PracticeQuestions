package com.practice.trees;

import java.util.ArrayList;
import java.util.List;

public class RootToNode {
    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();
        TreeNode root = TreeBasics.testTree();
        rootToNode(root, 5, result);
        System.out.println(result);
    }

    // inorder traversal - store path in ds - if false, go back and remove the last
    // element - O(n),O(h)
    public static boolean rootToNode(TreeNode node, int nodeData, List<Integer> path) {
        if (node == null) {
            // reached end - didnt find - return false
            return false;
        }

        path.add(node.data);

        if (node.data == nodeData) {
            return true;
        }

        // if any one is true - path found
        if (rootToNode(node.left, nodeData, path) || rootToNode(node.right, nodeData, path)) {
            return true;
        }

        // if both false - remove last element and return false
        path.remove(path.size() - 1);
        return false;
    }
}
