package com.practice.trees;

import java.util.Stack;

public class Traversal {
    // DFS - O(n), O(h) | O(n) for skew trees
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        preOrderIterative(root);
        System.out.println();
        preOrderTraversal(root);
        System.out.println();
        inOrderIterative(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);
        System.out.println();
        postOrderIterative1Stack(root);
    }

    // DFS - preorder, inorder, postorder - recursion
    // preOrder - DLR
    public static void preOrderTraversal(TreeNode node) {
        if (node == null)
            return;

        System.out.print(node.data + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    // inOrder - LDR
    public static void inOrderTraversal(TreeNode node) {
        if (node == null)
            return;

        inOrderTraversal(node.left);
        System.out.print(node.data + " ");
        inOrderTraversal(node.right);
    }

    // postOrder - LRD
    public static void postOrderTraversal(TreeNode node) {
        if (node == null)
            return;

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.data + " ");
    }

    // BFS - queue
    // DFS - stack

    // DFS preorder iterative - imitate recursion with a stack
    // pop & print - push right then left
    public static void preOrderIterative(TreeNode node) {
        if (node == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        // DLR
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            System.out.print(temp.data + " ");
            // add right first and left later so that left gets popped first - DLR
            if (temp.right != null)
                stack.push(temp.right);
            if (temp.left != null)
                stack.push(temp.left);
        }
    }

    // DFS inorder iterative - imitate recursion with a stack
    // go left till you cant - then pop, print & go right - break if stack is empty
    public static void inOrderIterative(TreeNode node) {
        if (node == null)
            return;
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.isEmpty())
                    break;
                // imitates returning back to the node
                TreeNode temp = stack.pop();
                System.out.print(temp.data + " ");
                // no need to push it here
                node = temp.right;
            }
        }
    }

    // similar to preorder - pop & store in stack2 - push left then right - stack2
    // needed cuz ans is reversed
    public static void postOrderIterative2Stack(TreeNode node) {
        if (node == null)
            return;
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.push(node);
        Stack<Integer> stack2 = new Stack<>();

        while (!stack1.isEmpty()) {
            TreeNode temp = stack1.pop();
            // store in stack 2
            stack2.push(temp.data);

            if (temp.left != null)
                stack1.push(temp.left);
            if (temp.right != null)
                stack1.push(temp.right);
        }

        // pop stack2 in the end
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop() + " ");
        }
    }

    // LRD - 1 stack iterative
    public static void postOrderIterative1Stack(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = node;
        if (node == null)
            return;

        // for the first iteration
        do {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                // dont pop out - go to its right instead
                TreeNode temp = stack.peek().right;
                if (temp == null) {
                    // no children
                    temp = stack.pop();
                    System.out.print(temp.data + " ");
                    // keep popping till right branch is finished
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        System.out.print(temp.data + " ");
                    }
                } else {
                    curr = temp;
                }
            }
        } while (!stack.isEmpty());
    }

}
