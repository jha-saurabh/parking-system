package models;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public class ParkingSlot {
    private boolean isAvailable;
    private int slotNumber;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }
}
