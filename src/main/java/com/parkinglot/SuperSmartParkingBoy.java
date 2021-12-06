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
        ParkingLot assignedParkingLot = super.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElseThrow(() -> new NoAvailablePositionException("No available position."));
        return assignedParkingLot.park(car);
    }
}
