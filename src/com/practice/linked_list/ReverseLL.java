package com.practice.linked_list;

public class ReverseLL {
    public static void main(String[] args) {

    }

    // reverse links - 3 pointer approach
    // 3 steps - save curr's next in temp - curr.next to prev - inc prev to curr -
    // inc curr to temp - O(n),O(1)
    public static Node reverseList(Node head) {
        Node curNode = head;
        Node prev = null;

        while (curNode != null) {
            Node temp = curNode.next;
            curNode.next = prev;
            prev = curNode;
            curNode = temp;
        }

        // prev is the first node now
        return prev;
    }

    // using recursion - only consider first case - rest leave on rlof - only need
    // to reverse headNext.next with head and make head.next null
    // O(n),O(n)
    public static Node recurReverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node newHead = recurReverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

}
