package com.practice.trees;

import java.util.*;

public class NodesAtKDistance {
    public static void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int k = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        TreeNode root = constructTree(arr, 0);
        nodesAtK(root, k);
    }

    // need parent track and visited track - at every node, go up left right k times
    // if it is not already visited - O(2n),O(4n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        Map<TreeNode, TreeNode> parent_track = addParents(root);
        Map<TreeNode, Boolean> visited = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        // start from the target
        q.offer(target);
        // IMPORTANT - mark target as visited
        visited.put(target, true);

        int curr_level = 0;

        // second BFS to go upto K nodes
        while (!q.isEmpty()) {
            int size = q.size();
            if (curr_level == k)
                break;
            curr_level++;

            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();

                // check and go left
                if (temp.left != null && visited.get(temp.left) == null) {
                    q.offer(temp.left);
                    visited.put(temp.left, true);
                }

                // check and go right
                if (temp.right != null && visited.get(temp.right) == null) {
                    q.offer(temp.right);
                    visited.put(temp.right, true);
                }

                // check and go up
                TreeNode up = parent_track.get(temp);
                if (up != null && visited.get(up) == null) {
                    q.offer(up);
                    visited.put(up, true);
                }
            }
        }

        // nodes left in the queue -> answer
        while (!q.isEmpty()) {
            result.add(q.poll().data);
        }

        return result;
    }

    // first BFS to mark the parents
    private Map<TreeNode, TreeNode> addParents(TreeNode root) {
        Map<TreeNode, TreeNode> result = new HashMap<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();

            if (temp.left != null) {
                result.put(temp.left, temp);
                q.offer(temp.left);
            }
            if (temp.right != null) {
                result.put(temp.right, temp);
                q.offer(temp.right);
            }
        }

        return result;
    }

    // can also do DFS to mark the parents
    private void addParents(TreeNode node, Map<TreeNode, TreeNode> map) {
        if (node == null)
            return;

        map.put(node.left, node);
        addParents(node.left, map);
        map.put(node.right, node);
        addParents(node.right, map);
    }

    // go left and right k times - no need for parent track and visited track since
    // starting from root
    private static void nodesAtK(TreeNode node, int k) {
        if (node == null)
            return;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);
        int currentLevel = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            if (currentLevel == k) {
                break;
            }
            currentLevel++;

            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();

                if (temp.left != null)
                    q.offer(temp.left);
                if (temp.right != null)
                    q.offer(temp.right);
            }
        }

        // nodes left in the queue -> answer
        while (!q.isEmpty()) {
            System.out.println(q.poll().data);
        }
    }

    private static TreeNode constructTree(int[] arr, int index) {
        TreeNode node = null;
        if (index < arr.length) {
            if (arr[index] != -1) {
                node = new TreeNode(arr[index]);
                node.left = constructTree(arr, 2 * index + 1);
                node.right = constructTree(arr, 2 * index + 2);
            }
        }
        return node;
    }

}
