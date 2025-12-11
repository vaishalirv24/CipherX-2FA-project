package services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogService {

    private String logFile = "data/logs.txt";

    public void log(String message) {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(LocalDateTime.now() + " - " + message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing log: " + e.getMessage());
        }
    }
}
