package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_car_given_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_null_when_park_car_given_full_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);

        //when
        Ticket ticket = parkingLot.park(car);

        //then
        assertNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_car_given_parking_lot_and_car_and_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car parkedCar = new Car();

        //when
        Ticket ticket = parkingLot.park(parkedCar);
        Car returnCar = parkingLot.fetch(ticket);

        //then
        assertEquals(parkedCar, returnCar);
    }

    @Test
    void should_return_right_car_when_fetch_car_given_parking_lot_and_2_cars_and_2_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car parkedCar1 = new Car();
        Car parkedCar2 = new Car();

        //when
        Ticket ticket1 = parkingLot.park(parkedCar1);
        Ticket ticket2 = parkingLot.park(parkedCar2);
        Car returnCar1 = parkingLot.fetch(ticket1);
        Car returnCar2 = parkingLot.fetch(ticket2);

        //then
        assertEquals(returnCar1, parkedCar1);
        assertEquals(returnCar2, parkedCar2);
    }
}
