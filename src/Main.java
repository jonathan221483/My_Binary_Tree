public class Main {
    public static void main(String[] args) {
        MyBinarySearchTree tree = new MyBinarySearchTree();
        tree.put(5,"Ruslan");
        tree.put(3,"d");
        System.out.printf((String) tree.get(5));
        tree.delete(5);
        System.out.printf((String) tree.get(5));
    }
}