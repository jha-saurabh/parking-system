package service;

import models.Car;
import models.Parking;
import models.ParkingSlot;
import models.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public interface ParkingService {

    void addParkingSlots(int n);
    ParkingSlot fetchNearestAvailableSlot();
    ArrayList<Parking> fetchParkings();
    Ticket allocateParking(final Car car, final ParkingSlot slot);
    boolean releaseParking(int slotNo);
    List<Car> fetchCarsByColour(final String colour);
    ParkingSlot fetchParkingSlot(final Car car);
    List<ParkingSlot> fetchParkingsByColour(final String colour);
    List<ParkingSlot> fetchAllSlots();
}
