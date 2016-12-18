package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by takirala on 10/28/2016.
 */
public class intToChars {

    static char[] chars = new char[27];

    public static void main(String[] args) {
        String input = "13256789109201293";
        char start = 'a';
        for (int i = 1; i <= 26; i++) {
            chars[i] = start++;
        }
        List<String> sols = new ArrayList<String>();

        System.out.println(input.substring(1, 1));
        //generate(input, -1, sols, "");
    }

    private static void generate(String input, int i, List<String> sols, String current) {
        if (i >= input.length()) {
            sols.add(current);
        }


    }

    boolean isValid(String str, int from, int end) {
        String sub = str.substring(from, end);
        //if()
        return true;
    }
}
