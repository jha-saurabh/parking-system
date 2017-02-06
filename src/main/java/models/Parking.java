package models;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public class Parking {

    private ParkingSlot slot;
    private Car car;

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
