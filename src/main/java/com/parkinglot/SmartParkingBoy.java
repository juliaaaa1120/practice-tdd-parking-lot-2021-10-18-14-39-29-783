package com.parkinglot;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot assignedParkingLot = super.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
                .max(Comparator.comparing(ParkingLot::getAvailablePosition))
                .orElseThrow(() -> new NoAvailablePositionException("No available position."));
        return assignedParkingLot.park(car);
    }
}
