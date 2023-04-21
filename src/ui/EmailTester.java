package ui;
import java.util.regex.Pattern;
public class EmailTester {

        public static void main(String[] args) {
            String emailRegEx = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            Pattern pattern = Pattern.compile(emailRegEx);

            String testEmail = "dani@java.com";

            if (pattern.matcher(testEmail).matches()) {
                System.out.println("Valid email");
            } else {
                System.out.println("Invalid email");
            }
        }

}
