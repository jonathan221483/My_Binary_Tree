public class Main {
    public static void main(String[] args) {
        MyBinarySearchTree<Integer,String> tree = new MyBinarySearchTree();
        tree.put(5,"Ruslan");
        tree.put(8,"Daniya");
        tree.put(23,"Alikhan");
        tree.put(12,"Alua");
        tree.put(3,"Elvira");
        for (MyBinarySearchTree.Pairs elem : tree) {
            System.out.println("key is " + elem.key + " and value is " + elem.val);
        }
    }
}