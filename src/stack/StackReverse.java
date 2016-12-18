package stack;

import java.util.Stack;

/**
 * Created by Lavenger on 9/30/2016.
 */
public class StackReverse {

    public static void main(String[] args) {

        Stack<Integer> st = new Stack<Integer>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        st.push(6);
        reverse(st);
        while (!st.isEmpty()) {
            System.out.println(st.pop());
        }
    }


    private static void reverse(Stack<Integer> st) {
        int size = st.size();
        while (size > 0) {
            int last = rev(st);
            System.out.println("#" + last);
            st.push(last);
            size--;
        }
    }

    private static int rev(Stack<Integer> st) {
        if (st.size() == 1) return st.pop();
        int cur = st.pop();
        int last = rev(st);
        st.push(cur);
        return last;
    }


}
