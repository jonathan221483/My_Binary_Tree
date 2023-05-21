import java.lang.NullPointerException;
import java.util.List;
import java.util.ArrayList;

public class MyBinarySearchTree<K extends Comparable<K>,V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node put(Node pointer, K key, V val) {
        if (pointer == null) {
            size++;
            return new Node(key, val);
        }
        if (key.compareTo(pointer.key) < 0) {
            pointer.left = put(pointer.left, key, val);
        } else {
            pointer.right = put(pointer.right, key, val);
        }
        return pointer;
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    public V get(K key) {
        Node found = get(root, key);
        if (found == null)
            return null;
        else
            return found.val;
    }

    private Node get(Node pointer, K key) throws NullPointerException{
        if (pointer == null || key.equals(pointer.key)) {
            return pointer;
        }

        if (key.compareTo(pointer.key) < 0)
            return get(pointer.left, key);
        else
            return get(pointer.right, key);
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    public Node delete(Node pointer, K key) {
        if (pointer == null) {
            return null;
        }
        if (key.compareTo(pointer.key) < 0) {
            pointer.left = delete(pointer.left, key);
        } else if (key.compareTo(pointer.key) > 0) {
            pointer.right = delete(pointer.right, key);
        } else {
            if (pointer.left == null) {
                size--;
                return pointer.right;
            } else if (pointer.right == null) {
                size--;
                return pointer.left;
            } else {
                Node child = findMin(pointer.right);
                pointer.key = child.key;
                pointer.val = child.val;
                pointer.right = delete(pointer.right, child.key);
            }
        }
        return pointer;
    }

    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    public Iterable<Pairs<K, V>> iterator(){
        List<Pairs<K, V>> pairs = new ArrayList<>();
        inorderTraversal(root, pairs);
        return pairs;
    }

    public class Pairs<K, V> {
        private K key;
        private V value;
        public Pairs(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private void inorderTraversal(Node node, List<Pairs<K, V>> pairs) {
        if (node != null) {
            inorderTraversal(node.left, pairs);
            pairs.add(new Pairs<>(node.key, node.val));
            inorderTraversal(node.right, pairs);
        }
    }


}