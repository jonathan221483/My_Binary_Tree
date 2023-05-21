public class MyBinarySearchTree<K extends Comparable<K>,V> {
    private Node root;
    private int size;
    private class Node
    {
        private K key;
        private V val;
        private Node left,right;
        public Node(K key, V val)
        {
            this.key = key;
            this.val = val;
        }
    }
    private Node put(Node current, K key, V val) {
        if (current == null) {
            size++;
            return new Node(key, val);
        }
        if (key.compareTo(current.key) < 0){
            current.left = put(current.left, key, val);
        } else{
            current.right = put(current.right, key, val);
        }
        return current;
    }
    public void put(K key,V val){
        root = put(root,key,val);
    }
    public V get(K key) {
        Node found = get(root, key);
        if (found == null)
            return null;
        else
            return found.val;
    }
    private Node get(Node current, K key) {
        if (current == null || key.equals(current.key)) {
            return current;
        }
        if (key.compareTo(current.key) < 0) {
            return get(current.left, key);
        } else {
            return get(current.right, key);
        }
    }
    //public void delete(K key){}
    //public Iterable<K> iterator(){}
}