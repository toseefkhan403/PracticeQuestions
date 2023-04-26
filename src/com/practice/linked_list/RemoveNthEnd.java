package com.practice.linked_list;

public class RemoveNthEnd {
    public static void main(String[] args) {

    }

    // slow and fast pointer - start at 0 - give fast head start of n - go straight
    // - delete slow's next
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode slow = start;
        ListNode fast = start;

        // give a head start of n
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // go till the end
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // edge case - remove the head - slow is still on 0
        if (slow.next == head) {
            ListNode res = head.next;
            head.next = null;
            return res;
        }

        // delete slow's next
        ListNode temp = slow.next;
        slow.next = slow.next.next;
        temp.next = null;

        return head;
    }

    // calc length - traverse length-k from start - delete node
    public static ListNode removeNthFromEndBrute(ListNode head, int n) {
        int length = 0;
        ListNode curr = head;

        while (curr != null) {
            curr = curr.next;
            length++;
        }

        int traverse = length - n;
        ListNode temp = head;

        while (traverse > 1) {
            temp = temp.next;
            traverse--;
        }

        if (traverse == 0) {
            // delete the first node
            ListNode res = head.next;
            head.next = null;
            return res;
        } else {
            // delete the node
            ListNode t = temp.next.next;
            temp.next.next = null;
            temp.next = t;

            return head;
        }
    }
}
