package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int toCopyIndex = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            newItems[i] = items[toCopyIndex];
            toCopyIndex = (toCopyIndex + 1) % items.length;
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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
        System.out.print(items[(nextLast - 1 + items.length) % items.length]);
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int toRemoveIndex = (nextFirst + 1) % items.length;
        T toRemove = items[toRemoveIndex];
        items[toRemoveIndex] = null;
        nextFirst = (nextFirst + 1) % items.length;
        size--;
        if (size > 8 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return toRemove;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int toRemoveIndex = (nextLast - 1 + items.length) % items.length;
        T toRemove = items[toRemoveIndex];
        items[toRemoveIndex] = null;
        nextLast = (nextLast - 1 + items.length) % items.length;
        size--;
        if (size > 8 && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return toRemove;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int realIndex = (nextFirst + 1 + index) % items.length;
        return items[realIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<T> {
        private int position;
        private int count;

        DequeIterator() {
            position = (nextFirst + 1) % items.length;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T toReturn = items[position];
            position = (position + 1) % items.length;
            count++;
            return toReturn;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<?> otherDeque = (Deque<?>) o;
        if (size != otherDeque.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            T thisItem = this.get(i);
            Object otherItem = otherDeque.get(i);
            if (thisItem == null) {
                if (otherItem != null) {
                    return false;
                }
            } else if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }
}
