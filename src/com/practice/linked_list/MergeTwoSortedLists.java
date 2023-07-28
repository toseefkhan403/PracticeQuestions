package com.practice.linked_list;

public class MergeTwoSortedLists {
    public static void main(String[] args) {

    }

    // same as merge() of merge sort
    // compare what's smaller - add to result and move to next - if one of the list
    // runs out, fill with the other one - O(m+n),O(m+n)
    // whenever need to create a LL - use dummy node and keep filling its next - at
    // the end return dummynode.next
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newList = new ListNode(0);
        ListNode newListHead = newList;

        while (list1 != null || list2 != null) {
            if (list1 == null) {
                // fill with list2
                newList.next = new ListNode(list2.val);
                list2 = list2.next;
                newList = newList.next;
                continue;
            }

            if (list2 == null) {
                // fill with list1
                newList.next = new ListNode(list1.val);
                list1 = list1.next;
                newList = newList.next;
                continue;
            }

            if (list1.val <= list2.val) {
                // always fill .next - not the node - nice trick
                newList.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                newList.next = new ListNode(list2.val);
                list2 = list2.next;
            }

            newList = newList.next;
        }

        // as first node is 0
        return newListHead.next;
    }

}
