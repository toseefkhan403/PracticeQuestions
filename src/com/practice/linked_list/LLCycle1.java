package com.practice.linked_list;

public class LLCycle1 {
    public static void main(String[] args) {

    }

    // slow and fast pointers will meet
    public boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

}
