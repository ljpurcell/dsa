package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {

    private final double LFACTOR_UPPER = 0.75;
    private final double LFACTOR_LOWER = 0.25;
    private final int MIN_CAPACITY = 8;

    private int size;
    private int capacity;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        capacity = MIN_CAPACITY;
        nextFirst = 3;
        nextLast = 4;
        items = (T[]) new Object[capacity];
    }

    public void addFirst(T item) {
        items[nextFirst % capacity] = item;
        nextFirst = (capacity + (nextFirst - 1)) % capacity;
        size += 1;

        if ((double) size / capacity > LFACTOR_UPPER) {
            increaseCapacity();
        }
    }

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

    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
        size += 1;

        if ((double) size / capacity > LFACTOR_UPPER) {
            increaseCapacity();
        }
    }

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

    // constant time
    public int size() {
        return size;
    }


    public void printDeque() {
        int indexToPrint;
        for (int i = 0; i < size; i++) {
            indexToPrint = (nextFirst + 1 + i) % capacity;
            System.out.print(items[indexToPrint] + " ");
        }

        System.out.println();
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
        if (!(o instanceof ArrayDeque) || ((ArrayDeque<?>) o).size() != size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (this.get(i) != ((ArrayDeque<?>) o).get(i)) {
                return false;
            }
        }

        return true;
    }

    private void increaseCapacity() {
        int newCapacity = capacity * 2;
        T[] larger = (T[]) new Object[newCapacity];

        copyIntoNewArray(larger);

        items = larger;
        capacity = newCapacity;
        nextFirst = capacity - 1;
        nextLast = size;
    }

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
        int indexToCopy;
        for (int i = 0; i < size; i++) {
            indexToCopy = (nextFirst + 1 + i) % capacity;
            newArray[i] = items[indexToCopy];
        }
    }
}
