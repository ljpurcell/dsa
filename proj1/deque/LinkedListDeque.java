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
    int size;

    public LinkedListDeque() {
        sentinel = new ListNode(null, -100, null);
        size = 0;
    }

    // must be constant time
    public void addFirst(int item) {
        sentinel.next = new ListNode(sentinel, item, sentinel.next);
        if (sentinel.next.next != null) {
            sentinel.next.next.previous = sentinel.next;
        }
        size += 1;
    }

    // must be constant time
    public int removeFirst() {
        if (sentinel.next != null) {
            int item = sentinel.next.item;
            ListNode newFirst = sentinel.next.next;
            sentinel.next = newFirst;
            if (newFirst != null) {
                newFirst.previous = sentinel;
            }
            size -= 1;
            return item;
        }

        return -1;
    }

    // must be constant time
    public void addLast(int item) {
        ListNode newLast = new ListNode(sentinel.previous, item, sentinel);
        sentinel.previous = newLast;
        if (sentinel.previous.previous != null) {
            sentinel.previous.previous.next = newLast;
        }
        size += 1;
    }

    // must be constant time
    public int removeLast() {
         if (sentinel.previous != null) {
             int item = sentinel.previous.item;
             ListNode newLast = sentinel.previous.previous;
             sentinel.previous = newLast;
             if (newLast != null) {
                 newLast.next = sentinel;
             }
             size -= 1;
             return item;
         }

         return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // constant time
    public int size() {
        return size;
    }


    public void printDeque() {

    }

    /*

    // must use iteration
    public T get(int index) { return  T; }

    public T getRecursive(int index) { return T; }



    public Iterator<T> iterator() {}

    public boolean equals(Object o) {}
    */
}
