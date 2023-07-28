package com.practice.linked_list;

public class AddTwoNumbers {
    public static void main(String[] args) {

    }

    // add node by node - push carry to the end - O(max(m,n)), O(max(m,n))
    public Node addTwoNumbers(Node l1, Node l2) {
        // dummy node
        Node newHead = new Node(0);
        Node temp = newHead;
        int carry = 0;

        // rather than going till the end again to add the carry - do it here
        while (l1 != null || l2 != null || carry != 0) {
            int data = (l1 == null ? 0 : l1.data) + (l2 == null ? 0 : l2.data) + carry;
            // needs to be temp.next here - not temp - as last element will become 0 and we
            // dont want that - instead have first node as 0 and then start the answer with
            // the second node
            temp.next = new Node(data % 10);
            temp = temp.next;
            carry = data / 10;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        // starting the answer with the second node
        return newHead.next;
    }
}
