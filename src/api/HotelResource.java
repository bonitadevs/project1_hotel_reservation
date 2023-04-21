package api;


import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static CustomerService customerService ;
    private static ReservationService reservationService;
    private static HotelResource hotelResourceInstance;
    private HotelResource() {
        customerService = new CustomerService();
        reservationService = new ReservationService();
    }

    public static HotelResource getInstance() {
        if (hotelResourceInstance == null) {
            hotelResourceInstance = new HotelResource();
        }
        return hotelResourceInstance;
    } //Singleton pattern code adapted from https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples, accessed 4/17/23

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom (String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom (String email, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = getCustomer(email);
        if (customer == null) {
            throw new IllegalArgumentException("Email: " + email + " is not found. Try again.");
        }
            reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
            return new Reservation(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }
}
