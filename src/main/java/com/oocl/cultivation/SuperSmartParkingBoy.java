package com.oocl.cultivation;

import java.math.BigDecimal;
import java.util.List;

import static com.oocl.cultivation.SmartParkingBoy.NOT_ENOUGH_POSITION;

public class SuperSmartParkingBoy extends ParkingBoy{


    @Override
    public ParkingTicket park(Car car) {
        List<ParkingLot> parkingLotList = getParkingLotList();

        ParkingTicket ticket;
        ParkingLot fetchedParkingLot = parkingLotList.stream()
                .reduce(this::getLargerPositionRate)
                .orElse(null);

        if(fetchedParkingLot != null){
            ticket = fetchedParkingLot.park(car);
            return ticket;
        }

        setLastErrorMessage(NOT_ENOUGH_POSITION);
        return null;

    }

    private ParkingLot getLargerPositionRate(ParkingLot parkLotOne, ParkingLot parkLotTwo) {
        return calculatePositionRate(parkLotTwo) > calculatePositionRate(parkLotOne) ? parkLotTwo : parkLotOne;
    }

    private Double calculatePositionRate(ParkingLot parkingLot){
        return Double.valueOf(Math.abs(parkingLot.getAvailableParkingPosition())) / parkingLot.getCapacity();
    }
}
