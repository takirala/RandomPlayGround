package concurrent;

/**
 * Created by takirala on 10/17/2016.
 */
public class Runn {
    public static void main(String[] args) {
        Thread t  = new T();
        t.start();
    }

}

class T extends Thread{

    @Override
    public void run() {
        System.out.println("run");
    }

    public void start() {
        System.out.println("start");
    }
}
