package companies;

import java.util.ArrayList;

public class BST {
    public final int data;
    public BST left = null;
    public BST right = null;

    public BST(int data, BST left, BST right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BST(int data) {
        this(data, null, null);
    }

    public String toString() {
        if (this.left == null && this.right == null) return "" + this.data;
        else {
            String lstr = (this.left == null) ? "_" : this.left.toString();
            String rstr = (this.right == null) ? "_" : this.right.toString();
            return "(" + lstr + " " + this.data + " " + rstr + ")";
        }
    }

    public static void main(String[] args) {
        BST testBst = new BST(8,
                new BST(3,
                        new BST(1),
                        new BST(6,
                                new BST(4),
                                new BST(7))),
                new BST(10,
                        null,
                        new BST(14,
                                new BST(13),
                                null)));

        System.out.println(testBst);

        for (int n = 0; n < 10; n++)
            System.out.println("" + n + "th smallest: " + testBst.nthSmallest(n));

        // 0th smallest: 1
        // 1th smallest: 3
        // 2th smallest: 4
        // 3th smallest: 6
        // ...
        // 8th smallest: 14
        // 9th smallest: None
    }

    /**
     * Returns the value of the nth smallest node in this
     * binary search tree.
     */
    public int nthSmallest(int n) {
        // YOUR CODE HERE

        ArrayList<Integer> list = new ArrayList<>();
        inOrder(this, n, list);
        System.out.println(list);
        if(list.size() > n) {
            return list.get(n);
        } else return -1;

    }

    private void inOrder(BST node, int n, ArrayList<Integer> list) {

        if (node.left != null) inOrder(node.left, n, list);
        list.add(node.data);
        if(list.size() > n) return;
        if (node.right != null) inOrder(node.right, n, list);
    }
}