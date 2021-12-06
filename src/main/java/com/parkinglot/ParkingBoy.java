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
        ParkingLot assignedParkingLot = this.parkingLots.stream()
                .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
                .findFirst()
                .orElseThrow(() -> new NoAvailablePositionException("No available position."));
        return assignedParkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot targetedParkingLot = this.parkingLots.stream()
                .filter(parkingLot -> parkingLot.isTheCarParkedInHere(ticket))
                .findFirst()
                .orElseThrow(() -> new UnrecognizedParkingTicketException("Unrecognized parking ticket."));
        return targetedParkingLot.fetch(ticket);
    }
}
