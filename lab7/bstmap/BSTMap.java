package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K,V> {

    int size = 0;
    BSTNode root;

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left, right;
        private BSTNode parent;
    }

    public BSTMap() {
        root = new BSTNode();
    }

    @Override
    public void clear() {
        size = 0;
        BSTNode emptyNode = new BSTNode();
        root = emptyNode;
    }

    @Override
    public boolean containsKey(K key) {
        return isNodeKey(key, root);
    }

    private boolean isNodeKey(K key, BSTNode node) {
        if (node.key == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0 && node.left != null) {
            return isNodeKey(key, node.left);
        } else if (key.compareTo(node.key) > 0 && node.right != null) {
            return isNodeKey(key, node.right);
        } else {
            return false;
        }
    }

    @Override
    public V get(K key) {
        return getNodeVal(key, root);
    }

    private V getNodeVal(K key, BSTNode node) {
        if (node.key == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node.value;
        } else if (key.compareTo(node.key) < 0 && node.left != null) {
            return getNodeVal(key, node.left);
        } else if (key.compareTo(node.key) > 0 && node.right != null) {
            return getNodeVal(key, node.right);
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (root.key == null) {
            root.key = key;
            root.value = value;
            size++;
        } else {
            put(key, value, root);
        }
    }

    private void put(K key, V value, BSTNode node) {
        if (key.compareTo(node.key) < 0) {
            if(node.left == null) {
                BSTNode newNode = new BSTNode();
                newNode.key = key;
                newNode.value = value;
                node.left = newNode;
                newNode.parent = node;
                size++;
            } else {
                put(key, value, node.left);
            }
        } else {
            if(node.right == null) {
                BSTNode newNode = new BSTNode();
                newNode.key = key;
                newNode.value = value;
                node.right = newNode;
                newNode.parent = node;
                size++;
            } else {
                put(key, value, node.right);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    public static void main(String[] args) {

    }
}
