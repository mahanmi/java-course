package controller;

import model.Database;
import model.User;
import view.MainMenu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainControl {
    private Scanner scanner;
    private User currentUser;
    private MainMenu mainMenu;

    MainControl(Scanner scanner, User user) {
        this.scanner = scanner;
        currentUser = user;
    }

    void run() {
        mainMenu = new MainMenu(this);
        mainMenu.startingMessage(currentUser.getUsername());
        mainMenu.run(scanner);
    }

    public void logout() {
        mainMenu.endingMessage(currentUser.getUsername());
    }

    public String listOfUsers() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Database.getUsers().size(); i++)
            builder.append(String.format("user %d: %s\n", i + 1, Database.getUsers().get(i).getUsername()));
        return builder.toString();
    }

    public String scoreboard() {
        StringBuilder builder = new StringBuilder();
        ArrayList<User> list = Database.getSortedUsers();
        for (int i = 0; i < list.size() && i < 5; i++) {
            builder.append(String.format("%d- username: %s level: %d experience: %d\n",
                    i + 1, list.get(i).getUsername(), list.get(i).getLevel(), list.get(i).getExperience()));
        }
        return builder.toString();
    }

    public void profileMenu() {
        ProfileControl profileControl = new ProfileControl(scanner, currentUser);
        profileControl.run();
    }

    public void shopMenu() {
        ShopControl shopControl = new ShopControl(scanner, currentUser);
        shopControl.run();
    }

    public String starGame(Matcher matcher) {
        int turns = Integer.parseInt(matcher.group("turns"));
        String username = matcher.group("username");
        if (turns < 5 || turns > 30) return Errors.TURNS_COUNT.toString();
        if (username.matches(".*[^a-zA-Z].*")) return Errors.USERNAME_FORMAT.toString();
        if (Database.getUserByUsername(username) == null) return Errors.USERNAME_NOT_EXIST.toString();
        GameControl gameControl = new GameControl(scanner, currentUser, Database.getUserByUsername(username), turns);
        gameControl.run();
        return "";
    }
}
