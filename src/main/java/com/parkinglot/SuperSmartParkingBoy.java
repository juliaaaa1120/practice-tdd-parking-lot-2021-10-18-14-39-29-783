package com.parkinglot;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        try {
            return super.getParkingLots().stream()
                    .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
                    .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                    .get()
                    .park(car);
        } catch (NoSuchElementException exception) {
            throw new NoAvailablePositionException("No available position.");
        }
    }
}
