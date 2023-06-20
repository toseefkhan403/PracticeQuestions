package com.practice.trees;

import java.util.ArrayList;
import java.util.List;

public class LCA {
    public static void main(String[] args) {
        TreeNode root = TreeBasics.testTree();
        System.out.println(lowestCommonAncestor(root, root.left.left, root.left.right).data);
    }

    // return nodes[p and q] - the first element which gets p and q from left and
    // right = LCA - O(n),O(n)[skew tree]
    public static TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        // base case
        if (node == null || node == p || node == q)
            return node;

        TreeNode left = lowestCommonAncestor(node.left, p, q);
        TreeNode right = lowestCommonAncestor(node.right, p, q);

        // return the not null element
        if (left == null)
            return right;
        else if (right == null)
            return left;
        else {
            // both are not null - found the LCA
            return node;
        }
    }

    // return nodes[p and q] - the first element which gets p and q from left and
    // right = LCA
    public static TreeNode lowestCommonAncestorI(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null)
            return null;

        if (node == p || node == q)
            return node;

        TreeNode l = lowestCommonAncestorI(node.left, p, q);
        TreeNode r = lowestCommonAncestorI(node.right, p, q);

        // got the lca
        if (l != null && r != null)
            return node;
        // return the value which is not null
        if (l != null)
            return l;
        if (r != null)
            return r;

        // both null
        return null;
    }

    // get paths - match em - first common occurence from the end = ans
    // uses extra TC n SC - O(2n+h^2 for path iteration), O(n+2h for storing paths)
    public static TreeNode lowestCommonAncestorBrute(TreeNode root, TreeNode p, TreeNode q) {
        // treenode path - cuz we need to return a treenode
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();

        pathToNode(root, p, pathP);
        pathToNode(root, q, pathQ);

        for (int i = pathP.size() - 1; i >= 0; i--) {
            for (int j = pathQ.size() - 1; j >= 0; j--) {
                if (pathP.get(i) == pathQ.get(j)) {
                    return pathP.get(i);
                }
            }
        }

        return null;
    }

    public static boolean pathToNode(TreeNode node, TreeNode nodeData, List<TreeNode> path) {
        if (node == null) {
            // reached end - didnt find - return false
            return false;
        }

        path.add(node);

        if (node == nodeData) {
            return true;
        }

        // if any one is true - path found
        if (pathToNode(node.left, nodeData, path) || pathToNode(node.right, nodeData, path)) {
            return true;
        }

        // if both false - remove last element and return false
        path.remove(path.size() - 1);
        return false;
    }

}
