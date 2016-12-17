package service.impl;

import handlers.EntryHandler;
import handlers.ExitHandler;
import models.Car;
import models.ParkingSlot;
import models.Ticket;
import service.ParkingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public class ParkingServiceImpl implements ParkingService {

    private ArrayList<ParkingSlot> parkings = new ArrayList<ParkingSlot>();
    private HashMap<String, Set<Car>> carsColourMap = new HashMap<String, Set<Car>>();
    private HashMap<String, Set<ParkingSlot>> parkingColourMap = new HashMap<String, Set<ParkingSlot>>();
    private HashMap<Car, ParkingSlot> parkingMap = new HashMap<Car, ParkingSlot>();
    private EntryHandler entryHandler;
    private ExitHandler exitHandler;

    public ParkingServiceImpl(){
        entryHandler = new EntryHandler(parkings, carsColourMap, parkingColourMap, parkingMap);
        exitHandler = new ExitHandler(parkings, carsColourMap, parkingColourMap, parkingMap);
    }

    /**
     *
     * @param n
     * @return
     */
    public boolean addParkingSlots(int n) {
        int size = parkings.size();
        try {
            /** Create new parkings*/
            for (int i = 0; i < n; i++){
                ParkingSlot parkingSlot = new ParkingSlot();
                parkingSlot.setIsAvailable(true);
                parkingSlot.setSlotNumber(size + i);
                parkings.add(parkingSlot);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @return
     */
    public ParkingSlot fetchNearestAvailableSlot() {
        return this.entryHandler.fetchNearestAvailableSlot();
    }


    /**
     *
     * @param car
     * @param slot
     * @return
     */
    public Ticket allocateParking(final Car car, final ParkingSlot slot) {
        return this.entryHandler.allocateParking(car, slot);
    }


    /**
     *
     * @param slot
     * @return
     */
    public boolean releaseParking(final ParkingSlot slot) {
        return this.exitHandler.releaseParking(slot);
    }

    /**
     *
     * @param car
     * @return
     */
    public ParkingSlot fetchParkingSlot(final Car car) {
        return parkingMap.get(car);
    }

    /**
     *
     * @param colour
     * @return
     */
    public Set<Car> fetchCarsByColour(final String colour) {
        return carsColourMap.get(colour);
    }

    /**
     *
     * @param colour
     * @return
     */
    public Set<ParkingSlot> fetchParkingsByColour(final String colour) {
        return parkingColourMap.get(colour);
    }

}
