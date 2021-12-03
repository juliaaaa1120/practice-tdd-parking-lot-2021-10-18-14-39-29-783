package com.parkinglot;

import java.util.Comparator;
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
                    .max(Comparator.comparing(ParkingLot::getAvailablePosition))
                    .get()
                    .park(car);
        } catch (NoSuchElementException exception) {
            throw new NoAvailablePositionException("No available position.");
        }
    }

    public void parkTo(Car car, ParkingLot parkingLot) {
        parkingLot.park(car);
    }
}
