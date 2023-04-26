package com.practice.linked_list;

public class PalindromeLL {
    public static void main(String[] args) {

    }

    // find mid - reverse the second half - match elements one by one
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // slow is at mid - slow is at mid+1 in even list
        // reverse mid till end
        ListNode prev = null;

        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        // prev is the start now
        // start matching
        while (prev != null) {
            if (head.val != prev.val) {
                return false;
            }

            prev = prev.next;
            head = head.next;
        }

        return true;
    }

}
