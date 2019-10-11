package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {

    @Test
    @DisplayName("AC1: should return parkingTicket when parkingBoy is given a car")
    void should_return_a_parking_ticket_when_parking_boy_given_a_car_into_parking_lot() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        //When
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //Then
        assertNotNull(parkingTicket);
    }

    @Test
    @DisplayName("AC1: should return car when given ticket")
    void should_return_car_when_parking_boy_is_given_ticket_to_fetch_car() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(car);

        //When
        car = parkingBoy.fetch(parkingTicket);

        //Then
        assertNotNull(car);
    }

    @Test
    @DisplayName("AC2: should return correct car when given correct ticket")
    void should_return_correct_car_by_parking_boy_using_given_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car sportsCar = new Car();
        ParkingTicket parkingTicketSportsCar = parkingBoy.park(sportsCar);

        //When
        car = parkingBoy.fetch(parkingTicket);
        sportsCar = parkingBoy.fetch(parkingTicketSportsCar);
        //Then

        assertNotEquals(car.hashCode(), sportsCar.hashCode());
        assertNotSame(car, sportsCar);
    }

    @Test
    @DisplayName("AC3: should not fetch car if wrong parkingTicket is given")
    void should_not_fetch_car_if_wrong_parking_ticket_is_given() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Car car;

        //When
        car = parkingBoy.fetch(wrongParkingTicket);

        //Then
        assertNull(car);
    }

    @Test
    @DisplayName("AC3: should not fetch car if no ticket is given")
    void should_not_fetch_car_if_no_ticket_is_given() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car;

        //When
        car = parkingBoy.fetch(null);

        //Then
        assertNull(car);
    }

    @Test
    @DisplayName("AC4: should not fetch car when ticket is already been used")
    void should_not_fetch_car_if_ticket_is_already_been_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //When
        car = parkingBoy.fetch(parkingTicket);
        car = parkingBoy.fetch(parkingTicket);

        //Then
        assertNull(car);
    }

    @Test
    @DisplayName("AC5: should not give parking ticket if parking lot is already full")
    void should_not_park_ticket_if_parking_lot_is_already_full() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket finalParkingTicket = new ParkingTicket();

        //When
        Car finalCar = new Car();
        for(int i = 0; i <= 10; i++){
            finalParkingTicket = parkingBoy.park(finalCar);
        }

        //Then
        assertNull(finalParkingTicket);
    }


}
