package com.gojek.parking.controller;

import com.gojek.parking.ParkingManager;
import com.gojek.parking.commons.Constants;
import com.gojek.parking.models.Car;
import com.gojek.parking.models.Parking;
import com.gojek.parking.models.ParkingSlot;
import com.gojek.parking.models.Ticket;
import com.gojek.parking.service.ParkingService;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public class ParkingController {

    private static ParkingService parkingService;

    public static void main(String args[]){
        try {
            parkingService = ParkingManager.getService();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String sCurrentLine = "";
            while (!"EXIT".equalsIgnoreCase(sCurrentLine = br.readLine())){
                File file = new File(sCurrentLine);
                if (file.exists()){
                    readFromFile(sCurrentLine);
                } else {
                    performAction(sCurrentLine);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while processing input" + e.getMessage());
        }
    }

    private static void readFromFile(String sCurrentLine){
        try {
            BufferedReader br = new BufferedReader(new FileReader(sCurrentLine));
            while ((sCurrentLine = br.readLine()) != null){
                performAction(sCurrentLine);
            }
        } catch (IOException e) {
            System.out.println("Exception while reading file " + e.getMessage());
        }

    }

    private static void performAction(String query) {
        String queries[] = query.split(" ");
        if (Constants.CREATE_PARKING.equals(queries[0])){
            createParkings(parkingService, Integer.parseInt(queries[1]));
        } else if (Constants.ALLOCATE_PARKING.equals(queries[0])){
            allocateParking(parkingService, queries[1], queries[2]);
        } else if (Constants.CARS_COLOUR_QUERY.equals(queries[0])){
            displayCarsByColour(parkingService, queries[1]);
        } else if (Constants.PARKING_COLOUR_QUERY.equals(queries[0])){
            displayParkingByColour(queries[1]);
        } else if (Constants.PARKING_SLOT_QUERY.equals(queries[0])){
            displayParkingSlot(queries[1]);
        } else if (Constants.PARKING_STATUS.equals(queries[0])){
            displayParkingStatus();
        } else if (Constants.RELEASE_PARKING.equals(queries[0])){
            releaseParking(Integer.parseInt(queries[1]));
        }

    }

    private static void createParkings(ParkingService parkingService, int n){
        try {
            parkingService.addParkingSlots(n);
            System.out.println("Created a parking lot with " + n +  " slots");
        } catch (Exception e) {
            System.out.println("Exception while creating parkings " + e.getMessage());
        }
    }

    private static void allocateParking(ParkingService parkingService, String registerNo, String colour){
        try {
            Car car = new Car();
            car.setRegisterationNo(registerNo);
            car.setColour(colour);
            Ticket ticket = parkingService.allocateParking(car, parkingService.fetchNearestAvailableSlot());
            if (ticket != null){
                System.out.println("Allocated slot number: " + (ticket.getParking().getSlot().getSlotNumber() + 1));
            } else {
                System.out.println("Sorry, parking lot is full");
            }
        } catch (Exception e) {
            System.out.println("Exception while allocating parking " + e.getMessage());
        }
    }

    private static void releaseParking(int slotNo){

        try {
            parkingService.releaseParking(slotNo - 1);
            System.out.println("Slot number " + (slotNo) + " is free");
        } catch (Exception e) {
            System.out.println("Exception while releasing parking " + e.getMessage());
        }
    }

    private static void displayParkingStatus(){
        ArrayList<Parking> parkings = parkingService.fetchParkings();
        System.out.print("Slot No.\t" + "Registration No\t" + "Colour");
        System.out.println();
        for (Parking parking : parkings){
            ParkingSlot slot = parking.getSlot();
            Car car = parking.getCar();
            System.out.print((slot.getSlotNumber() + 1) + "\t" + car.getRegisterationNo() + "\t" + car.getColour() );
            System.out.println();
        }
    }

    private static void displayCarsByColour(ParkingService parkingService, String colour){
        List<Car> carSet = parkingService.fetchCarsByColour(colour);
        String cars = "";
        Iterator<Car> iterator = carSet.iterator();
        while (iterator.hasNext()){
            cars += iterator.next().getRegisterationNo() + ",";
        }
        System.out.println(cars.substring(0, cars.length() - 1));
    }

    private static void displayParkingByColour(String colour){
        List<ParkingSlot> parkingSet = parkingService.fetchParkingsByColour(colour);
        String parkingList = "";
        Iterator<ParkingSlot> iterator = parkingSet.iterator();
        while (iterator.hasNext()){
            parkingList += iterator.next().getSlotNumber() + 1 + ",";
        }
        System.out.println(parkingList.substring(0, parkingList.length() - 1));
    }

    private static void displayParkingSlot(String registerationNo){
        Car car = new Car();
        car.setRegisterationNo(registerationNo);
        ParkingSlot slot = parkingService.fetchParkingSlot(car);
        if (slot == null)
            System.out.println("Not found");
        else System.out.println(slot.getSlotNumber() + 1);
    }
}
