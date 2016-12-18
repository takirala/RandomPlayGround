import sun.reflect.generics.tree.Tree;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Lavenger on 10/14/2016.
 */
public class LeetCode {

    public static void main(String[] args) {
        LeetCode l = new LeetCode();
        l.spiralOrder();
        l.medianFinder();
        System.out.println(l.informatica(7, 4));
        System.out.println(l.isValidParanthesis("{{{}}}"));
        System.out.println(l.SExpression("(B,D) (D,E) (A,B) (B,D) (C,F) (E,G) (A,C) (Q,P)"));

    }

    private void medianFinder() {
        MedianFinder mf = new MedianFinder();
        mf.addNum(-1);
        System.out.println(mf.findMedian());
        mf.addNum(-2);
        System.out.println(mf.findMedian());
        mf.addNum(-3);
        System.out.println(mf.findMedian());
        mf.addNum(-4);
        System.out.println(mf.findMedian());
        mf.addNum(-5);
        System.out.println(mf.findMedian());
    }

    public class MedianFinder {

        TreeSet<Integer> minHeap;
        TreeSet<Integer> maxHeap;

        MedianFinder() {
            minHeap = new TreeSet<Integer>();
            maxHeap = new TreeSet<Integer>(Collections.reverseOrder());
        }

        // Adds a number into the data structure.
        public void addNum(int num) {
            int minSize = minHeap.size();
            int maxSize = maxHeap.size();

            if (minSize == 0) {
                minHeap.add(num);
                return;
            }

            if (maxSize == 0) {
                if (minHeap.first() > num) {
                    minHeap.add(num);
                    maxHeap.add(minHeap.pollFirst());
                    return;
                }
                maxHeap.add(num);
                return;
            }

            if (minHeap.first() < num) {
                maxHeap.add(num);
            } else if (maxHeap.first() > num) {
                minHeap.add(num);
            }

            minSize = minHeap.size();
            maxSize = maxHeap.size();

            if (minSize > maxSize + 1) {
                maxHeap.add(minHeap.pollFirst());
            } else if (maxSize > minSize + 1) {
                minHeap.add(maxHeap.pollFirst());
            }

        }

        // Returns the median of current data stream
        public double findMedian() {
            int minSize = minHeap.size();
            int maxSize = maxHeap.size();

            if (minSize == 0 && maxSize == 0) return 0;

            if (minSize == 0) return maxHeap.first();
            if (maxSize == 0) return minHeap.first();

            if (minSize == maxSize) {
                return maxHeap.first() / 2.0 + minHeap.first() / 2.0;
            }

            if (minSize > maxSize) {
                return minHeap.first();
            }

            if (maxSize > minSize) {
                return maxHeap.first();
            }

            return -1;
        }
    }

    ;


    static String SExpression(String nodes) {
        String arr[] = nodes.split(" ");
        HashMap<Character, Boolean> hm = new HashMap<>();
        HashMap<Character, List<Character>> hk = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {

            char c1 = arr[i].charAt(1);
            char c2 = arr[i].charAt(3);
            if (hm.get(c1) == null) {
                hm.put(c1, true);
                List<Character> l = new ArrayList<>();
                l.add(c2);
                hk.put(c1, l);
                hm.put(c2, false);
            } else if (hm.get(c1) != null) {
                List<Character> l = hk.get(c1);
                if (l == null) {
                    l = new ArrayList<>();
                }
                if (l.contains(c2)) {
                    return "E2";
                }
                l.add(c2);
                hk.put(c1, l);
                hm.put(c2, false);
            }
        }
        List<Character> list = new ArrayList<>(hm.keySet());
        int count = 0;
        Character root = null;
        for (Character s : list) {
            if (hm.get(s)) {
                count++;
                root = s;
            }
        }
        if (count == 0) {
            return ("E3");

        }
        if (count > 1) {
            return ("E4");
        }
        for (Character s : list) {
            if (hk.get(s) == null) {
                continue;
            }
            if (hk.get(s).size() > 2) {
                return ("E1");

            }
        }
        List<Character> ll = hk.get(root);
        String result = helper(root, ll, hk);
        return result;
    }

