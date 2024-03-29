package com.practice.trees;

class FlattenBTtoLL {
    // need it as class variable
    TreeNode prev = null;

    // brute: store the preorder - make the linked list - O(2n),O(n)

    // better: recursive - RLD - keep track of prev - join node right with prev and
    // make left null - update prev with node - O(n),O(n)
    public void flatten(TreeNode node) {
        if (node == null)
            return;

        flatten(node.right);
        flatten(node.left);

        node.right = prev;
        node.left = null;
        prev = node;
    }

    // optimal: using Morris traversal - meh - O(n),O(1)
}
