package com.practice.linked_list;

public class ReorderLL {
    public static void main(String[] args) {

    }

    // rather than going last every time, reverse second mid half and insert alternately
    // in the first half
    public static void reorderListOpti(ListNode head) {
        // find mid
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // reverse the list
        ListNode reverseHead = reverseLL(slow);

        // merge alternately
        ListNode curr = head;
        // for odd no of elements && for even no of elements - optimized
        while (curr != reverseHead && curr.next != reverseHead) {
            ListNode temp = curr.next;
            curr.next = reverseHead;
            reverseHead = reverseHead.next;

            curr.next.next = temp;
            curr = temp;
        }
    }

    // rather than going last every time, reverse second half and insert alternately
    // in the first half
    public static void reorderList(ListNode head) {
        // find mid
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // reverse the list
        ListNode reverseHead = reverseLL(slow);

        // insert alternately
        ListNode curr = head;
        while (curr != null && reverseHead != null) {
            ListNode temp = curr.next;
            // required if even elements - else cycle
            if (temp == reverseHead) {
                break;
            }

            curr.next = reverseHead;
            reverseHead = reverseHead.next;

            curr.next.next = temp;
            curr = temp;
        }
    }

    public static ListNode reverseLL(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;   // important as it marks the end of the result - else cycle

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    // pick last, delete it and insert alternately
    public static void reorderListI(ListNode head) {
        ListNode trav = head;

        if (trav == null || trav.next == null) {
            return;
        }

        while (trav.next.next != null) {
            ListNode temp = trav.next;
            trav.next = lastNode(head);
            trav.next.next = temp;
            trav = temp;
        }
    }

    private static ListNode lastNode(ListNode head) {
        while (head.next.next != null) {
            head = head.next;
        }

        // delete and return head's next
        ListNode temp = head.next;
        head.next = null;
        return temp;
    }
}
