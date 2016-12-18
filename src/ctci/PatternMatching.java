package ctci;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashMap;

public class PatternMatching {

    public static void main(String[] args) {
        String input = "aaaaa";
        String pattern = "aaaaa";

        System.out.println(++aChar);

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        boolean doMatch = doMatch(pattern, input, 0, map);
        System.out.println(doMatch);
    }

    static Integer aChar = 1;

    private static boolean doMatch(String pattern, String input, int index, HashMap<Integer, String> map) {
        if (index >= input.length()) return true;
        for (int i = index + 1; i < input.length(); i++) {
            // for each possible first string
            String inital = input.substring(index, i);

            boolean res = false;

            if (map.containsValue(inital)) {
                res = doMatch(pattern, input, i, map);
            }

            if (res) return res;

            Integer temp = aChar++;
            System.out.println(temp + " -> " + inital + " ");
            map.put(temp, inital);
            res = doMatch(pattern, input, i, map);
            if (res) return res;
            map.remove(temp);

        }
        return false;
    }

    Character getByValue(HashMap<Character, String> map, String value) {
        for (Character c : map.keySet()) {
            if (map.get(c).equals(value)) return c;
        }
        return null;
    }

}
