package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int N;
    private int M;
    private double loadFactor;
    private HashSet<K> allKeys = new HashSet<>();


    /** Constructors */
    public MyHashMap() {
        buckets = createTable(16);
        N = 0;
        M = buckets.length;
        loadFactor = .75;
    }

    public MyHashMap(int initialSize) {
        buckets = createTable(initialSize);
        N = 0;
        M = buckets.length;
        loadFactor = .75;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        N = 0;
        M = buckets.length;
        loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new HashSet();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    @Override
    public void clear() {
        buckets = createTable(16);
        N = 0;
        M = buckets.length;
        loadFactor = .75;
    }

    /* Return the index of the bucket where the input key may be. */
    private int keyFromBucketIndex(K key, int M) {
        return Math.floorMod(key.hashCode(), M);
    }

    @Override
    public boolean containsKey(K key) {
        int bucketIndex = keyFromBucketIndex(key, M);
        Collection<Node> bucket = buckets[bucketIndex];
        if (bucket == null) {
            return false;
        }
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int bucketIndex = keyFromBucketIndex(key, M);
        Collection<Node> bucket = buckets[bucketIndex];
        if (bucket == null) {
            return null;
        }
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    /* Resize the HashMap to input M */
    private void resize(int sizeToRe) {
        Collection<Node>[] newBuckets = createTable(sizeToRe);
        int newM = newBuckets.length;
        for (K key : allKeys) {
            int bucketIndex = keyFromBucketIndex(key, sizeToRe);
            if (newBuckets[bucketIndex] == null) {
                newBuckets[bucketIndex] = createBucket();
            }
            Collection<Node> bucket = newBuckets[bucketIndex];
            Node newNode = new Node(key, get(key));
            bucket.add(newNode);
        }
        buckets = newBuckets;
        M = newM;
    }

    @Override
    public void put(K key, V value) {
        int bucketIndex = keyFromBucketIndex(key, M);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = createBucket();
        }
        Collection<Node> bucket = buckets[bucketIndex];
        if (this.containsKey(key)) {
            for (Node node : bucket) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }
        }
        bucket.add(createNode(key, value));
        N ++;
        allKeys.add(key);
        if ((double) N / M >= loadFactor) {
            resize(M * 2);
        }
    }

    @Override
    public Set<K> keySet() {
        return allKeys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        private Iterator<K> setIterator;
        
        public HashMapIterator() {
            setIterator = allKeys.iterator();
        }
        
        @Override
        public boolean hasNext() {
            return setIterator.hasNext();
        }
        
        @Override
        public K next() {
            return setIterator.next();
        }
    }
}
