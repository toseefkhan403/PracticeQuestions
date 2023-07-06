package com.practice.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagLOT {
    public static void main(String[] args) {

    }

    // level order traversal - but at every second step[keep a boolean], store list
    // in reverse order - O(n),O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> sublist = new ArrayList<>(size);

            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.remove();
                // 0 - shifts others to the right - why doesnt work with size-1-i?
                // cuz must add elements to ArrayList serially -
                // constructor sets the initial capacity, not the size - size is still 0 -
                // dynamic memory allocation - thus indexoutofbounds
                int insert = leftToRight ? i : 0;
                sublist.add(insert, temp.data);

                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            leftToRight = !leftToRight;
            result.add(sublist);
        }

        return result;
    }

    // faster
    public List<List<Integer>> zigzagLevelOrderI(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] sublist = new int[size];

            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.remove();
                int insert = leftToRight ? i : size - 1 - i;
                // use arr as sublist - faster than shifting elements in the arraylist
                sublist[insert] = temp.data;

                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            leftToRight = !leftToRight;
            List<Integer> sub = new ArrayList<>(size);
            for (int i : sublist) {
                sub.add(i);
            }
            result.add(sub);
        }

        return result;
    }

}
