package com.practice.trees;

public class BSTDeleteNode {
    // O(h[finding right most node - can go till height of the tree]),O(h[aux
    // space])
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        // delete the root
        if (root.data == key)
            return deleteHelper(root);

        TreeNode curr = root;
        // 1. Search the node - without parent pointer(curr's left or right == key)
        while (curr != null) {
            if (curr.data > key) {
                if (curr.left != null && curr.left.data == key) {
                    curr.left = deleteHelper(curr.left);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {
                if (curr.right != null && curr.right.data == key) {
                    curr.right = deleteHelper(curr.right);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }

        return root;
    }

    // does the actual node deletion - return the node to be attached
    public static TreeNode deleteHelper(TreeNode root) {
        // 2a. if one branch is null, attach to the other one
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            // 2b. attach right subtree to last right of left subtree
            TreeNode lastRight = findLastRight(root.left);
            lastRight.right = root.right;
            return root.left;
        }
    }

    // recursive method to find the last right node
    public static TreeNode findLastRight(TreeNode node) {
        if (node.right == null)
            return node;

        return findLastRight(node.right);
    }

    // find the node - 3 cases - also handle deleting the root node
    // O(h[finding right most node - can go till height of the tree]),O(1)
    public static TreeNode deleteNodeI(TreeNode root, int key) {
        if (root == null)
            return null;

        // find the node
        TreeNode curr = root;
        // with parent pointer
        TreeNode par = root;
        boolean isFound = false;

        while (curr != null) {
            if (curr.data == key) {
                isFound = true;
                break;
            }

            par = curr;

            if (curr.data < key) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        if (!isFound)
            return root;
        if (root == curr)
            return deleteRoot(root);

        // 3 cases
        // leaf node
        if (curr.left == null && curr.right == null) {
            if (par.left == curr) {
                par.left = null;
            } else {
                par.right = null;
            }
        }
        // one child non-empty
        else if (curr.left == null || curr.right == null) {
            if (par.left == curr) {
                par.left = curr.left == null ? curr.right : curr.left;
            } else {
                par.right = curr.left == null ? curr.right : curr.left;
            }
        }
        // both left and right exist
        else {
            // join right subtree to the right of the rightmost[biggest] node of the left
            // subtree
            TreeNode rt = curr.left;
            while (rt.right != null) {
                rt = rt.right;
            }

            rt.right = curr.right;

            // delete the node
            if (par.left == curr) {
                par.left = curr.left;
            } else {
                par.right = curr.left;
            }
        }

        return root;
    }

    public static TreeNode deleteRoot(TreeNode curr) {
        // 3 cases
        // leaf node
        if (curr.left == null && curr.right == null) {
            return null;
        }
        // one child non-empty
        else if (curr.left == null || curr.right == null) {
            if (curr.left == null) {
                return curr.right;
            } else {
                return curr.left;
            }
        }
        // both left and right exist
        else {
            // join right subtree to the right of the rightmost[biggest] node of the left
            // subtree
            TreeNode rt = curr.left;
            while (rt.right != null) {
                rt = rt.right;
            }

            rt.right = curr.right;

            return curr.left;
        }
    }

}
