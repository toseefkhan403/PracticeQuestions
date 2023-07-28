package com.practice.linked_list;

public class CopyListRandomPointer {
    public static void main(String[] args) {

    }

    // brute: measure distance of each random pointer - add same distance node in
    // the random pointers in the copy - O(n^2)

    // better: do one to one mapping of nodes in a map - key,value = original node,
    // copy node
    // - key's random will point to another key(say x) - value's random will point
    // to x's value - O(2n),O(n)

    // optimal: do mapping without map - insert new nodes alternately in the
    // original list - add randoms using next pointers - break the alternate links -
    // add the null checks later
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node curr = head;

        // 1. add mapping
        while (curr != null) {
            Node temp = curr.next;
            curr.next = new Node(curr.data);
            curr.next.next = temp;
            curr = temp;
        }

        // 2. add random
        curr = head;
        while (curr != null) {
            curr.next.random = curr.random != null ? curr.random.next : null;
            curr = curr.next.next;
        }

        // 3. rejoin lists
        curr = head;
        Node newHead = curr.next;
        while (curr != null) {
            Node save = curr.next;
            curr.next = curr.next.next;
            save.next = save.next != null ? save.next.next : null;
            curr = curr.next;
        }

        return newHead;
    }

}
