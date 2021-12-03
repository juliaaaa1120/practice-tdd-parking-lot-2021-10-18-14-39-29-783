package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    List<ParkingLot> parkingLots = new ArrayList<>();

    @Test
    void should_return_ticket_when_park_car_given_standard_parking_boy_manage_one_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingLot.park(new Car());

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_park_to_first_parking_lot_when_park_car_given_standard_parking_boy_manage_two_parking_lots_both_available() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(9, firstParkingLot.getAvailablePosition());
        assertEquals(10, secondParkingLot.getAvailablePosition());
    }
}
