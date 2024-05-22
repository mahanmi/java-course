package view;

import controller.LoginControl;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private LoginControl control;

    public LoginMenu(LoginControl loginControl) {
        this.control = loginControl;
    }

    public void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;

            if (Commands.EXIT.getMatcher(command) != null) return;

            if ((matcher = Commands.REGISTER.getMatcher(command)) != null)
                System.out.print(control.register(matcher));
            else if ((matcher = Commands.LOGIN.getMatcher(command)) != null)
                System.out.print(control.login(matcher));
            else if (Commands.SHOW_CURRENT_MENU.getMatcher(command) != null)
                System.out.println("Register/Login Menu");
            else
                System.out.println("Invalid command!");
        }
    }
}
