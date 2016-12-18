package concurrent;

import java.util.concurrent.*;

/**
 * Created by takirala on 10/31/2016.
 */
public class Executor {

    public static void main(String[] args) {

    }

    static void cachedThreadPool() {
        ExecutorService es = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });
        Future<Long> g = es.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return 1L;
            }
        });
    }
}
