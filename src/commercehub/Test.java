package commercehub;

/**
 * Created by takirala on 11/3/2016.
 */
public class Test {

    public static void main(String[] args) {
        String s = "asdasdasd";
        long t1 = System.currentTimeMillis();
        for (long i = 0; i < 10 || i < 9999999999L; i++) {
            s = "" + s;
            int a = 1;
        }
        System.out.println(System.currentTimeMillis() - t1);
    }

    static void TEST() {

    }
}
