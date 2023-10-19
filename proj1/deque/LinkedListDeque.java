package deque;

public class LinkedListDeque {
    private static class ListNode {
        ListNode previous;
        int item;
        ListNode next;

        public ListNode(ListNode p, int i, ListNode n) {
            previous = p;
            item = i;
            next = n;
        }
    }

    ListNode sentinel;

    public LinkedListDeque() {
        ListNode ln = null;
    }

    // must be constant time
    public void addFirst(T item) {}

    // must be constant time
    public void addLast(T item) {}

    // must be constant time
    public T removeFirst() { return T; }

    // must be constant time
    public T removeLast() { return T; }

    // must use iteration
    public T get(int index) { return  T; }

    public T getRecursive(int index) { return T; }

    public boolean isEmpty() { return true; }

    // constant time
    public int size() { return 0; }

    public void printDeque() {}


    public Iterator<T> iterator() {}

    public boolean equals(Object o) {}
}
