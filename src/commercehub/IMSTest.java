package commercehub;

/**
 * Created by takirala on 10/24/2016.
 */

import static org.junit.Assert.*;

import org.junit.Test;


public class IMSTest {
    static InventoryManagementSystem inventoryMS = null;

    static {
        inventoryMS = new InventoryManagementSystemImpl();
    }

    public static void main(String[] args) {

        IMSTest t = new IMSTest();
        t.testInvalidProductIdPickUp();
        t.testInvalidProductIdRestock();
        t.testRestock();
        t.testPickUp();
        t.testInvalidAmountPickUp();
        t.testInvalidAmountRestock();
        t.testConcurrentRestocks();
        t.testConcurrentPickups();
        System.out.println("All test cases passed!");
    }

    @Test
    synchronized void testInvalidProductIdPickUp() {
        assertEquals(Result.Status.INVALID_PRODUCT_ID, inventoryMS.pickProduct(null, -1).getResultStatus());
    }

    @Test
    void testInvalidAmountPickUp() {
        assertEquals(Result.Status.INVALID_AMOUNT, inventoryMS.pickProduct("Prod1", -1).getResultStatus());
    }

    @Test
    void testInvalidProductIdRestock() {
        assertEquals(Result.Status.INVALID_PRODUCT_ID, inventoryMS.pickProduct(null, -1).getResultStatus());
    }

    @Test
    void testInvalidAmountRestock() {
        assertEquals(Result.Status.INVALID_AMOUNT, inventoryMS.pickProduct("Prod1", -1).getResultStatus());
    }

    @Test
    void testPickUp() {
        assertEquals(Result.Status.SUCCESS, inventoryMS.pickProduct("Prod1", 1).getResultStatus());
    }

    @Test
    void testRestock() {
        assertEquals(Result.Status.SUCCESS, inventoryMS.restockProduct("Prod1", 10).getResultStatus());
    }

    @Test
    void testConcurrentRestocks() {
        final String productId = "RandomName";
        // 20 threads add 5 items of same product id.
        final int noOfThreads = 20;
        final int eachRestock = 5;
        Thread[] threads = new Thread[noOfThreads];

        for (int i = 0; i < noOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    assertEquals(inventoryMS.restockProduct(productId, eachRestock).getResultStatus(), Result.Status.SUCCESS);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(inventoryMS.pickProduct(productId, 100).getResultStatus(), Result.Status.SUCCESS);
    }

    @Test
    void testConcurrentPickups() {
        //Restock 100 items and pick them all up concurrently.
        final String productId = "RandomPickName";
        inventoryMS.restockProduct(productId, 100);
        // 20 threads add 5 items of same product id.
        final int noOfThreads = 20;
        final int eachRestock = 5;
        Thread[] threads = new Thread[noOfThreads];

        for (int i = 0; i < noOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    assertEquals(inventoryMS.pickProduct(productId, eachRestock).getResultStatus(), Result.Status.SUCCESS);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Product should be all picked. Pick some random value of product amount (which will fail)
        assertEquals(inventoryMS.pickProduct(productId, 1).getResultStatus(), Result.Status.PICK_FAILED_DUE_TO_LOW_STOCK);
    }
}
