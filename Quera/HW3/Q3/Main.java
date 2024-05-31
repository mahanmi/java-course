import controller.LoginControl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginControl loginControl = new LoginControl(new Scanner(System.in));
        loginControl.run();
    }
}
