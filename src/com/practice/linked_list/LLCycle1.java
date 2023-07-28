package com.practice.linked_list;

import java.util.HashSet;
import java.util.Set;

public class LLCycle1 {
    // slow and fast pointers will meet - O(n), O(1)
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

    // brute: put in set - if a guy occurs again - has cycle - O(n), O(n)
    public boolean hasCycleBrute(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return true;
            }
            set.add(curr);
            curr = curr.next;
        }
        return false;
    }

}
