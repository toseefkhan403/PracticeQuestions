package com.practice.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeNDeserialize {
    // Encodes a tree to a single string
    public String serialize(TreeNode root) {
        // LOT - O(n),O(n)
        String res = "";
        if (root == null)
            return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();

            if (temp == null) {
                res += "# ";
                // avoids nullpointer exception
                continue;
            }

            res += temp.data + " ";
            // CAN insert null values - will take care of it while polling
            q.offer(temp.left);
            q.offer(temp.right);
        }

        return res;
    }

    // Decodes your encoded data to tree - O(n),O(n)
    public TreeNode deserialize(String data) {
        System.out.println(data);
        if (data.isEmpty())
            return null;

        // split on the space
        String[] str = data.split(" ");

        // has to be TreeNode Q - need to attach left and right pointers
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        q.offer(root);

        // LOT - attach left and right one by one
        for (int i = 1; i < str.length; i++) {
            TreeNode parent = q.poll();

            if (!str[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(str[i]));
                parent.left = left;
                q.offer(left);
            }

            // IMPORTANT - left right occur together - next element in the array is the
            // right child - hence ++i
            if (!str[++i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(str[i]));
                parent.right = right;
                q.offer(right);
            }
        }

        return root;
    }

    // works ONLY for full BT
    // nulls should also have children till the full height
    private TreeNode constructTree(String str, int i) {
        TreeNode node = null;
        if (i < str.length()) {
            if (str.charAt(i) != '#') {
                node = new TreeNode(Character.getNumericValue(str.charAt(i)));
                node.left = constructTree(str, 2 * i + 1);
                node.right = constructTree(str, 2 * i + 2);
            }
        }

        return node;
    }

}
