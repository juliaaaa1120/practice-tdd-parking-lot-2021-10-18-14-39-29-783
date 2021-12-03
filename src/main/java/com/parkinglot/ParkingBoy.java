package com.parkinglot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ParkingBoy {
    HashMap<Ticket, Car> ticketCarMap = new HashMap<>();
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return this.parkingLots.stream()
                .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
                .findFirst()
                .get()
                .park(car);
    }
}
