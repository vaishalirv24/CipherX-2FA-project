package services;

import java.util.HashMap;
import java.util.Random;

public class OtpService {

    private HashMap<String, String> otpStorage = new HashMap<>();

    public String generateOtp(String username) {
        Random rand = new Random();
        String otp = String.format("%06d", rand.nextInt(999999));
        otpStorage.put(username, otp);
        return otp;
    }

    public boolean validateOtp(String username, String otp) {
        if (otpStorage.containsKey(username)) {
            return otpStorage.get(username).equals(otp);
        }
        return false;
    }
}
