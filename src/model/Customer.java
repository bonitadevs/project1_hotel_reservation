package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public static String emailRegX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public Customer(String email, String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailPattern(email);
    }

    private void emailPattern(String email) {
        Pattern pattern = Pattern.compile(emailRegX);
        //added exception to MainMenu
    }


    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "Customer:" +
                "First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Email: " + email +
                '}';
    }
}
