public class MyBinarySearchTree<K extends Comparable<K>,V> {
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

}