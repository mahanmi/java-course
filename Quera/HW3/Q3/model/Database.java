package model;

import model.spells.FireBall;
import model.troops.Dragon;
import model.troops.Archer;
import model.troops.Wizard;

import java.util.ArrayList;
import java.util.Collections;

public class Database {
    public static final String[] allCardTypesInput = new String[] { "Fireball", "Heal", "Dragon", "Archer",
            "Wizard" };
    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<User> sortedUsers = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<User> getSortedUsers() {
        Collections.sort(sortedUsers);
        return sortedUsers;
    }

    public static void addUser(String username, String password) {
        User user = new User(username, password);
        users.add(user);
        sortedUsers.add(user);
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static int getRankByUserName(String username) {
        Collections.sort(sortedUsers);
        for (int i = 0; i < sortedUsers.size(); i++) {
            if (sortedUsers.get(i).getUsername().equals(username))
                return i + 1;
        }
        return -1;
    }

    static Card getCard(User owner, String type) {
        switch (type) {
            case "Fireball":
                return new FireBall(owner);
            case "Dragon":
                return new Dragon(owner);
            case "Archer":
                return new Archer(owner);
            case "Wizard":
                return new Wizard(owner);
            default:
                return null;
        }
    }

    public static int getPrice(String type) {
        switch (type) {
            case "Fireball":
                return FireBall.price;
            case "Dragon":
                return Dragon.price;
            case "Archer":
                return Archer.price;
            case "Wizard":
                return Wizard.price;
            default:
                return -1;
        }
    }
}
