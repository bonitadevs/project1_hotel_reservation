package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ReservationService {
    private static final Map<String, IRoom> rooms = new HashMap<>();
    //empty room hashmap that will store all rooms by RoomID
    private static final Collection<Reservation> reservations = new ArrayList<>(); //collection that stores an empty array list for all the reservations

    public ReservationService() {
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }


    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }

    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        //returns a collection of IRoom objects between check-in and checkout dates.
        Collection<IRoom> availableRooms = new ArrayList<>();
        //creates an empty array list named availableRooms to store available rooms for that data range, can store any object that implements the IRoom interface
        for (IRoom room : rooms.values()) { //for loops iterates through room map to check for available rooms that implements IRoom interface.
            boolean isRoomAvailable = true; // check if the current room is available during the given date range
            for (Reservation reservation : reservations) { // another loop that iterates through all the reservation objects in the reservation collection
                if (reservation.getRoom().equals(room) &&
                        !(reservation.getCheckOutDate().before(checkInDate) || reservation.getCheckInDate().after(checkOutDate))) {
                    isRoomAvailable = false; //room isn't available stop the loop
                    break;
                }
            }
            if (isRoomAvailable) {
                availableRooms.add(room); //room is available and added to the available room list
            }
        }
        return availableRooms; //available rooms within the requested date range
    }


    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservation() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}

