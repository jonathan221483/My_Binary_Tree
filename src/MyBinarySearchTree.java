import java.lang.NullPointerException;
import java.util.Stack;
import java.util.Iterator;

public class MyBinarySearchTree<K extends Comparable<K>,V> implements Iterable<MyBinarySearchTree.Pairs> {
    private Node root; //first and main node of binary tree

    private class Node {
        private K key; //key of node - will be used for comparing
        private V val; //value of node
        private int size = 1; //size of binary tree
        private Node left, right; //children of node

        public Node(K key, V val) { //node constructor
            this.key = key;
            this.val = val;
        }
    }

    private Node put(Node pointer, K key, V val) { //recursive method for nodes needed to implement insert method
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
    } //insert new data into binary tree

    public V get(K key) { //get data by a key from binary tree
        return get(root, key).val;
    }
    private Node get(Node pointer, K key) throws NullPointerException{ //recursive method for nodes needed to implement get method
        if (key.compareTo(pointer.key) == 0)
            return pointer;
        else if (key.compareTo(pointer.key) < 0)
            return get(pointer.left, key);
        else
            return get(pointer.right, key);
    }
    public int size(){return root.size;} //returns the size of binary tree
    public void delete(K key) { //deletes data by key from binary tree
        root = delete(root, key, true);
    }
    public Node delete(Node pointer, K key,boolean size1) { //recursive method for nodes needed to implement deleting
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

    private Node findMin(Node node) { //returns node with minimum key
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }
    private Node findMax(Node node){ //returns node with maximum key
        if (node.right != null){
            return findMax(node.right);
        }
        return node;
    }
    public boolean consist(K key){
        try {
            if (!consist(root, key).equals(null))
                return true;
        }
        catch (NullPointerException e)
        {
            return false;
        }
            return false;
    }
    private Node consist(Node node, K key){
        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return get(node.left, key);
        else
            return consist(node.right, key);
    }



    public Iterator<MyBinarySearchTree.Pairs> iterator(){ //iterator needed to implement in-order traverse
        return new MyIterator();
    }

    private class MyIterator implements Iterator<MyBinarySearchTree.Pairs>{ //Myiterator class
        private Stack<Pairs> stack;
        public MyIterator(){
            this.stack = new Stack<>();
            inOrder(root);
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        @Override
        public Pairs next() {
            return stack.pop();
        }
        private void inOrder(Node node){ //in-order traverse
            if (node == null){
                return;
            }
            inOrder(node.right);
            this.stack.push(new Pairs(node.key, node.val)); //it pushes every element from binary tree into stack in order with their keys
            inOrder(node.left);
        }
    }

    public class Pairs<K, V> { //class for key-value pairs of nodes in binary tree
        public K key;
        public V val;
        public Pairs(K key, V value) { //constructor for pairs
            this.key = key;
            this.val = value;
        }
    }
}