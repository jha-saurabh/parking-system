package handlers;

import models.Car;
import models.ParkingSlot;
import models.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public class ExitHandler {

    private ArrayList<ParkingSlot> parkings = new ArrayList<ParkingSlot>();
    private HashMap<String, Set<Car>> carsColourMap = new HashMap<String, Set<Car>>();
    private HashMap<String, Set<ParkingSlot>> parkingColourMap = new HashMap<String, Set<ParkingSlot>>();
    private HashMap<Car, ParkingSlot> parkingMap = new HashMap<Car, ParkingSlot>();

    public ExitHandler(ArrayList<ParkingSlot> parkings, HashMap<String, Set<Car>> carsColourMap,
                 HashMap<String, Set<ParkingSlot>> parkingColourMap, HashMap<Car, ParkingSlot> parkingTicketMap){
        this.parkings = parkings;
        this.carsColourMap = carsColourMap;
        this.parkingColourMap = parkingColourMap;
        this.parkingMap = parkingTicketMap;
    }

    public boolean releaseParking(final ParkingSlot slot) {
        try {
            if (!slot.isAvailable())
                return false;

            //free the parking slot
            slot.setIsAvailable(true);

            //update maps
            Car car = getParkedCar(slot);
            if (car != null){
                parkingColourMap.get(car.getColour()).remove(slot);
                carsColourMap.get(car.getColour()).remove(car);
                parkingMap.remove(car);
            }
            System.out.println("Slot number " + (slot.getSlotNumber() + 1) + " is free");
            return true;
        } catch (Exception e) {
            System.out.println("Exception while releasing parking " + e.getMessage());
            return false;
        }
    }

    private Car getParkedCar(final ParkingSlot slot){
        for (Car car : parkingMap.keySet()){
            if (parkingMap.get(car).getSlotNumber() == slot.getSlotNumber()){
                return car;
            }
        }
        return null;
    }
}
