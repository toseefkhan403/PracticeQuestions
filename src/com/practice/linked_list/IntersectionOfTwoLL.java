package com.practice.linked_list;

import java.util.HashSet;

public class IntersectionOfTwoLL {
    public static void main(String[] args) {

    }

    // optimal 1: find lengths - find diff - move longer by diff -> aligned - start
    // traversing together - will meet at the intersection pt.
    // optimal 2: traverse d1 and d2 - when you reach end, go to the other list -
    // will meet at the intersection pt. - or at null if there is no int. pt.
    // O(2*m), O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode d1 = headA;
        ListNode d2 = headB;

        while (d1 != d2) {
            d1 = d1 != null ? d1.next : headB;
            d2 = d2 != null ? d2.next : headA;
        }

        // will meet at the intersection pt. - or at null if there is no int. pt.
        return d1;
    }

    // O(m+n), O(m)
    public static ListNode getIntersectionNodeSpace(ListNode headA, ListNode headB) {
        // hash the address
        HashSet<ListNode> set = new HashSet<>();

        // fill set with A
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        // return when first same guy found
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }

    // for each A, go thru entire B - return if intersection found - O(m*n)
    public static ListNode getIntersectionNodeBrute(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode currB = headB;
            while (currB != null) {
                if (headA == currB) {
                    // found intersection
                    return currB;
                }
                currB = currB.next;
            }

            headA = headA.next;
        }

        return null;
    }
}
