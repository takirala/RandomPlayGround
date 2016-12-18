package concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lavenger on 10/9/2016.
 */
public class Reentrant {
    public static void main(String[] args) throws InterruptedException {

        Lock l2 = new ReentrantLock();
        Condition c = l2.newCondition();

        l2.lock();
        //c.await();
        l2.unlock();

        c.signalAll();
        System.out.println("done");
    }

    void run() throws Exception {
        final MCSLock l = new MCSLock();
        l.lock();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                l.lock();
            }
        });
        Thread.sleep(1000);
        l.unlock();
        System.out.println("----");
        //l.lock();
    }

    /**
     * Some bees and bears share a pot of honey. The pot is initially empty and its capacity is H portions of honey.
     * Bee threads repeatedly add single portions of honey to the pot. When the pot is full,
     * the bees stop adding honey and wait for a bear to empty the pot by eating all the honey.
     * Bears sleep until the pot is full, then a bear wakes up and eats all the honey.
     * These bees and bears are extremely cooperative in that when the pot is full, the bees will notify the bears.
     * Similarly, when the pot is empty, the bears with notify the bees. Here is an outline of a honey pot class that
     * provides methods for the bees and the bear.
     * <p>
     * h = num of portions
     * H = max number of portions pot can hold
     * beeAddsHoney(){
     * await(h < H) h++;
     * }
     * <p>
     * bearsEatHoney(){
     * await(h == H) h = 0;
     * }
     */

    class HoneyPot {
        int h = 2;
        int H = 10;
        ReentrantLock lock = new ReentrantLock();
        Condition notFull = lock.newCondition();
        Condition isFull = lock.newCondition();

        void beesAddHoney() {
            lock.lock();
            try {
                while (h >= H) {
                    try {
                        notFull.await();
                    } catch (InterruptedException ig) {

                    }
                }
                //adds honey
                h++;
                if (h == H) isFull.signal();
            } finally {
                lock.unlock();
            }
        }

        void bearsEatHoney() {
            lock.unlock();
            try {
                while (h < H) {
                    try {
                        isFull.await();
                    } catch (InterruptedException ig) {

                    }
                }
                // eat honey
                h = 0;
                notFull.signalAll();
            } finally {
                lock.unlock();
            }

        }
    }

    public class MCSLock {
        class Qnode {
            volatile boolean locked = false;
            volatile Qnode next = null;
        }

        AtomicReference<Qnode> tail =
                new AtomicReference<Qnode>(null);
        ThreadLocal<Qnode> myNode =
                new ThreadLocal<Qnode>() {
                    protected Qnode initialValue() {
                        return new Qnode();
                    }
                };

        public void lock() {
            Qnode qnode = myNode.get();
            Qnode pred = tail.getAndSet(qnode);
            System.out.println("before lockd");
            if (pred != null) {
                System.out.println("lcokedd");
                qnode.locked = true;
                pred.next = qnode;
                while (qnode.locked) {
                    //System.out.println("spin");
                }
            }
        }

        public void unlock() {
            Qnode qnode = myNode.get();
            if (qnode.next == null) {
                if (tail.compareAndSet(qnode, null)) return;
                while (qnode.next == null) {
                    System.out.println("spin unlock");
                }
            }
            qnode.next.locked = false;

        }

    }
}
