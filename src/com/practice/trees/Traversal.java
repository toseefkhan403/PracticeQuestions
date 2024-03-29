package com.practice.trees;

import java.util.ArrayList;
import java.util.List;
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
        // preOrderTraversal(root);
        // System.out.println();
        inOrderIterative(root);
        System.out.println();
        // inOrderTraversal(root);
        // System.out.println();
        postOrderTraversal(root);
        System.out.println();
        // postOrderIterative1Stack(root);
        allInOneTraversal(root);
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

    // remember the algorithm - use node,num pair - diff step for num = 1,2,3 -
    // O(3n), O(4n)
    public static void allInOneTraversal(TreeNode node) {
        if (node == null)
            return;
        Stack<Pair> stack = new Stack<>();
        // push new nodes with num = 1
        stack.push(new Pair(node, 1));
        // lists needed - cant print directly in the loop
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        while (!stack.isEmpty()) {
            Pair p = stack.pop();

            // 1 - add to preorder - push num+1 - push left child
            if (p.num == 1) {
                preorder.add(p.node.data);
                p.num++;
                // push same node with num+1
                stack.push(p);

                // push new nodes with num = 1
                if (p.node.left != null)
                    stack.push(new Pair(p.node.left, 1));
            } else if (p.num == 2) {
                // 2 - add to inorder - push num+1 - push right child
                inorder.add(p.node.data);
                p.num++;
                // push same node with num+1
                stack.push(p);

                if (p.node.right != null)
                    stack.push(new Pair(p.node.right, 1));
            } else if (p.num == 3) {
                // 3 - just add to postorder - dont push again
                postorder.add(p.node.data);
            }
        }

        System.out.println();
        for (int it : preorder) {
            System.out.print(it + " ");
        }
        System.out.println();

        for (int it : inorder) {
            System.out.print(it + " ");
        }
        System.out.println();

        for (int it : postorder) {
            System.out.print(it + " ");
        }

    }

}
