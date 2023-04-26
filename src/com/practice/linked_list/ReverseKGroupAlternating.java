package com.practice.linked_list;

public class ReverseKGroupAlternating {
    public static void main(String[] args) {
        ListNode head = new ListNode(10);
        ListNode n2 = new ListNode(20);
        ListNode n3 = new ListNode(30);
        ListNode n4 = new ListNode(40);
        ListNode n5 = new ListNode(50);
        ListNode n6 = new ListNode(60);
        ListNode n7 = new ListNode(70);

        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        traverse(head);
        System.out.println();

        traverse(reverseKGroupAlternating(head, 2));
    }

    // same as reversing k group - except at the end skip one k group by moving
    // storeNext and prevRev by k places
    public static ListNode reverseKGroupAlternating(ListNode head, int k) {
        ListNode currHead = head;
        // dummy node
        ListNode prevRev = new ListNode(0);
        prevRev.next = head;
        // need to keep a pointer to the head - else after reverse head moves ahead
        ListNode res = prevRev;

        while (validHead(currHead, k)) {
            // save the next node after k nodes
            ListNode curr = currHead;
            // go k-1 times
            for (int i = 1; i < k; i++) {
                curr = curr.next;
            }

            ListNode storeNext = curr.next;

            ListNode reverseHead = reverseK(currHead, k);
            // IMPORTANT - connect reverse head with the previous node
            prevRev.next = reverseHead;

            for (int i = 1; i < k; i++) {
                reverseHead = reverseHead.next;
            }

            // update prevRev with reverse's end
            prevRev = reverseHead;
            // connect reverse's end with storeNext
            reverseHead.next = storeNext;

            // skip one k group
            for (int i = 0; i < k && storeNext != null; i++) {
                storeNext = storeNext.next;
                // prevRev is one node behind than storeNext - so no need for null check
                // need to move prevRev as well - else result skips non repeating k group
                prevRev = prevRev.next;
            }

            // move currHead - reverse this group in the next iteration
            currHead = storeNext;
        }

        return res.next;
    }

    // reverse k nodes at a time
    public static ListNode reverseK(ListNode head, int k) {
        ListNode curr = head;
        ListNode prev = null;

        for (int i = 0; i < k; i++) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        // reverse head
        return prev;
    }

    // valid if a multiple of k
    private static boolean validHead(ListNode currHead, int k) {
        int length = 0;

        while (currHead != null) {
            length++;
            if (length == k) {
                return true;
            }

            // changing it here doesn't change it in the caller method - passes a copy
            currHead = currHead.next;
        }

        return false;
    }

    public static void traverse(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}
