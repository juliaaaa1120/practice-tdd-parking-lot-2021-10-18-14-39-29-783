package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {
    List<ParkingLot> parkingLots = new ArrayList<>();

    @Test
    void should_park_to_first_parking_lot_when_park_car_given_super_smart_parking_boy_manage_two_parking_lots_both_with_same_available_position_rate() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot();
        secondParkingLot.park(new Car());
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        Ticket ticket = superSmartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(8, firstParkingLot.getAvailablePosition());
        assertEquals(9, secondParkingLot.getAvailablePosition());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_car_given_super_smart_parking_boy_manage_two_parking_lots_second_has_larger_available_position_rate() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot(6);
        secondParkingLot.park(new Car());
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        Ticket ticket = superSmartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(7, firstParkingLot.getAvailablePosition());
        assertEquals(4, secondParkingLot.getAvailablePosition());
    }

    @Test
    void should_return_right_car_when_fetch_car_given_super_smart_parking_boy_manage_two_parking_lots_each_with_one_car_and_two_tickets() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car parkedCar1 = new Car();
        Car parkedCar2 = new Car();
        Ticket ticket1 = superSmartParkingBoy.park(parkedCar1);
        Ticket ticket2 = superSmartParkingBoy.park(parkedCar2);

        //when
        Car returnCar1 = superSmartParkingBoy.fetch(ticket1);
        Car returnCar2 = superSmartParkingBoy.fetch(ticket2);

        //then
        assertEquals(parkedCar1, returnCar1);
        assertEquals(parkedCar2, returnCar2);
    }

    @Test
    void should_throw_unrecognized_parking_ticket_exception_when_fetch_car_given_super_smart_parking_boy_manage_two_parking_lots_and_unrecognized_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            superSmartParkingBoy.fetch(new Ticket());
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_unrecognized_parking_ticket_exception_when_fetch_car_given_super_smart_parking_boy_manage_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Ticket ticket = superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.fetch(ticket);

        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            superSmartParkingBoy.fetch(ticket);
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_no_available_position_exception_when_park_car_given_super_smart_parking_boy_manage_two_parking_lots_both_full_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.park(new Car());

        //when
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
            superSmartParkingBoy.park(new Car());
        });

        //then
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
