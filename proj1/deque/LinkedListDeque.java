package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {

    private class TNode<T>{
        public T item;
        public TNode<T> next;
        public TNode<T> prev;

        public TNode(TNode prev, T item, TNode next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    int size;
    public TNode sentinel;

    public LinkedListDeque() {
        sentinel = new TNode<T>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new TNode(null, item, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {

        TNode newNode = new TNode(sentinel, item, sentinel.next);

        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        size++;
    }

    public void addLast(T item) {

        TNode newNode = new TNode(sentinel.prev, item, sentinel);

        sentinel.prev.next = newNode;
        sentinel.prev = newNode;

        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T printDeque() {

        TNode current = sentinel.next;

        for (int i = 0; i < size; i++) {
            System.out.print(current.item);
            System.out.print(" ");
            current = current.next;
        }
        System.out.println('\n');

        return null;
    }

    public T removeFirst() {

        if (size == 0) {
            return null;
        }

        T toBeRemoved = (T) sentinel.next.item;

        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;

        return toBeRemoved;
    }

    public T removeLast() {

        if (size == 0) {
            return null;
        }

        T toBeRemoved = (T) sentinel.prev.item;

        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;

        return toBeRemoved;
    }

    public T get(int index) {

        if (index < 0 || index >= size) {
            return null;
        }

        TNode current = sentinel.next;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return (T) current.item;
    }

    private T getRecursiveHelper(TNode<T> current, int index) {
        if (index == 0) {
            return current.item;
        } else {
            return getRecursiveHelper(current.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper((TNode<T>) sentinel.next, index);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int currentPos;
        public LinkedListIterator() {
            currentPos = 0;
        }
        public boolean hasNext() {
            return currentPos < size;
        }
        public T next() {
            T returnItem = (T) get(currentPos);
            currentPos++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof LinkedListDeque otherDeque) {
            for (int i = 0; i < size; i++) {
                if (this.get(i) != otherDeque.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