    public static String helper(Character root, List<Character> ll,
                                HashMap<Character, List<Character>> hk) {
        if (ll == null)
            return "(" + root + ")";
        String result = "(" + root;
        Character c1 = null;
        Character c2 = null;
        if (ll.size() == 2) {
            c1 = ll.get(0);
            c2 = ll.get(1);
        } else if (ll.size() == 1) {
            c1 = ll.get(0);
        }
        if (c2 != null && c1 > c2) {
            Character temp = c1;
            c1 = c2;
            c2 = temp;
        }
        result = result + helper(c1, hk.get(c1), hk);
        if (c2 != null)
            result = result + helper(c2, hk.get(c2), hk);
        result = result + ")";
        return result;
    }

    HashMap<Integer, Integer> map = new HashMap<>();

    boolean isPossible(int a, int b, int c, int d) {

        if (a > c || b > d) return false;
        if (a == c && b == d) return true;
        //way1

        boolean result = isPossible(a + b, b, c, d);
        //way2
        if (!result)
            result = isPossible(a, a + b, c, d);
        if (result) return result;
        return false;
    }

    boolean isValidParanthesis(String str) {

        if (str == null || str.isEmpty() || str.length() == 0) return true;

        Stack<Character> st = new Stack<>();
        for (char c : str.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    st.push(c);
                    break;
                case ')':
                    if (st.isEmpty() || st.pop() != '(') return false;
                    break;
                case '}':
                    if (st.isEmpty() || st.pop() != '{') return false;
                    break;
                case ']':
                    if (st.isEmpty() || st.pop() != '[') return false;
                    break;
            }
        }
        return st.isEmpty();
    }

    int dir = 0; // 0 - R, 1 - D, 2 - L, 3 - T
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    int informatica(int N, int M) {

        int[][] matrix = new int[N][M];
        int[] next = {0, 0};
        int counter = 0;
        while (true) {
            if (next[0] == -1 || next[1] == -1) break;
            //Visit this.
            matrix[next[0]][next[1]] = ++counter;
            //System.out.println(next[0] + "," + next[1] + " : " + matrix[next[0]][next[1]]);
            next = getNextValidPath(matrix, next[0], next[1]);
        }
        return counter;
    }

    private int[] getNextValidPath(int[][] matrix, int row, int col) {
        int[] next = {-1, -1};
        int max = 4;
        while (max > 0) {
            int nextRow = row + dirs[dir][0];
            int nextCol = col + dirs[dir][1];

            if (nextRow >= 0 && nextRow < matrix.length && nextCol >= 0 && nextCol < matrix[0].length
                    && matrix[nextRow][nextCol] == 0) {
                next[0] = nextRow;
                next[1] = nextCol;
                break;
            }
            dir = (dir + 1) % 4;
            max--;
        }
        dir = (dir + 1) % 4;
        return next;
    }

    void spiralOrder() {
    }

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> sol = new ArrayList<Integer>();

        if (matrix == null || matrix.length == 0) return sol;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int left = 0;
        int right = cols - 1;
        int top = 0;
        int bottom = rows - 1;

        while (top < bottom || left < right) {

            //right
            for (int i = left; i <= right; i++) {
                sol.add(matrix[top][i]);
            }
            top++;

            //bottom
            for (int i = top; i <= bottom; i++) {
                sol.add(matrix[i][right]);
            }
            right--;

            //left
            for (int i = right; i >= left; i--) {
                sol.add(matrix[bottom][i]);
            }
            bottom--;

            //up
            for (int i = bottom; i >= top; i--) {
                sol.add(matrix[i][left]);
            }
            left++;
        }
        return sol;
    }

}
