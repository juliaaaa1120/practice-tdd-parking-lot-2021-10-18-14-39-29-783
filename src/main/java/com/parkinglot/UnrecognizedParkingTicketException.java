package com.parkinglot;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public UnrecognizedParkingTicketException (String message) {
        super(message);
    }
}
