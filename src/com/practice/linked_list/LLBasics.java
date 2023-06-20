package com.practice.linked_list;

public class LLBasics {
    public static void main(String[] args) {
        // create a LL - 1-indexed
        Node head = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);

        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        // traverse(head);
        // head = insert(head, 1, 1);
        head = insert(head, 5, 25);
        traverse(head);
        System.out.println();
        head = delete(head, 4);
        traverse(head);
    }

    public static void traverse(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    // traverse till the position - make a node and rewire links - take care of 0
    // position
    // in array, we get to the position without traversing - using formula(starting
    // address+no. of bytes of data type*position) - but then we need to shift
    // elements to insert new ones - here need to go till the position and can
    // insert without shifting
    public static Node insert(Node head, int position, int data) {
        Node toAdd = new Node(data);

        if (position <= 1) {
            // make head
            toAdd.next = head;
            head = toAdd;
            return head;
        }

        Node curr = head;
        // not <pos
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
        }

        // always toAdd.next first to avoid value loss
        toAdd.next = curr.next;
        curr.next = toAdd;
        return head;
    }

    // similar logic as insertion - take care of 0 position - just rewire links
    // Always remove the next link while deleting - then only the gc will collect it
    // - dont do temp = null - temp will point to null but the link will still be
    // there - won't free the memory
    public static Node delete(Node head, int position) {
        if (position <= 1) {
            // dont do head=null - ref to LL will be lost
            Node t = head;
            head = head.next;
            t.next = null; // now will be collected by gc
            return head;
        }

        Node curr = head;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
        }

        Node t = curr.next;
        curr.next = curr.next.next;
        t.next = null;

        return head;
    }

    // delete by matching the data in the node
    public static Node deleteMatched(Node head, int data) {
        if (head == null)
            return null;

        // first position
        if (head.data == data) {
            Node temp = head;
            head = head.next;
            // Always remove the next link while deleting - then only the gc will collect it
            // - dont do temp = null - temp will point to null but the link will still be
            // there - won't free the memory
            temp.next = null;
            return temp;
        }

        // slow and fast pointer
        Node p = head, q = null;
        // assigning and checking in the same line - first assigns then checks
        while ((q = p.next) != null) {
            if (q.data == data) {
                p.next = q.next;
                q.next = null;
                return q;
            }

            p = q;
        }

        // node does not exist
        return null;
    }

    // using hare and tortoise - 3 pointers going in line
    public static Node removeEnd(Node head) {
        Node p = head, q = null, r = p.next;

        // q->p->r
        while ((r = p.next) != null) {
            q = p;
            p = r;
        }

        // remove q.next - which is p - r points to null(the end of the list)
        q.next = null;
        // return the deleted node
        return p;
    }

}
