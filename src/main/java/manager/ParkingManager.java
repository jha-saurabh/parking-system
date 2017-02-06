package manager;


import service.ParkingService;
import service.impl.ParkingServiceImpl;

/**
 * Created by saurabh.jha on 17/12/16.
 */
public class ParkingManager {

    public static ParkingService parkingService;

    public static ParkingService getService(){
        if (parkingService == null){
            synchronized(ParkingManager.class){
                if (parkingService == null)
                    parkingService = new ParkingServiceImpl();
            }
        }
        return parkingService;
    }
}
