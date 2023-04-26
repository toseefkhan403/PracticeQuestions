package com.practice.linked_list;

import java.util.HashSet;

public class LLCycle2 {
    public static void main(String[] args) {

    }

    // return cycle start
    public static ListNode detectCycle(ListNode head) {
        // get meeting point
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (hasCycle) {
            // distance of head to cycle start = meeting point to cycle start
            // as intersection point is ahead of head by the length of the cycle
            while (slow != head) {
                head = head.next;
                slow = slow.next;
            }

            return head;
        } else {
            return null;
        }
    }

    // use a set - return if set contains that node -> starting point
    public static ListNode detectCycleSet(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode curr = head;

        while (curr != null) {

            if (set.contains(curr)) {
                // starting point
                return curr;
            }

            set.add(curr);
            curr = curr.next;
        }

        // cycle doesn't exist
        return null;
    }

    // first detect if cycle - calc cycle length - move p2 ahead by cycle length(at
    // the meeting point basically) - then keep moving p1 and p2 - will meet at the
    // starting point
    public static ListNode detectCycleWithLength(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle)
            return null;

        int cycleLength = calcLength(slow);

        ListNode p1 = head;
        ListNode p2 = head;

        // move p2 ahead by cycleLength
        while (cycleLength > 0) {
            p2 = p2.next;
            cycleLength--;
        }

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    private static int calcLength(ListNode slow) {
        int length = 1;
        ListNode curr = slow.next;
        while (curr != slow) {
            curr = curr.next;
            length++;
        }

        return length;
    }

}
