package model;

import java.util.Calendar;

public class Reservation {
    private Customer customer;
    private IRoom room;
    Calendar checkInDate = Calendar.getInstance();;

    Calendar checkOutDate = Calendar.getInstance();;

    @Override
    public String toString() {
        return "Reservation: " +
                "Customer: " + customer +
                ", Room: " + room +
                ", Check In: " + checkInDate +
                ", Check Out: " + checkOutDate +
                '}';
    }
}
