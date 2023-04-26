package com.practice.linked_list;

public class RotateList {
    public static void main(String[] args) {

    }

    // reverse does not work - first create a cycle - then remove it after length-k
    // nodes - return its next
    public ListNode rotateRight(ListNode head, int k) {
        ListNode curr = head;
        int length = 0;

        if (k == 0 || curr == null)
            return head;

        // need to find the length as k might be a large no - so k%n will be quicker
        while (curr != null) {
            length++;
            if (curr.next == null) {
                curr.next = head; // cycle the link
                break;
            }
            curr = curr.next;
        }

        k = k % length;

        int count = length - k;
        // go count times - break the link and return its next
        while (count > 1) {
            head = head.next;
            count--;
        }

        ListNode newHead = head.next;
        head.next = null;

        return newHead;
    }

    public ListNode rotateLeft(ListNode head, int k) {
        ListNode curr = head;
        int length = 0;

        if (k == 0 || curr == null)
            return head;

        // need to find the length as k might be a large no - so k%n will be quicker
        while (curr != null) {
            length++;
            if (curr.next == null) {
                curr.next = head; // cycle the link
                break;
            }
            curr = curr.next;
        }

        k = k % length;

        int count = k; // only difference between left and right rotate

        // go count times - break the link and return its next
        while (count > 1) {
            head = head.next;
            count--;
        }

        ListNode newHead = head.next;
        head.next = null;

        return newHead;
    }

}
