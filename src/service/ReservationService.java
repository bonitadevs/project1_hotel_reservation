package service;

import model.Customer;
import model.IRoom;
import model.Reservation;


import java.util.*;

public class ReservationService {
    private static final Map<String, IRoom> rooms = new HashMap<>();
    //empty room hashmap that will store all rooms by RoomID
    private static final Collection<Reservation> reservations = new ArrayList<>(); //collection that stores an empty array list for all the reservations

    public ReservationService() {
    }

    public void addRoom(IRoom room) { //adds room to rooms hashmap
        rooms.put(room.getRoomNumber(), room);  //adds room to the hashmap using room # as key
    }


    public IRoom getARoom(String roomId) {
        return rooms.get(roomId);
    }  //retrieves from by roomID

    public void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) { //create reservation ArrayList
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate); //create Reservation object with these parameters
        reservations.add(reservation); //adds new reservation to the ArrayList
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : rooms.values()) {
            if (isRoomAvailable(room, checkInDate, checkOutDate)) {
                System.out.println("The room " + room.getRoomNumber() + " is available for the requested dates: " +
                        "Check-in: " + checkInDate + ", Check-out: " + checkOutDate);
                availableRooms.add(room);
            } else if (isRoomAvailable(room, recommendedDates(checkInDate, 7), recommendedDates(checkOutDate, 7))) {
                System.out.println("The room " + room.getRoomNumber() + " is not available for the requested dates, but it is available for the recommended dates: " +
                        "Check-in: " + recommendedDates(checkInDate, 7) + ", Check-out: " + recommendedDates(checkOutDate, 7));
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }


    public Collection<IRoom> getAllRooms(){
        return new ArrayList<>(rooms.values());
    }

    private boolean isRoomAvailable (IRoom room, Date checkInDate, Date checkOutDate) { //checks if rooms are available on given date
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room) && !(reservation.getCheckOutDate().before(checkInDate) || reservation.getCheckInDate().after(checkOutDate))) {
                return false;
            }
        }
        return true;
    }

    private Date recommendedDates (Date date, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); //take requested dates as an argument
        calendar.add(Calendar.DATE, days); //add 7 days to the requested dates for the recommended dates
        return calendar.getTime(); //return recommended dates
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>(); //store reservations based on customer input
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            } //retrieves customer's reservation based on customer input
        }
        return customerReservations;
    } //return customer's reservation


    public Collection<Reservation> printAllReservation() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
        return reservations;
    }
}


