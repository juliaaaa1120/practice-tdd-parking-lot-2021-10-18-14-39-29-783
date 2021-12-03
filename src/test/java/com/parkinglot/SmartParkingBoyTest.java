package com.parkinglot;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    List<ParkingLot> parkingLots = new ArrayList<>();

    @Test
    void should_park_to_first_parking_lot_when_park_car_given_smart_parking_boy_manage_two_parking_lots_both_same_empty_position() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        int i = 0;
        while (i < 5) {
            smartParkingBoy.parkTo(new Car(), firstParkingLot);
            smartParkingBoy.parkTo(new Car(), secondParkingLot);
            i++;
        }

        //when
        Ticket ticket = smartParkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(4, firstParkingLot.getAvailablePosition());
        assertEquals(5, secondParkingLot.getAvailablePosition());
    }

    @Test
    void should_park_to_second_parking_lot_when_park_car_given_smart_parking_boy_manage_two_parking_lots_first_full_and_second_available() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        int i = 0;
        while (i < 10) {
            parkingBoy.park(new Car());
            i++;
        }

        //when
        Ticket ticket = parkingBoy.park(new Car());

        //then
        assertNotNull(ticket);
        assertEquals(0, firstParkingLot.getAvailablePosition());
        assertEquals(9, secondParkingLot.getAvailablePosition());
    }

    @Test
    void should_return_right_car_when_fetch_car_given_smart_parking_boy_manage_two_parking_lots_each_with_one_car_and_two_tickets() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car parkedCar1 = new Car();
        Car parkedCar2 = new Car();
        Ticket ticket1 = parkingBoy.parkTo(parkedCar1, firstParkingLot);
        Ticket ticket2 = parkingBoy.parkTo(parkedCar2, secondParkingLot);

        //when
        Car returnCar1 = parkingBoy.fetch(ticket1);
        Car returnCar2 = parkingBoy.fetch(ticket2);

        //then
        assertEquals(parkedCar1, returnCar1);
        assertEquals(parkedCar2, returnCar2);
    }

    @Test
    void should_throw_unrecognized_parking_ticket_exception_when_fetch_car_given_smart_parking_boy_manage_two_parking_lots_and_unrecognized_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetch(new Ticket());
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_unrecognized_parking_ticket_exception_when_fetch_car_given_smart_parking_boy_manage_two_parking_lots_and_used_ticket() {
        //given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = parkingBoy.park(new Car());
        parkingBoy.fetch(ticket);

        //when
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingBoy.fetch(ticket);
        });

        //then
        assertEquals("Unrecognized parking ticket.", unrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_throw_no_available_position_exception_when_park_car_given_smart_parking_boy_manage_two_parking_lots_both_full_and_car() {
        //given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        //when
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
            parkingBoy.park(new Car());
        });

        //then
        assertEquals("No available position.", noAvailablePositionException.getMessage());
    }
}
