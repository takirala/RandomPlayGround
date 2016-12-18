package commercehub;

/**
 * Created by takirala on 10/24/2016.
 */
public class Result {
    enum Status {
        INVALID_PRODUCT_ID,
        INVALID_AMOUNT,
        SUCCESS,
        NO_PRODUCT_FOUND,
        PICK_FAILED_DUE_TO_LOW_STOCK,
        RESTOCK_FAILED_DUE_TO_OVER_STOCK,
    }

    String productId;
    int stockLeft;
    Status resultStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getStockLeft() {
        return stockLeft;
    }

    public void setStockLeft(int stockLeft) {
        this.stockLeft = stockLeft;
    }

    public Status getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Status resultStatus) {
        this.resultStatus = resultStatus;
    }


}
