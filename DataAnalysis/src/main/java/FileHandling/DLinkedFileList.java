package FileHandling;

import java.io.File;

public class DLinkedFileList {

    private final DNode dummyHead;
    private final DNode dummyTail;
    private int size;

    public DLinkedFileList() {
        dummyHead = new DNode();
        dummyTail = new DNode();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    public File get(int i) {
        return getNode(i).element;
    }

    public void set(int i, File f) {
        getNode(i).element = f;
    }

    public void add(File f) {
        //The illusion of choice
        addLast(f);

    }

    public void add(int i, File f) {
        if (i > size || i <= 0) {
            throw new ArrayIndexOutOfBoundsException("Tried to add a file to a index that is out of bounds");
        }
        DNode newNode = new DNode();
        newNode.element = f;

        DNode prevNode = getNode(i - 1);
        prevNode.next.prev = newNode;
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        newNode.prev = prevNode;

        size += 1;
    }

    public File remove(int i) {
        DNode del = getNode(i);
        File deletedFile = del.element;
        del.prev.next = del.next;
        del.next.prev = del.prev;
        del.element = null;
        size -= 1;
        return deletedFile;
    }

    public void remove(File f) {
        DNode node = dummyHead.next;
        // O(N) :(
        for (int i = 0; i < size; i++) {
            if (node.element.equals(f)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.element = null;
                size -= 1;
                return;
            }
        }
    }

    public void clear() {
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(File f) {

        DNode nextNode = dummyHead.next;
        DNode newNode = new DNode();
        newNode.element = f;
        newNode.next = nextNode;
        newNode.prev = dummyHead;
        nextNode.prev = newNode;
        dummyHead.next = newNode;
        size += 1;
    }

    public void addLast(File f) {
        DNode prevNode = dummyTail.prev;
        DNode newNode = new DNode();
        newNode.element = f;
        newNode.prev = prevNode;
        newNode.next = dummyTail;
        prevNode.next = newNode;
        dummyTail.prev = newNode;
        size += 1;
    }

    public void removeFirst(File f) {
        // the illusion of choice
        remove(f);

    }

    private DNode getNode(int i) {
        DNode node;
        if (i < size / 2) {
            node = dummyHead;
            for (int k = 0; k < i; k++) {
                node = node.next;
            }
        } else {
            node = dummyTail;
            for (int k = size - 1; k > i; k--) {
                node = node.prev;
            }
        }
        return node;
    }

    private class DNode {
        File element;
        DNode next;
        DNode prev;
    }
}
