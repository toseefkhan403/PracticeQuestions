package com.practice.linked_list;

public class ReverseKGroup {
    public static void main(String[] args) {

    }

    // reverse k nodes at a time - take care of the next and prev pointers after
    // each reversal
    public ListNode reverseKGroup(ListNode head, int k) {
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

            // move currHead to storeNext - reverse this group in the next iteration
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

    // valid if length equals k
    private static boolean validHead(ListNode currHead, int k) {
        int length = 0;

        while (currHead != null) {
            length++;
            if (length == k) {
                return true;
            }

            currHead = currHead.next;
        }

        return false;
    }

}
