package services;

import java.util.Random;

public class OtpService {
    private String currentOtp;
    private long expiryTime;
// Generates a 6‑digit OTP and sets expiry time (2 minutes)

    public String generateOtp() {
        Random random = new Random();
        currentOtp = String.format("%06d", random.nextInt(1000000));
        expiryTime = System.currentTimeMillis() + 2 * 60 * 1000; // valid 2 minutes
        return currentOtp;
    }
// Validates the OTP and checks if it is expired

    public boolean validateOtp(String otp) {
        if (System.currentTimeMillis() > expiryTime) {
            return false; // expired
        }
        return otp.equals(currentOtp);
    }
}

