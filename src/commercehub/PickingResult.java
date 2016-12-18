package commercehub;

/**
 * Created by takirala on 10/24/2016.
 */
public class PickingResult extends Result {

    int currentPick;

    public int getCurrentPick() {
        return currentPick;
    }

    public void setCurrentPick(int currentPick) {
        this.currentPick = currentPick;
    }

    @Override
    public String toString() {
        return "Product ID : " + productId +
                ", Stock Left : " + stockLeft +
                ", Current Pick : " + currentPick +
                " , Result : " + resultStatus;
    }
}
