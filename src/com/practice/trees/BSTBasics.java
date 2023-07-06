package com.practice.trees;

public class BSTBasics {

    // insert at leaf node - O(logn),O(1)
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        TreeNode curr = root;

        while (curr != null) {
            if (curr.data > val) {
                // go left if you can
                if (curr.left != null)
                    curr = curr.left;
                else {
                    // attach and break
                    curr.left = new TreeNode(val);
                    break;
                }
            } else {
                // go right if you can
                if (curr.right != null)
                    curr = curr.right;
                else {
                    // attach and break
                    curr.right = new TreeNode(val);
                    break;
                }
            }
        }

        return root;
    }

    // just smaller than the key - O(logn),O(1)
    public static TreeNode floor(TreeNode root, int key) {
        TreeNode res = null;
        TreeNode curr = root;

        while (curr != null) {
            if (curr.data == key) {
                res = curr;
                break;
            } else if (curr.data < key) {
                // update ceil and go right
                res = curr;
                curr = curr.right;
            } else if (curr.data > key) {
                // go left
                curr = curr.left;
            }
        }

        return res;
    }

    // just bigger than the key - O(logn),O(1)
    public static TreeNode ceil(TreeNode root, int key) {
        TreeNode res = null;
        TreeNode curr = root;

        while (curr != null) {
            if (curr.data == key) {
                res = curr;
                break;
            } else if (curr.data > key) {
                // update ceil and go left
                res = curr;
                curr = curr.left;
            } else if (curr.data < key) {
                // go right
                curr = curr.right;
            }
        }

        return res;
    }

    // binary search - O(logn),O(logn) | O(n) in skew tree
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;

        if (root.data < val) {
            // go right
            return searchBST(root.right, val);
        } else if (root.data > val) {
            // go left
            return searchBST(root.left, val);
        } else {
            // element found
            return root;
        }
    }

    // binary search - iterative - O(logn),O(1)
    public TreeNode searchBSTIter(TreeNode root, int val) {
        while (root != null && root.data != val) {
            if (root.data < val) {
                // go right
                root = root.right;
            } else if (root.data > val) {
                // go left
                root = root.left;
            }
        }

        return root;
    }

}
