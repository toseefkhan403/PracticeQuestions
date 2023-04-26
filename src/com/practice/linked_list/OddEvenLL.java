package com.practice.linked_list;

public class OddEvenLL {
    public static void main(String[] args) {

    }

    // keep a pointer at the last node - move odd nodes to the end
    private static Node oddEvenLL(Node head) {
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        // only 1 node
        if (head == last)
            return head;

        // 3 pointers - prev, curr, last
        Node prev = new Node(-1);
        prev.next = head;
        // dummy node so we dont have to update head upon removal
        Node prevHead = prev;
        Node curr = head;
        Node lastCurr = last;

        while (curr != last) {
            if (curr.data % 2 == 1) {
                // remove
                prev.next = curr.next;
                curr.next = null;

                // put it in the end
                lastCurr.next = curr;
                lastCurr = lastCurr.next;

                // do not move prev here
                curr = prev.next;
            } else {
                // move prev and curr
                prev = curr;
                curr = curr.next;
            }
        }

        // edge case - if last is odd, put it at the end too
        if (last.data % 2 == 1) {
            prev.next = curr.next;
            curr.next = null;

            // put it in the end
            lastCurr.next = curr;
        }

        return prevHead.next;
    }

    // 2 pointers - for odd and even - make odd and even lists - join them in the
    // end and break the cycle
    private static Node oddEvenLLI(Node head) {
        Node start = new Node(0);
        start.next = head;
        Node odd = start;
        Node oddHead = odd; // put it on the first odd node
        Node even = start;
        Node evenHead = even; // put it on the first even node
        Node curr = head;
        boolean firstTimeOdd = true;
        boolean firstTimeEven = true;

        while (curr != null) {
            if (curr.data % 2 == 0) {
                even.next = curr;
                even = even.next;
                if (firstTimeEven) {
                    evenHead = even;
                    firstTimeEven = false;
                }
            } else {
                odd.next = curr;
                odd = odd.next;
                if (firstTimeOdd) {
                    oddHead = odd;
                    firstTimeOdd = false;
                }
            }
            curr = curr.next;
        }

        // join even with odd
        even.next = oddHead;
        // break the cycle
        odd.next = null;

        return evenHead;
    }

}
