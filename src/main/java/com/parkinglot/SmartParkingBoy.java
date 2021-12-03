package com.parkinglot;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class SmartParkingBoy {
    HashMap<Ticket, Car> ticketCarMap = new HashMap<>();
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        try {
            return this.parkingLots.stream()
                    .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
                    .findFirst()
                    .get()
                    .park(car);
        } catch (NoSuchElementException exception) {
            throw new NoAvailablePositionException("No available position.");
        }
    }

    public Ticket parkTo(Car car, ParkingLot parkingLot) {
        return parkingLot.park(car);
    }
}
