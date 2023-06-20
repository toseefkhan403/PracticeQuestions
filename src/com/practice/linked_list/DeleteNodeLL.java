package com.practice.linked_list;

public class DeleteNodeLL {
    public static void main(String[] args) {

    }

    // since nodes have unique data and the node isn't tail node, copy data from
    // next and delete next - no one would know - O(1), O(1)
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        ListNode temp = node.next;
        node.next = temp.next;
        // free the memory
        temp.next = null;
    }

}
