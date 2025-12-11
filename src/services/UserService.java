package services;

import models.User;
import java.io.*;
import java.util.ArrayList;

public class UserService {

    private final String FILE_PATH = "data/users.txt";

    public ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    users.add(new User(data[0], data[1], data[2]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users: " + e.getMessage());
        }

        return users;
    }

    public void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(user.toString());
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public boolean validateLogin(String username, String password) {
        ArrayList<User> users = loadUsers();

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
