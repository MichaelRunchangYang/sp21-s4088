package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {
    public T[] items;
    public int size;
    public int nextFirst;
    public int nextLast;

    // Constructor
    public ArrayDeque() {
        items = (T[])new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void resize(int capacity) {
        T[] newItems = (T[])new Object[capacity];
        int toCopyIndex = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            newItems[i] = items[toCopyIndex];
            toCopyIndex = (toCopyIndex + 1) % items.length;
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    // Adds an item of type T to the front of the deque
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 +items.length) % items.length;
        size++;
    }

    // Adds an item of type T to the back of the deque
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    // Returns true if deque is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque
    public int size() {
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space
    public void printDeque() {
        if (size == 0) {
            System.out.println();
            return;
        }
        int toPrintIndex = (nextFirst + 1) % items.length;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(items[toPrintIndex] + " ");
            toPrintIndex = (toPrintIndex + 1) % items.length;
        }
        System.out.print(items[(nextLast-1+items.length)%items.length]);
        System.out.println();
    }

    // Removes and returns the item at the front of the deque
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int toRemoveIndex = (nextFirst + 1) % items.length;
        T toRemove = items[toRemoveIndex];
        items[toRemoveIndex] = null;
        nextFirst = (nextFirst + 1) % items.length;
        size--;
        if (size > 8 && (double) size / items.length < .25) {
            resize(items.length / 2);
        }
        return toRemove;
    }

    // Removes and returns the item at the back of the deque
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int toRemoveIndex = (nextLast - 1 + items.length) % items.length;
        T toRemove = items[toRemoveIndex];
        items[toRemoveIndex] = null;
        nextLast = (nextLast - 1 + items.length) % items.length;
        size--;
        if (size > 8 && (double) size / items.length < .25) {
            resize(items.length / 2);
        }
        return toRemove;
    }

    // Gets the item at the given index
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int realIndex = (nextFirst + 1 + index) % items.length;
        return items[realIndex];
    }

    // Returns an iterator over elements of type T
    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        int currentPos = (nextFirst + 1) % items.length;

        @Override
        public boolean hasNext() {
            return currentPos > nextFirst || currentPos < nextLast;
        }

        @Override
        public T next() {
            T toReturn = items[currentPos];
            currentPos = (currentPos + 1) % items.length;
            return toReturn;
        }
    }

    // Returns whether or not the parameter o is equal to the Deque
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof ArrayDeque otherDeque) {
            for (int i = 0; i < size; i++) {
                if (this.get(i) != otherDeque.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(1);
        deque.addLast(2);
        deque.get(0);
    }
}
