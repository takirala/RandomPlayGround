import java.util.Comparator;

/**
 * Created by takirala on 10/19/2016.
 */
public class Test {
    public static void main(String[] args) {
        char c[] = new char[2];
        Comparator<String> comp = (String f, String s) -> f.length() - s.length();
        sendEmail(() -> System.out.print("tarun"));
        System.out.println(toHex(-1));
    }

    public static void sendEmail(Runnable runnable) {
        runnable.run();
    }


    public static String toHex(int num) {
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        if (num == 0) return "0";
        String result = "";
        while (num != 0) {
            result = map[(num & 15)] + result;
            num = (num >>> 4);
        }
        return result;
    }

}
