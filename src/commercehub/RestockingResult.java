package commercehub;

/**
 * Created by takirala on 10/24/2016.
 */
public class RestockingResult extends Result {

    int currentRestock;

    public int getCurrentRestock() {
        return currentRestock;
    }

    public void setCurrentRestock(int currentRestock) {
        this.currentRestock = currentRestock;
    }

    @Override
    public String toString() {
        return "Product ID : " + productId +
                ", Stock Left : " + stockLeft +
                " , Current Restock : " + currentRestock +
                " , Result : " + resultStatus;
    }
}
