package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private class ListNode {
        ListNode previous;
        T item;
        ListNode next;

        ListNode(ListNode p, T i, ListNode n) {
            previous = p;
            item = i;
            next = n;
        }
    }

    ListNode sentinel;
    int size;

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // must be constant time
    public void addFirst(T item) {
        sentinel.next = new ListNode(sentinel, item, sentinel.next);
        sentinel.next.next.previous = sentinel.next;
        size += 1;
    }

    // must be constant time
    public T removeFirst() {
        T item = null;
        if (sentinel.next != sentinel) {
            item = sentinel.next.item;
            ListNode newFirst = sentinel.next.next;
            sentinel.next = newFirst;
            newFirst.previous = sentinel;
            size -= 1;
        }
        return item;
    }

    // must be constant time
    public void addLast(T item) {
        sentinel.previous = new ListNode(sentinel.previous, item, sentinel);
        sentinel.previous.previous.next = sentinel.previous;
        size += 1;
    }

    // must be constant time
    public T removeLast() {
        T item = null;
        if (sentinel.previous != sentinel) {
            item = sentinel.previous.item;
            ListNode newLast = sentinel.previous.previous;
            sentinel.previous = newLast;
            newLast.next = sentinel;
            size -= 1;
        }
        return item;
    }

    // constant time
    public int size() {
        return size;
    }


    public void printDeque() {
        for (ListNode node = sentinel.next; node != sentinel; node = node.next) {
            System.out.print(node.item + " ");
        }
        System.out.println();
    }


    // must use iteration
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        ListNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }

        return getElementNAway(sentinel.next, index);
    }

    private T getElementNAway(ListNode o, int off) {
        if (off == 0) {
            return o.item;
        }

        return getElementNAway(o.next, off - 1);
    }

    // TODO
    public Iterator<T> iterator() {
        return null;
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque) || ((LinkedListDeque<?>) o).size() != size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(((LinkedListDeque<?>) o).get(i))) {
                return false;
            }
        }

        return true;
    }
}
