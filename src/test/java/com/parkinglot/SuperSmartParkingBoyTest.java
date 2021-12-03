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
        ParkingLot firstParkingLot = new ParkingLot(20);
        ParkingLot secondParkingLot = new ParkingLot(30);
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        int i = 0, j = 0;
        while (i < 14) {
            superSmartParkingBoy.parkTo(new Car(), firstParkingLot);
            i++;
        }
        while (j < 21) {
            superSmartParkingBoy.parkTo(new Car(), secondParkingLot);
            j++;
        }

        //when
        Ticket ticket = superSmartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(5, firstParkingLot.getAvailablePosition());
        assertEquals(9, secondParkingLot.getAvailablePosition());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_car_given_super_smart_parking_boy_manage_two_parking_lots_second_has_larger_available_position_rate() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(50);
        ParkingLot secondParkingLot = new ParkingLot(30);
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        int i = 0, j = 0;
        while (i < 32) {
            superSmartParkingBoy.parkTo(new Car(), firstParkingLot);
            i++;
        }
        while (j < 18) {
            superSmartParkingBoy.parkTo(new Car(), secondParkingLot);
            j++;
        }

        //when
        Ticket ticket = superSmartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(18, firstParkingLot.getAvailablePosition());
        assertEquals(11, secondParkingLot.getAvailablePosition());
    }

    @Test
    void should_return_right_car_when_fetch_car_given_super_smart_parking_boy_manage_two_parking_lots_each_with_one_car_and_two_tickets() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car parkedCar1 = new Car();
        Car parkedCar2 = new Car();
        Ticket ticket1 = superSmartParkingBoy.parkTo(parkedCar1, firstParkingLot);
        Ticket ticket2 = superSmartParkingBoy.parkTo(parkedCar2, secondParkingLot);

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
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = smartParkingBoy.park(new Car());
        smartParkingBoy.fetch(ticket);

        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            smartParkingBoy.fetch(ticket);
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
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());

        //when
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
            smartParkingBoy.park(new Car());
        });

        //then
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
