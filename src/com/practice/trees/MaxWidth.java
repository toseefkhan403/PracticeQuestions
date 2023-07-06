package com.practice.trees;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidth {
    public static void main(String[] args) {
        TreeNode root = TreeBasics.testTree();
        System.out.println(widthOfBinaryTree(root));
    }
    
    // LOT - index the children using Pair - do last-first+1 for the width
    // of the level - left side stays on 0 - right increases to give the width - O(n),O(n)
    public static int widthOfBinaryTree(TreeNode root) {
        int result = 0;
        if (root == null)
            return result;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            int size = q.size();
            int first = 0;
            int last = 0;
            // first index of the level is min - to make the left num start from 0
            int lmin = q.peek().num;
            for (int i = 0; i < size; i++) {
                Pair temp = q.poll();
                TreeNode tNode = temp.node;
                // int overflow can happen - therefore subtract from the min num of the level
                int tNum = temp.num - lmin;
                if (i == 0)
                    first = tNum;
                if (i == size - 1)
                    last = tNum;

                // formula for indexing nodes - 2*parent+1 , +2
                if (tNode.left != null)
                    q.offer(new Pair(tNode.left, 2 * tNum + 1));
                if (tNode.right != null)
                    q.offer(new Pair(tNode.right, 2 * tNum + 2));
            }
            // max width
            result = Math.max(result, last - first + 1);
        }

        return result;
    }
}

class Pair {
    TreeNode node;
    int num;

    public Pair(TreeNode node, int num) {
        this.node = node;
        this.num = num;
    }

}
