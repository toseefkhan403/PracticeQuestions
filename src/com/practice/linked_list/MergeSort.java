package com.practice.linked_list;

public class MergeSort {
    public static void main(String[] args) {

    }

    // get mid - two halves - keep splitting - merge sorted lists
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMid(head);
        ListNode temp = mid.next;
        // to get left half - else goes through the complete list
        mid.next = null;

        ListNode leftHalf = mergeSort(head);
        ListNode rightHalf = mergeSort(temp);
        ListNode finalHead = merge(leftHalf, rightHalf);
        return finalHead;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next; // .next needed for even list (to get floor value) - else gives ceil value and
                                   // gets stuck in infinite loop

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public ListNode merge(ListNode h1, ListNode h2) {
        ListNode temp = new ListNode(-1);
        ListNode tempHead = temp;

        while (h1 != null || h2 != null) {
            if (h1 == null) {
                temp.next = h2;
                h2 = h2.next;
                temp = temp.next;
                continue;
            }

            if (h2 == null) {
                temp.next = h1;
                h1 = h1.next;
                temp = temp.next;
                continue;
            }

            if (h1.val <= h2.val) {
                temp.next = h1;
                h1 = h1.next;
            } else {
                temp.next = h2;
                h2 = h2.next;
            }

            temp = temp.next;
        }

        return tempHead.next;
    }

}
