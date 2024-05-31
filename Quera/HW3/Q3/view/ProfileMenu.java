package view;

import controller.ProfileControl;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private ProfileControl control;

    public ProfileMenu(ProfileControl profileControl) {
        this.control = profileControl;
    }

    public void startingMessage() {
        System.out.println("Entered profile menu!");
    }

    public void endingMessage() {
        System.out.println("Entered main menu!");
    }

    public void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;

            if (Commands.BACK.getMatcher(command) != null) return;

            if ((matcher = Commands.CHANGE_PASSWORD.getMatcher(command)) != null)
                System.out.print(control.changePassword(matcher));
            else if (Commands.INFO.getMatcher(command) != null)
                System.out.print(control.info());
            else if ((matcher = Commands.REMOVE_FROM_BATTLE_DECK.getMatcher(command)) != null)
                System.out.print(control.removeFromBattleDeck(matcher));
            else if ((matcher = Commands.ADD_TO_BATTLE_DECK.getMatcher(command)) != null)
                System.out.print(control.addToBattleDeck(matcher));
            else if (Commands.SHOW_BATTLE_DECK.getMatcher(command) != null)
                System.out.print(control.showBattleDeck());
            else if (Commands.SHOW_CURRENT_MENU.getMatcher(command) != null)
                System.out.println("Profile Menu");
            else
                System.out.println("Invalid command!");
        }
    }
}
