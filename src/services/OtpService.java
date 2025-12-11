package services;

import java.util.Random;

public class OtpService {
    private String currentOtp;
    private long expiryTime;

    public String generateOtp() {
        Random random = new Random();
        currentOtp = String.format("%06d", random.nextInt(1000000));
        expiryTime = System.currentTimeMillis() + 2 * 60 * 1000; // valid 2 minutes
        return currentOtp;
    }

    public boolean validateOtp(String otp) {
        if (System.currentTimeMillis() > expiryTime) {
            return false; // expired
        }
        return otp.equals(currentOtp);
    }
}

