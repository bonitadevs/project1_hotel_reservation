package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    private final String emailRegX = "^(.+)@(.+).(.+)$";
    private final Pattern pattern = Pattern.compile(emailRegX);

    public Customer(String first, String second, String mail) {
        if (!pattern.matcher(mail).matches()) {
            throw new IllegalArgumentException("Invalid email format. Please reenter your email.");
        }
        this.firstName = first;
        this.lastName = second;
        this.email = mail;
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
