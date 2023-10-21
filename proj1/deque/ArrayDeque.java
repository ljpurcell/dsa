package deque;

import java.util.Iterator;

public class ArrayDeque<T> {

    private final double LFACTOR_UPPER = 0.75;
    private final double LFACTOR_LOWER = 0.25;
    private final int MIN_CAPACITY = 8;

    private int size;
    private int capacity;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        // TODO probably using % capacity inefficiently
        size = 0;
        capacity = MIN_CAPACITY;
        nextFirst = 3;
        nextLast = 4;
        items = (T[]) new Object[capacity];
    }

    // TODO need to handle case where reaching end of array
    public void addFirst(T item) {
        if ((double) size / capacity > LFACTOR_UPPER) {
            increaseCapacity();
        }

        items[nextFirst % capacity] = item;
        nextFirst = (nextFirst - 1) % capacity;
        size += 1;
    }

    // TODO need to handle case where reaching end of array
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int indexOfItem = (nextFirst + 1) % capacity;
        T item = items[indexOfItem];
        items[indexOfItem] = null;
        size -= 1;
        nextFirst = indexOfItem;

        if ((double) size / capacity < LFACTOR_LOWER) {
            decreaseCapacity();
        }

        return item;
    }

    // TODO need to handle case where reaching end of array
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size += 1;

        if ((double) size / capacity > LFACTOR_UPPER) {
            increaseCapacity();
        }
    }

    // TODO need to handle case where reaching end of array
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T item = items[(nextLast - 1) % capacity];
        items[(nextLast - 1) % capacity] = null;
        size -= 1;
        nextLast = (nextLast - 1) % capacity;

        if ((double) size / capacity < LFACTOR_LOWER) {
            decreaseCapacity();
        }

        return item;
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

    public T get(int index) {
        int underlyingIndex = getUnderlyingIndex(index);
        return underlyingIndex >= 0 ? items[underlyingIndex] : null;
    }

    private int getUnderlyingIndex(int index) {
        if (index < 0 || index > size - 1) {
            return -1;
        }

        /*
        user interface  {1, 2, 3, 4} -> underlying {0, 0, 0, 1, 2, 3, 4, 0}
        0 = start -> nextFirst + 1

         */

        return (nextFirst + 1 + index) % capacity;
    }

    // TODO
    public Iterator<T> iterator() {
        return null;
    }

    public boolean equals(Object o) {
        return false;
    }


    // TODO: NAIVE - centre items in larger array
    private void increaseCapacity() {
        int newCapacity = capacity * 2;
        T[] larger = (T[]) new Object[newCapacity];

        /* arraycopy with wrap
         * start = (nextFirst + 1) % capacity
         * copy size elements
         * start =
         */

        copyIntoNewArray(larger);

        items = larger;
        capacity = newCapacity;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    // TODO: NAIVE - centre items in larger array
    private void decreaseCapacity() {
        if (capacity > MIN_CAPACITY) {
            int newCapacity = capacity / 2;
            T[] smaller = (T[]) new Object[newCapacity];

            copyIntoNewArray(smaller);

            items = smaller;
            capacity = newCapacity;
            nextFirst = capacity - 1;
            nextLast = size;
        }
    }

    private void copyIntoNewArray(T[] newArray) {
        int indexToCopy = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            indexToCopy = (nextFirst + 1 + i) % capacity;
            newArray[i] = items[indexToCopy];
        }
    }
}
