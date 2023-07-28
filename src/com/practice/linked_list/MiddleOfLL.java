package com.practice.linked_list;

public class MiddleOfLL {
    // brute: calc length - traverse to n/2th guy - O(1.5*n), O(1)

    // slow and fast pointer - O(0.5n), O(1)
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
