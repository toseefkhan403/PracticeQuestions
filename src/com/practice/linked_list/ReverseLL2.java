package com.practice.linked_list;

public class ReverseLL2 {
    public static void main(String[] args) {

    }

    // rather than going l-r+1 length beforehand for lastNext, do it after reverse -
    // no need to traverse twice
    // 3 steps
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // edge case
        if (left == right || head == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;

        // 1. travel p-1 nodes
        for (int i = 0; i < left - 1; i++) {
            prev = curr;
            curr = curr.next;
        }

        // curr is on pth node - prev is one before curr
        ListNode lastNodeOfSublist = curr;
        ListNode lastNodeOfFirstPart = prev;
        ListNode lastNext = null;

        // 2. reverse p to q nodes
        for (int i = 0; i < right - left + 1; i++) {
            lastNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = lastNext;
        }

        // 3. join links with p-1 and q+1 nodes
        if (lastNodeOfFirstPart != null) {
            lastNodeOfFirstPart.next = prev;
        } else {
            // left = 1 - reverse's head is the new head
            head = prev;
        }
        lastNodeOfSublist.next = lastNext;

        return head;
    }

    // keep pre and lastNext ptr - reverse in between - join them
    public ListNode reverseBetweenI(ListNode head, int left, int right) {
        // for left = 1 - keep it on null
        ListNode pre = null;
        ListNode curr = head;

        for (int i = 1; i < left; i++) {
            pre = curr;
            curr = curr.next;
        }

        ListNode last = curr;
        for (int i = left; i < right; i++) {
            last = last.next;
        }

        ListNode lastNext = last.next;
        last.next = null;

        ListNode newHead = reverseList(curr);
        ListNode newHeadCurr = newHead;

        if (pre != null) {
            pre.next = newHead;
        }

        int length = right - left;

        for (int i = 0; i < length; i++) {
            newHeadCurr = newHeadCurr.next;
        }

        newHeadCurr.next = lastNext;

        // if left = 1, the head is changed
        return left != 1 ? head : newHead;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode curNode = head;
        ListNode prev = null;

        while (curNode != null) {
            ListNode temp = curNode.next;
            curNode.next = prev;
            prev = curNode;
            curNode = temp;
        }

        // prev is the first node now
        return prev;
    }
}