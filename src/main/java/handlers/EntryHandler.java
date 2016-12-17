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
public class EntryHandler {

    private ArrayList<ParkingSlot> parkings = new ArrayList<ParkingSlot>();
    private HashMap<String, Set<Car>> carsColourMap = new HashMap<String, Set<Car>>();
    private HashMap<String, Set<ParkingSlot>> parkingColourMap = new HashMap<String, Set<ParkingSlot>>();
    private HashMap<Car, ParkingSlot> parkingTicketMap = new HashMap<Car, ParkingSlot>();

    public EntryHandler(ArrayList<ParkingSlot> parkings, HashMap<String, Set<Car>> carsColourMap,
                 HashMap<String, Set<ParkingSlot>> parkingColourMap, HashMap<Car, ParkingSlot> parkingTicketMap){
        this.parkings = parkings;
        this.carsColourMap = carsColourMap;
        this.parkingColourMap = parkingColourMap;
        this.parkingTicketMap = parkingTicketMap;
    }

    public ParkingSlot fetchNearestAvailableSlot() {
        return null;
    }

    public Ticket allocateParking(final Car car, final ParkingSlot slot) {
        return null;
    }
}
