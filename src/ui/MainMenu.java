package ui;
import api.HotelResource;
import model.Reservation;
import model.Customer;
import model.IRoom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminMenu.populateTestData();
        displayMainMenu(scanner);


    }

    public static void displayMainMenu(Scanner scanner) {
        int selection;

        while (true) {
            System.out.println("Welcome to the Java Luxury Hotel.");
            System.out.println("You are at the main menu. Please pick a selection below:");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");

            do {
                System.out.println("Enter your selection:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1-6:");
                    scanner.nextLine();
                }
                selection = scanner.nextInt();
                scanner.nextLine();
            } while (selection < 1 || selection > 5);

            switch (selection) {
                case 1:
                    bookARoom(scanner);
                    break;
                case 2:
                   seeReservations(scanner);
                   break;
                case 3:
                    createAccount(scanner);
                    break;
                case 4:
                    AdminMenu.displayAdminMenu(scanner);
                    break;
                case 5:
                    System.out.println("Would you like to exit? 'yes'/'no'");
                    String likeToExit = scanner.nextLine();

                    if (likeToExit.equalsIgnoreCase("yes")) {
                        break;
                    } else {
                        displayMainMenu(scanner);
                    }
                    break;
            }
        }
    }
    private static void bookARoom(Scanner scanner) {
        HotelResource hotelResource = HotelResource.getInstance();

        Date checkInDate = null;
        Date checkOutDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        boolean validCheckInDate = false;
        while (!validCheckInDate) {
            System.out.println("Enter your desired check-in date (MM-dd-yyyy):");
            String checkInDateString = scanner.nextLine();
            try {
                checkInDate = dateFormat.parse(checkInDateString);
                validCheckInDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format MM-dd-yyyy.");
            }
        }

        boolean validCheckOutDate = false;
        while (!validCheckOutDate) {
            System.out.println("Enter your desired check-out date (MM-dd-yyyy):");
            String checkOutDateString = scanner.nextLine();
            try {
                checkOutDate = dateFormat.parse(checkOutDateString);
                validCheckOutDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format MM-dd-yyyy.");
            }
        }

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms are available for the selected dates.");
            return;
        }

        System.out.println("Available rooms for the selected dates:");
        for (IRoom availableRoom : availableRooms) {
            System.out.println(availableRoom);
        }

        System.out.println("Do you have a Java Lux Account? (yes/no):");
        String haveAccount = scanner.nextLine();
        Customer customer = null;

        if (haveAccount.equalsIgnoreCase("yes")) {
            System.out.println("Please enter your email:");
            String email = scanner.nextLine();
            customer = hotelResource.getCustomer(email);
        }

        if (customer == null) {
            System.out.println("It seems you don't have an account with us. Create an account before booking.");
            displayMainMenu(scanner);
        } else {
            System.out.println("Enter the room number you want to book:");
            String roomNumber = scanner.nextLine();

            IRoom selectedRoom = null;
            for (IRoom availableRoom : availableRooms) {
                if (availableRoom.getRoomNumber().equals(roomNumber)) {
                    selectedRoom = availableRoom;
                    break;
                }
            }

            if (selectedRoom == null) {
                System.out.println("The room number you entered is not available. Please try again.");
                return;
            }

            Reservation reservation = hotelResource.bookARoom(customer.getEmail(), selectedRoom, checkInDate, checkOutDate);
            System.out.println("Successfully booked the room:");
            System.out.println(reservation);
        }
    }

    private static void createAccount(Scanner scanner) {
        HotelResource hotelResource = HotelResource.getInstance();

        System.out.println("Let's create your Java Lux account.");
        System.out.println("Enter your email : ");
        String email = scanner.nextLine();

        // Check if the email format is valid
        String emailRegX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegX);
        if (!pattern.matcher(email).matches()) {
            System.out.println("Invalid email format. Please reenter your email.");
            displayMainMenu(scanner);
            return;
        }

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();

        hotelResource.createACustomer(email, firstName, lastName);
        System.out.println("Success! Your account has been created.");
    }



    private static void seeReservations(Scanner scanner){
        HotelResource hotelResource = HotelResource.getInstance();
        System.out.println("Enter your email address: ");
        String customerEmail = scanner.nextLine();

        printMyReservations(hotelResource.getCustomersReservations(customerEmail));
    }
    private static void printMyReservations(Collection<Reservation> reservations){

            if (reservations.isEmpty()) {
                System.out.println("You have no rooms reserved.");
            } else {
                System.out.println("Your reservations:");
                for (Reservation reservation : reservations) {
                    System.out.println(reservation);
                }
            }
    }
}

