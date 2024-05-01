import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Program {
  Scanner scanner = new Scanner(System.in);
  ArrayList<User> users = new ArrayList<User>();
  ArrayList<Studio> studios = new ArrayList<Studio>();

  void run() {
    Matcher matcher;
    String input = scanner.nextLine();

    users.add(new User("admin", "AdminPass", "admin"));

    while (true) {
      if (input.matches(Commands.ADD_STUDIO.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_STUDIO.regex);
        addStudio(matcher);
      }
    }

  }

  private Matcher getCommandMatcher(String input, String regex) {

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);

    matcher.find();

    return matcher;

  }

  boolean isAdmin(String username) {
    for (User loginUser : users) {
      if (loginUser.getUsername().equals(username) && loginUser.getPermission().equals("admin")) {
        return true;
      }
    }
    return false;
  }

  void addStudio(Matcher matcher) {
    String username = matcher.group("username");
    String password = matcher.group("password");

    String ID = matcher.group("studioID");
    String name = matcher.group("studioName");
    String year = matcher.group("year");
    String capacity = matcher.group("capacity");
    String address = matcher.group("address");

    for (Studio studio : studios) {
      if (studio.getID().equals(ID)) {
        System.out.println("duplicate-id");
        return;
      }
    }

    if (!isAdmin(username)) {
      System.out.println("permission-denied");
      return;
    }

    for (User user : users) {
      if (user.getUsername().equals(username)) {
        if (user.getPassword().equals(password)) {
          studios.add(new Studio(ID, name, year, capacity, address));
          System.out.println("Success");
          return;
        }
        System.out.println("invalid-pass");
        return;
      }
      System.out.println("not-found");
      return;
    }

  }
}
