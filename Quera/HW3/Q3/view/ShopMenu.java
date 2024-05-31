package view;

import controller.ShopControl;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    private ShopControl control;

    public ShopMenu(ShopControl shopControl) {
        this.control = shopControl;
    }

    public void startingMessage() {
        System.out.println("Entered shop menu!");
    }

    public void endingMessage() {
        System.out.println("Entered main menu!");
    }

    public void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;

            if (Commands.BACK.getMatcher(command) != null) return;

            if ((matcher = Commands.BUY_CARD.getMatcher(command)) != null)
                System.out.print(control.buyCard(matcher));
            else if ((matcher = Commands.SELL_CARD.getMatcher(command)) != null)
                System.out.print(control.sellCard(matcher));
            else if (Commands.SHOW_CURRENT_MENU.getMatcher(command) != null)
                System.out.println("Shop Menu");
            else
                System.out.println("Invalid command!");
        }
    }
}
