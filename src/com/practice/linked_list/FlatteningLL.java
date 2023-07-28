package com.practice.linked_list;

public class FlatteningLL {
    public static void main(String[] args) {

    }

    public static NodeF flattenRecur(NodeF head) {
        if (head == null || head.next == null) {
            return head;
        }

        // goes till last - then starts merging and comes back
        head.next = flatten(head.next);
        head = mergeSorted(head, head.next);

        return head;
    }

    // merge(flatten) one by one - curr with next - return result
    public static NodeF flatten(NodeF head) {
        NodeF curr = head;
        // dummy NodeF
        NodeF temp = new NodeF(-1);
        NodeF res = temp;
        while (curr != null) {
            temp.bottom = mergeSorted(temp.bottom, curr);
            curr = curr.next;
        }

        return res.bottom;
    }

    // merge vertically - according to the bottom value - without extra space(no new nodes)
    public static NodeF mergeSorted(NodeF a, NodeF b) {
        NodeF temp = new NodeF(-1);
        NodeF res = temp;
        while (a != null && b != null) {
            if (a.data < b.data) {
                temp.bottom = a;
                a = a.bottom;
                temp = temp.bottom;
            } else {
                temp.bottom = b;
                b = b.bottom;
                temp = temp.bottom;
            }
        }

        // b exhausted
        if (a != null) {
            temp.bottom = a;
        }
        // a exhausted
        else {
            temp.bottom = b;
        }

        return res.bottom;
    }

}

class NodeF {
    int data;
    NodeF next;
    NodeF bottom;

    NodeF(int d) {
        data = d;
        next = null;
        bottom = null;
    }
}