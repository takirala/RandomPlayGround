package concurrent;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by takirala on 11/7/2016.
 */
public class ConcurrentListTester {

    static ConcurrentSearcherList<Integer> l = new ConcurrentSearcherList<>();

    static Vector<Integer> insertsList = new Vector<>();
    static Vector<Integer> searchesList = new Vector<>();
    static Vector<Integer> removesList = new Vector<>();

    static ConcurrentHashMap<Integer, Boolean> searchesMap = new
            ConcurrentHashMap<>();
    static ConcurrentHashMap<Integer, Boolean> deletesMap = new
            ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        //inserts
        inserts(10);
        searches(10);
        removes(10);
        Thread.sleep(3000);

        System.out.println("Inserts " + insertsList.toString());
        System.out.println("Searches " + searchesList.toString());
        System.out.println("Removes " + removesList.toString());

        Thread.sleep(3000);

        System.out.println(searchesMap.toString());
        System.out.println(deletesMap.toString());

        //l.print();
        Thread.sleep(1000);

    }

    private static void inserts(int i) throws InterruptedException {
        for (int j = 0; j < i; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int next = new Random().nextInt(100);
                        insertsList.add(next);
                        l.insert(next);
                    } catch (InterruptedException e) {
                        System.out.println("Insert");
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void removes(int i) throws InterruptedException {
        for (int j = 0; j < i; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int next = new Random().nextInt(100);
                        removesList.add(next);
                        boolean res = l.remove(next);
                        deletesMap.put(next, res);
                    } catch (InterruptedException e) {
                        System.out.println("remove");
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void searches(int i) throws InterruptedException {
        for (int j = 0; j < i; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int next = new Random().nextInt(100);
                        searchesList.add(next);
                        boolean res = l.search(next);
                        searchesMap.put(next, res);
                    } catch (InterruptedException e) {
                        System.out.println("search");
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
