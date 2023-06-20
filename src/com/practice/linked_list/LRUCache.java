package com.practice.linked_list;

import java.util.HashMap;

class LRUCache {
    // head and tail ALWAYS point to these dummy nodes 
    // don't move head n tail - move inside nodes
    DLLNode head = new DLLNode(-1, -1);
    DLLNode tail = new DLLNode(-1, -1);
    // key -> DLL node
    HashMap<Integer, DLLNode> map = new HashMap<>();
    int capacity = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        // if present, move it ahead
        // if not return -1
        if (map.containsKey(key)) {
            DLLNode node = map.get(key);
            // move it ahead
            removeNode(node);
            insertAfterHead(node);

            return node.data;
        }

        return -1;
    }

    private void insertAfterHead(DLLNode node) {
        // insert node after head
        map.put(node.key, node);
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(DLLNode node) {
        // no need to traverse! its a DLL! simply change prev and next - this is why DLL
        // over LL
        // DLLNode curr = head.next;
        // while (curr != tail && curr.next != node) {
        // curr = curr.next;
        // }
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node = null;
    }

    public void put(int key, int value) {
        // if absent-> if enough space, just put it after head - else remove
        // tail's prev and add it after head
        if (!map.containsKey(key)) {
            // map size gives length - no need to traverse the LL
            if (map.size() == capacity) {
                // remove tail's prev - the LEAST RECENTLY USED element
                removeNode(tail.prev);
            }
        } else {
            // update the already present value - remove it - and insert new Node next to
            // head
            removeNode(map.get(key));
        }

        // add after head
        DLLNode newNode = new DLLNode(key, value);
        insertAfterHead(newNode);
    }
}

class DLLNode {
    int data;
    // add key to the node - so you don't have to traverse map to delete LRU element
    int key;
    DLLNode prev;
    DLLNode next;

    public DLLNode(int key, int data) {
        this.key = key;
        this.data = data;
        prev = null;
        next = null;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */