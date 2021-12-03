package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    HashMap<Ticket, Car> ticketCarMap = new HashMap<>();
    private final int DEFAULT_CAPACITY = 10;
    private final int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public Ticket park(Car car) {
        if (hasAvailablePosition()) {
            Ticket ticket = new Ticket();
            ticketCarMap.put(ticket, car);
            return ticket;
        } else {
            return null;
        }
    }

    private boolean hasAvailablePosition() {
        return ticketCarMap.size() < this.capacity;
    }

    public Car fetch(Ticket ticket) {
        if (isTheTicketNotUsed(ticket)) {
            Car returnCar = ticketCarMap.get(ticket);
            ticketCarMap.remove(ticket);
            return returnCar;
        } else {
            return null;
        }
    }

    private boolean isTheTicketNotUsed(Ticket ticket) {
        return ticketCarMap.get(ticket) != null;
    }
}
