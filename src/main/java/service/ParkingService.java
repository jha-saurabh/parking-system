package service;

import models.Car;
import models.ParkingSlot;
import models.Ticket;

import java.util.Set;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public interface ParkingService {

    boolean addParkingSlots(int n);
    ParkingSlot fetchNearestAvailableSlot();
    Ticket allocateParking(final Car car, final ParkingSlot slot);
    boolean releaseParking(final ParkingSlot slot);
    ParkingSlot fetchParkingSlot(final Car car);
    Set<Car> fetchCarsByColour(final String colour);
    Set<ParkingSlot> fetchParkingsByColour(final String colour);

}
