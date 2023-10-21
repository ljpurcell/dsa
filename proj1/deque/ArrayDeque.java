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
        nextFirst = nextFirst - 1 % capacity;
        size += 1;
    }

    // TODO need to handle case where reaching end of array
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if ((double) size / capacity < LFACTOR_LOWER) {
            decreaseCapacity();
        }

        T item = items[nextFirst + 1 % capacity];
        items[nextFirst + 1 % capacity] = null;
        size -= 1;
        nextFirst = nextFirst - 1 % capacity;
        return item;
    }

    // TODO need to handle case where reaching end of array
    public void addLast(T item) {
        if ((double) size / capacity > LFACTOR_UPPER) {
            increaseCapacity();
        }
        items[nextLast % capacity] = item;
        nextLast =  nextLast + 1 % capacity;
        size += 1;
    }

    // TODO need to handle case where reaching end of array
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if ((double) size / capacity < LFACTOR_LOWER) {
            decreaseCapacity();
        }
        T item = items[nextLast - 1 % capacity];
        items[nextLast - 1 % capacity] = null;
        size -= 1;
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
            return  -1;
        }

        /*
        user interface  {1, 2, 3, 4} -> underlying {0, 0, 0, 1, 2, 3, 4, 0}
        0 = start -> nextFirst + 1

         */

        return nextFirst + 1 + index % capacity; // TODO doesn't wrap when first has gone past the zeroth index
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
        capacity = capacity * 2;
        T[] larger = (T[]) new Object[capacity];
        System.arraycopy(items, 0, larger, 0, size);
        items = larger;
    }

    // TODO: NAIVE - centre items in larger array
    private void decreaseCapacity() {
        if (capacity > MIN_CAPACITY) {
            capacity = capacity / 2;
            T[] smaller = (T[]) new Object[capacity];
            System.arraycopy(items, 0, smaller, 0, size);
            items = smaller;
        }
    }
}
