public class Main {
    public static void main(String[] args) {
        MyBinarySearchTree<Integer,String> tree = new MyBinarySearchTree();
        tree.put(5,"Ruslan");
        tree.put(3,"d");
        System.out.println((String) tree.get(5));
        //System.out.printf((String) tree.get(5));
        for (MyBinarySearchTree.Pairs elem : tree.iterator()) {
            System.out.println("key is " + elem.key + " and value is " + elem.val);
        }
    }
}