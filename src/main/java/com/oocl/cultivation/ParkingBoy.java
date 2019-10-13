package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = parkingLot.park(car);

        if(ticket == null){
            lastErrorMessage = "Not enough position.";
            return null;
        }

        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car car;

        if(ticket == null) {
            lastErrorMessage = "Please provide your parking ticket";
            return null;
        }

        car = parkingLot.fetch(ticket);

        if(car == null){
            lastErrorMessage = "Unrecognized parking ticket.";
            return null;
        }

        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }


}
