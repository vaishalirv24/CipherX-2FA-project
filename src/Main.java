import java.util.Scanner;
import models.User;
import services.UserService;
import services.OtpService;
import utils.LogService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        OtpService otpService = new OtpService();
        LogService logger = new LogService();

        System.out.println("=== CipherX 2FA Login System ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            System.out.print("Enter email: ");
            String email = sc.nextLine();

            User u = new User(username, password, email);
            userService.saveUser(u);
            logger.log("User registered: " + username);

            System.out.println("Registration successful!");
        }

        else if (choice == 2) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            boolean isValid = userService.validateLogin(username, password);

            if (!isValid) {
                logger.log("Failed login attempt: " + username);
                System.out.println("Invalid credentials!");
                return;
            }

            String otp = otpService.generateOtp(username);
            logger.log("OTP generated for " + username + ": " + otp);

            System.out.println("Your OTP is: " + otp);
            System.out.print("Enter OTP: ");
            String userOtp = sc.nextLine();

            if (otpService.validateOtp(username, userOtp)) {
                logger.log("Login success: " + username);
                System.out.println("Login successful!");
            } else {
                logger.log("Invalid OTP for: " + username);
                System.out.println("Invalid OTP!");
            }
        }
    }
}
