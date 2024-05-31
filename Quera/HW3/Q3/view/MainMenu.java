package view;

import controller.MainControl;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private MainControl control;

    public MainMenu(MainControl mainControl) {
        this.control = mainControl;
    }

    public void startingMessage(String username) {
        System.out.printf("User %s logged in!\n", username);
    }

    public void endingMessage(String username) {
        System.out.printf("User %s logged out successfully!\n", username);
    }

    public void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;

            if (Commands.LOGOUT.getMatcher(command) != null) {
                control.logout();
                return;
            }

            if (Commands.LIST_OF_USERS.getMatcher(command) != null) System.out.print(control.listOfUsers());
            else if (Commands.SCOREBOARD.getMatcher(command) != null) System.out.print(control.scoreboard());
            else if (Commands.PROFILE_MENU.getMatcher(command) != null) control.profileMenu();
            else if (Commands.SHOP_MENU.getMatcher(command) != null) control.shopMenu();
            else if ((matcher = Commands.START_GAME.getMatcher(command)) != null)
                System.out.print(control.starGame(matcher));
            else if (Commands.SHOW_CURRENT_MENU.getMatcher(command) != null)
                System.out.println("Main Menu");
            else
                System.out.println("Invalid command!");
        }
    }
}
