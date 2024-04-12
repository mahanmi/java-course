import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    String input, email, password;

    ArrayList<String> emails = new ArrayList<String>();
    ArrayList<String> passwords = new ArrayList<String>();

    input = scanner.nextLine().trim();

    while (!input.equals("end")) {
      if (input.equals("Sign up")) {

        email = scanner.nextLine().trim();

        if (email.matches(".*@gmail\\.com")) {

          boolean isEmailUsed = false;

          for (String newEmail : emails) {
            if (newEmail.equals(email)) {
              System.out.println("this address is already used");
              isEmailUsed = true;
              break;
            }
          }

          if (!isEmailUsed) {
            password = scanner.nextLine().trim();

            if (checkPassword(password)) {
              emails.add(email);
              passwords.add(password);
              System.out.println("account created !");
            }

          }

        } else {
          System.out.println("email is not correct");
        }

      } else if (input.equals("Log in")) {

        email = scanner.nextLine().trim();

        if (email.matches(".*@gmail\\.com")) {

          int index = -1;

          for (int i = 0; i < emails.size(); i++) {
            if (emails.get(i).equals(email)) {
              index = i;
              break;
            }
          }

          if (index == -1) {
            System.out.println("The email address provided is not registered");
          } else {
            password = scanner.nextLine().trim();

            if (password.equals(passwords.get(index))) {
              System.out.println("just logged in!");
            } else {
              System.out.println("password in not correct");
            }
          }

        } else {
          System.out.println("email is not correct");
        }

      } else if (input.equals("Print all Emails")) {
        for (String newEmail : emails) {
          System.out.println(newEmail);
        }
      } else if (input.equals("Check")) {
        String search = scanner.nextLine().trim();
        for (String newEmail : emails) {
          if (newEmail.replace("@gmail.com", "").contains(search)) {
            System.out.println(newEmail);
          }
        }
      }

      input = scanner.nextLine().trim();

    }

    scanner.close();

  }

  public static boolean checkPassword(String password) {

    if (password.length() < 8) {
      System.out.println("The password must be at least 8 characters long");
      return false;
    }

    if (!password.matches(".*[0-9].*") || !password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
      System.out.println("Your password needs to include numbers , upper and lower case characters");
      return false;
    }

    for (int i = 0; i < password.length() - 2; i++) {
      if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
        System.out.println("Passwords should not contain more than two identical, consecutive characters");
        return false;
      }
    }

    return true;
  }
}
