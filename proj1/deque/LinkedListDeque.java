package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private class TNode<T> {
        private T item;
        private TNode<T> next;
        private TNode<T> prev;

        TNode(TNode<T> prev, T item, TNode<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;
    private TNode<T> sentinel;

    public LinkedListDeque() {
        sentinel = new TNode<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        TNode<T> newNode = new TNode<>(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        TNode<T> newNode = new TNode<>(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
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
        TNode<T> current = sentinel.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println(current.item);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T toBeRemoved = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return toBeRemoved;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T toBeRemoved = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return toBeRemoved;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        TNode<T> current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    private T getRecursiveHelper(TNode<T> current, int index) {
        if (index == 0) {
            return current.item;
        }
        return getRecursiveHelper(current.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int currentPos;

        LinkedListIterator() {
            currentPos = 0;
        }

        @Override
        public boolean hasNext() {
            return currentPos < size;
        }

        @Override
        public T next() {
            T returnItem = get(currentPos);
            currentPos++;
            return returnItem;
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
