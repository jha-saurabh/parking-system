package handlers;

import models.Car;
import models.Parking;
import models.ParkingSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public class ExitHandler {

    private ArrayList<ParkingSlot> parkingSlots;
    private HashMap<String, Set<Car>> carsColourMap = new HashMap<String, Set<Car>>();
    private HashMap<String, Set<ParkingSlot>> parkingColourMap = new HashMap<String, Set<ParkingSlot>>();
    private HashMap<Integer, Parking> parkingMap = new HashMap<Integer, Parking>();

    public ExitHandler(ArrayList<ParkingSlot> parkings, HashMap<String, Set<Car>> carsColourMap,
                 HashMap<String, Set<ParkingSlot>> parkingColourMap, HashMap<Integer, Parking> parkingTicketMap){
        this.parkingSlots = parkings;
        this.carsColourMap = carsColourMap;
        this.parkingColourMap = parkingColourMap;
        this.parkingMap = parkingTicketMap;
    }

    /**
     *
     * @param parking
     * @return
     */
    public boolean releaseParking(final Parking parking) {
        if (parking == null || parking.getSlot() == null || parking.getCar() == null)
            return false;

        ParkingSlot slot = parking.getSlot();
        if (slot.isAvailable())
            return false;

        //free the parking slot
        slot.setIsAvailable(true);

        parkingMap.remove(slot.getSlotNumber());

        //update maps
        Car car = parking.getCar();
        parkingColourMap.get(car.getColour()).remove(slot);
        carsColourMap.get(car.getColour()).remove(car);

        return true;
    }

}
