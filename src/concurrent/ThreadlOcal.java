package concurrent;

public class ThreadlOcal {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> local = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "Eureka";
            }
        };
        Thread.currentThread();

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(local.get());
                    local.set("tarun" + Thread.currentThread().getName());
                }
            }
        });
        t.start();

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(local.get());
                    local.set("gupta" + Thread.currentThread().getName());
                }
            }
        });
        t2.start();

        //t.join();
        //t2.join();

        System.out.println("both done processinng!");
    }
}
