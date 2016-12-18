package concurrent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by takirala on 11/1/2016.
 */
public class volTest {

    volatile static int global = 0;

    public static void main(String[] args) throws Exception {
        ReentrantLock[] locks = new ReentrantLock[5];
        ReentrantLock l = new ReentrantLock();
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws NullPointerException {
                return null;
            }
        };
        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };
        Thread t = new Thread(r);
        t.start();
        //Thread.sleep(1000);
        l.unlock();
    }

    public static void main1(String[] args) throws Exception {


        class Tarun implements Callable<String> {

            @Override
            public String call() throws Exception {
                return null;
            }
        }
        threewayMutex();


        ExecutorService es = Executors.newCachedThreadPool();
        FutureTask<String> ft = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
        Future<?> submit = es.submit(ft);


        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    global++;
                }
            }).start();

        }
        Thread.sleep(4000);
        System.out.println(global);
    }

    private static void threewayMutex() throws InterruptedException {
        Semaphore notSearching = new Semaphore(1);
        Semaphore notInserting = new Semaphore(1);
        Semaphore insertSem = new Semaphore(1);
        Semaphore searchMutex = new Semaphore(1);
        Semaphore insertMutex = new Semaphore(1);
        int numSearcher = 0;
        int numInserter = 0;

        /**
         * Searcher
         */
        searchMutex.acquire();
        numSearcher++;
        if (numSearcher == 1) notSearching.acquire();
        searchMutex.release();

        //perform search

        searchMutex.acquire();
        numSearcher--;
        if (numSearcher == 0) notSearching.release();
        searchMutex.release();


        /**
         * Inserter
         */
        insertMutex.acquire();
        numInserter++;
        if (numInserter == 1) notInserting.acquire();
        insertMutex.release();

        insertSem.acquire();
        //Insert
        insertSem.release();

        insertMutex.acquire();
        numInserter--;
        if (numInserter == 0) notInserting.release();
        insertMutex.release();

        /**
         * Deleter
         */
        notSearching.acquire();
        notInserting.acquire();
        //Delete
        notInserting.release();
        notSearching.release();
    }
}
