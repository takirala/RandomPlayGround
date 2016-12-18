package commercehub;

import java.util.HashMap;

/**
 * Created by takirala on 10/24/2016.
 */
public class InventoryManagementSystemImpl implements InventoryManagementSystem {

    private volatile HashMap<String, Integer> inventory = null;

    public InventoryManagementSystemImpl() {
        inventory = new HashMap<>();
    }

    @Override
    public PickingResult pickProduct(String productId, int amountToPick) {

        PickingResult result = new PickingResult();
        if (productId == null || productId.isEmpty()) {
            result.setResultStatus(Result.Status.INVALID_PRODUCT_ID);
            return result;
        }

        result.setProductId(productId);
        if (amountToPick <= 0) {
            // Considering 0 as also invalid
            result.setResultStatus(Result.Status.INVALID_AMOUNT);
            return result;
        }

        //Read the value.
        Integer previousStock = inventory.get(productId);
        if (previousStock == null) {
            // No product found. Return an exception.
            result.setResultStatus(Result.Status.NO_PRODUCT_FOUND);
            return result;
        }

        // This is a design choice, we can either throw and exception or return whatever of stock is left.
        if (previousStock < amountToPick) {
            result.setResultStatus(Result.Status.PICK_FAILED_DUE_TO_LOW_STOCK);
            return result;
        }

        // Now acquire a lock to do some non thread safe operations.
        synchronized (this) {
            previousStock = inventory.get(productId);
            Integer newStock = previousStock - amountToPick;
            inventory.put(productId, newStock);
            result.setStockLeft(newStock);
        }

        result.setCurrentPick(amountToPick);
        result.setResultStatus(Result.Status.SUCCESS);
        return result;
    }

    @Override
    public RestockingResult restockProduct(String productId, int amountToRestock) {

        RestockingResult result = new RestockingResult();
        if (productId == null || productId.isEmpty()) {
            result.setResultStatus(Result.Status.INVALID_PRODUCT_ID);
            return result;
        }

        result.setProductId(productId);
        if (amountToRestock <= 0) {
            // Considering 0 as also invalid
            result.setResultStatus(Result.Status.INVALID_AMOUNT);
            return result;
        }

        Integer newStock = amountToRestock;

        // Now acquire a lock to do some non thread safe operations.
        synchronized (this) {
            //Read the value.
            Integer previousStock = inventory.get(productId);
            if (previousStock != null) {
                newStock += previousStock;
            }
            inventory.put(productId, newStock);
        }

        result.setCurrentRestock(amountToRestock);
        result.setStockLeft(newStock);
        result.setResultStatus(Result.Status.SUCCESS);
        return result;
    }
}
