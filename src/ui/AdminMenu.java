package ui;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import static ui.MainMenu.displayMainMenu;

public class AdminMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayAdminMenu(scanner);
    }

    public static void displayAdminMenu(Scanner scanner) {
        int selection;

        while (true) {
            System.out.println("Welcome Java Luxury Hotel Administrator.");
            System.out.println("You are at the admin menu. Please pick a selection below:");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add a room");
            System.out.println("5. Populate Test Data");
            System.out.println("6. Back to Main Menu");

            System.out.println("Enter your selection:");

            selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 1:
                    seeAllCustomers();
                    break;
                case 2:
                    seeAllRooms();
                    break;
                case 3:
                    seeAllReservations();
                    break;
                case 4:
                    addNewRoom(scanner);
                    break;
                case 5:
                    populateTestData();
                    break;
                case 6:
                    System.out.println("Would you like to exit back to the Main Menu? 'yes'/'no'");
                    String likeToExit = scanner.nextLine();

                    if (likeToExit.equalsIgnoreCase("yes")) {
                        System.out.println("Successfully signed out!");
                        displayMainMenu(scanner);
                    } else {
                        displayAdminMenu(scanner);
                    }
                    break;

            }
        }
    }

    static void populateTestData() {
        AdminResource adminResource = AdminResource.getInstance();
        adminResource.addCustomer("baby.ares@testdata.com", "Ares", "Stone");
        adminResource.addCustomer("josethecat@testdata.com", "Jose", "Kitten");
        adminResource.addCustomer("percythedog@testdata.com", "Percy", "Puppy");
        adminResource.addCustomer("lonely.fly@testdata.com", "Paula", "Lopez");


        IRoom room = new Room ("213", 199.00, RoomType.DOUBLE, false);
        AdminResource.addRoom(Collections.singletonList(room));

        IRoom room1 = new Room ("210", 159.00, RoomType.SINGLE, false);
        AdminResource.addRoom(Collections.singletonList(room1));

        IRoom room2 = new Room ("120", 169.00, RoomType.SINGLE, true);
        AdminResource.addRoom(Collections.singletonList(room2));

        IRoom room3 = new Room ("300", 229.00, RoomType.DOUBLE, false);
        AdminResource.addRoom(Collections.singletonList(room3));

    }
    private static void seeAllCustomers(){
        Collection<Customer> customers = AdminResource.getAllCustomers();
        if(customers.isEmpty()) {
            System.out.println("There are no customers.");
        } else {
            System.out.println("List of Customers: ");
            for (Customer customer : customers ) {
                System.out.println(customer);
            }
        }

    }

    private static void seeAllRooms() {
        Collection<IRoom> room = AdminResource.getInstance().getAllRooms();
        if (room.isEmpty()) {
            System.out.println("There are no rooms.");
        } else {
            System.out.println("List of Rooms : ");
            for (IRoom rooms : room) {
                System.out.println(rooms); // Print individual reservation
            }
        }
    }

    private static void seeAllReservations(){
        Collection<Reservation> reservations = AdminResource.displayAllReservations();
        assert reservations != null;
        if(reservations.isEmpty()) {
            System.out.println("There are no reservations.");
        } else {
            System.out.println("List of Reservations: ");
            for (Reservation reservation : reservations ) {
                System.out.println(reservation); // Print individual reservation
            }
        }

    }
    private static void addNewRoom(Scanner scanner) {
        AdminResource adminResource = AdminResource.getInstance();

        System.out.println("Add a New Room");
        System.out.println("Enter the room number: ");
        String roomNumber = scanner.nextLine();

        System.out.println("Enter the price: ");
        double roomPrice;

        try {
            roomPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please try again.");
            return;
        }


        System.out.println("Enter room type: SINGLE or DOUBLE ");
        String roomTypeInput = scanner.nextLine().toUpperCase();
        RoomType roomType;
        try {
            roomType = RoomType.valueOf(roomTypeInput);
        }catch (IllegalArgumentException e){
            System.out.println("Invalid room type. Must use SINGLE or DOUBLE");
            return;
        }

        System.out.println("Is this room free? Enter 'yes' or 'no' :");
        String isFreeResponse = scanner.nextLine().toUpperCase();
        boolean isFree;
        if (isFreeResponse.equals("YES")) {
            isFree = true;
        }else if (isFreeResponse.equals("NO")) {
            isFree = false;
        } else {
            System.out.println("Invalid input for room price.");
            return;
        }

        IRoom newRoom = new Room(roomNumber, roomPrice, roomType, isFree);
        AdminResource.addRoom(Collections.singletonList(newRoom));
        System.out.println("The room has been successfully added to the list.");
    }


}
