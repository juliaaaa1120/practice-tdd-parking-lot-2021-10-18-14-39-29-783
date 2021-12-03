package com.parkinglot;

import java.util.*;

public class ParkingBoy {
    HashMap<Ticket, Car> ticketCarMap = new HashMap<>();
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
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

    public Car fetch(Ticket ticket) {
        try {
            return this.parkingLots.stream()
                    .filter(parkingLot -> parkingLot.isTheCarParkedInHere(ticket))
                    .findFirst()
                    .get()
                    .fetch(ticket);
        } catch (NoSuchElementException exception) {
            throw new UnrecognizedParkingTicketException("Unrecognized parking ticket.");
        }
    }
}
