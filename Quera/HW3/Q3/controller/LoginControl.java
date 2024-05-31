package controller;

import model.Database;
import view.LoginMenu;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginControl {
    private Scanner scanner;

    public LoginControl(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        LoginMenu loginMenu = new LoginMenu(this);
        loginMenu.run(scanner);
    }


    static boolean isPasswordFormatWrong(String password) {
        if (password.contains(" ")) return true;
        if (password.length() < 8 || password.length() > 20) return true;
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*"))
            return true;
        if (!password.matches(".*[0-9].*")) return true;
        if (password.charAt(0) >= '0' && password.charAt(0) <= '9') return true;
        return !password.matches(".*[!@#$%^&*].*");
    }

    public String register(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        if (username.matches(".*[^a-zA-Z].*")) return Errors.USERNAME_FORMAT.toString();
        if (isPasswordFormatWrong(password)) return Errors.PASSWORD_FORMAT.toString();
        if (Database.getUserByUsername(username) != null) return Errors.USERNAME_EXISTS.toString();
        Database.addUser(username, password);
        return String.format("User %s created successfully!\n", username);
    }

    public String login(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        if (username.matches(".*[^a-zA-Z].*")) return Errors.USERNAME_FORMAT.toString();
        if (isPasswordFormatWrong(password)) return Errors.PASSWORD_FORMAT.toString();
        if (Database.getUserByUsername(username) == null) return Errors.USERNAME_NOT_EXIST.toString();
        if (Objects.requireNonNull(Database.getUserByUsername(username)).isPasswordWrong(password))
            return Errors.INCORRECT_PASSWORD.toString();
        MainControl mainControl = new MainControl(scanner, Database.getUserByUsername(username));
        mainControl.run();
        return "";
    }
}
