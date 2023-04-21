package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static CustomerService customerService;
    private static ReservationService reservationService;
    private static AdminResource adminResourceInstance;

//    static {
//        reservationService = new ReservationService();
//    }
    private AdminResource() {
        customerService = new CustomerService();
        reservationService = new ReservationService();
    }

    public static AdminResource getInstance() {
        if (adminResourceInstance == null) {
            adminResourceInstance = new AdminResource();
        }
        return adminResourceInstance;
    } //Singleton pattern starter code adapted from https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples, accessed 4/17/23

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms){
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }
    public Collection <IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public static Collection <Customer> getAllCustomers() {

        return CustomerService.getAllCustomers();
    }

    public void addCustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public static Collection<Reservation> displayAllReservations() {
        return reservationService.printAllReservation();
    }
}



