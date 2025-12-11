package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class LogService {

    private final String FILE_PATH = "data/logs.txt";

    public void log(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(message);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Error writing log: " + e.getMessage());
        }
    }
}
