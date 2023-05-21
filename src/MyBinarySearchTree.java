import java.lang.NullPointerException;
import java.util.List;
import java.util.ArrayList;

public class MyBinarySearchTree<K extends Comparable<K>,V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private int size = 1;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node put(Node pointer, K key, V val) {
        if (pointer == null) {
            return new Node(key, val);
        }
        if (key.compareTo(pointer.key) < 0) {
            pointer.left = put(pointer.left, key, val);
        } else {
            pointer.right = put(pointer.right, key, val);
        }
        pointer.size++;
        return pointer;
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    public V get(K key) {
        return get(root, key).val;
    }
    private Node get(Node pointer, K key) throws NullPointerException{
        if (key.compareTo(pointer.key) == 0)
            return pointer;
        else if (key.compareTo(pointer.key) < 0)
            return get(pointer.left, key);
        else
            return get(pointer.right, key);
    }
    public int size(){return root.size;}
    public void delete(K key) {
        root = delete(root, key, true);
    }
    public Node delete(Node pointer, K key,boolean size1) {
        if (pointer == null) {
            return null;
        }
        if (key.compareTo(pointer.key) == 0) {
            if (pointer.left == null) {
                return pointer.right;
            } else if (pointer.size == 1){
                if (pointer.left == null) pointer.right = null;
                else if (pointer.right == null) pointer.left = null;
            }
            else {
                Node temp;
                if (pointer.left.size < pointer.right.size){
                    temp = findMax(pointer.left);
                }
                else{
                    temp = findMin(pointer.right);
                }
                delete(pointer, temp.key, false);
                pointer.key = temp.key;
                pointer.val = temp.val;
            }
        }
        else if (key.compareTo(pointer.key) < 0) {
                pointer.left = delete(pointer.left, key, size1);
            }
        else {
                pointer.right = delete(pointer.right, key, size1);
            }
            return pointer;
        }

    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }
    private Node findMax(Node node){
        if (node.right != null){
            return findMax(node.right);
        }
        return node;
    }

    public Iterable<Pairs<K, V>> iterator(){
        List<Pairs<K, V>> pairs = new ArrayList<>();
        inorderTraversal(root, pairs);
        return pairs;
    }

    public class Pairs<K, V> {
        public K key;
        public V val;
        public Pairs(K key, V value) {
            this.key = key;
            this.val = value;
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