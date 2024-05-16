import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

import Users.Customer;
import Users.Manager;
import Users.Staff;
import Users.User;

public class Program {
  Scanner scanner = new Scanner(System.in);
  private ArrayList<User> users = new ArrayList<User>();
  private ArrayList<Studio> studios = new ArrayList<Studio>();
  private ArrayList<Category> categories = new ArrayList<Category>();

  void run() {
    Matcher matcher;
    String input = scanner.nextLine();

    users.add(new User("admin", "AdminPass", "admin"));

    while (!input.equals("finish")) {
      if (input.matches(Commands.ADD_STUDIO.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_STUDIO.regex);
        addStudio(matcher);
      } else if (input.matches(Commands.ADD_CATEGORY.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_CATEGORY.regex);
        addCategory(matcher);
      } else if (input.matches(Commands.ADD_CUSTOMER.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_CUSTOMER.regex);
        addCustomer(matcher);
      } else if (input.matches(Commands.ADD_STAFF.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_STAFF.regex);
        addStaff(matcher);
      } else if (input.matches(Commands.ADD_MANAGER.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_MANAGER.regex);
        addManager(matcher);
      } else if (input.matches(Commands.REMOVE_USER.regex)) {
        matcher = getCommandMatcher(input, Commands.REMOVE_USER.regex);
        removeUser(matcher);
      } else {
        System.out.println("invalid-command");
      }

      input = scanner.nextLine();
    }
  }

  private Matcher getCommandMatcher(String input, String regex) {

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);

    matcher.find();

    return matcher;
  }

  User getUser(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  Customer getCustomer(String subscriptionNumber) {

    for (User user : users) {
      if (user instanceof Customer) {
        Customer customer = (Customer) user;
        if (customer.getUsername().equals(subscriptionNumber)) {
          return customer;
        }
      }
    }
    return null;

  }

  Staff getStaff(String subscriptionNumber) {

    for (User user : users) {
      if (user instanceof Staff) {
        Staff staff = (Staff) user;
        if (staff.getUsername().equals(subscriptionNumber)) {
          return staff;
        }
      }
    }
    return null;

  }

  private Manager getManager(String subscriptionNumber) {

    for (User user : users) {
      if (user instanceof Manager) {
        Manager manager = (Manager) user;
        if (manager.getUsername().equals(subscriptionNumber)) {
          return manager;
        }
      }
    }
    return null;

  }

  Studio getStudio(String ID) {
    for (Studio studio : studios) {
      if (studio.getID().equals(ID)) {
        return studio;
      }
    }
    return null;
  }

  Category getCategory(String ID) {
    for (Category category : categories) {
      if (category.getID().equals(ID)) {
        return category;
      }
    }
    return null;
  }

  boolean isAdmin(User user) {
    if (user.getPermission().equals("admin"))
      return true;

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

    User user = getUser(username);

    if (getStudio(ID) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (!isAdmin(user)) {
      System.out.println("permission-denied");
      return;
    }

    if (user.getUsername().equals(username)) {
      if (user.getPassword().equals(password)) {
        studios.add(new Studio(ID, name, year, capacity, address));
        System.out.println("success");
        return;
      }
      System.out.println("invalid-pass");
      return;
    }
    System.out.println("not-found");
    return;
  }

  private void addCategory(Matcher matcher) {
    String username = matcher.group("username");
    String password = matcher.group("password");
    User user = getUser(username);

    String ID = matcher.group("categoryID");
    String name = matcher.group("categoryName");
    String superCategory = matcher.group("superCategory");
    Category supCategory = (superCategory.equals("null")) ? null : getCategory(superCategory);

    if (getCategory(name) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null || (supCategory == null && !superCategory.equals("null"))) {
      System.out.println("not-found");
      return;
    }

    if (!isAdmin(user)) {
      System.out.println("permission-denied");
      return;
    }

    if (user.getPassword().equals(password)) {
      if (superCategory.equals("null")) {
        categories.add(new Category(name, ID));
      } else {
        supCategory.addSubCategory(new Category(name, ID));
      }
      System.out.println("success");
      return;
    }

    System.out.println("invalid-pass");
  }

  private void addCustomer(Matcher matcher) {

    String username = matcher.group("username");
    String password = matcher.group("password");
    User user = getUser(username);

    String subscriptionNumber = matcher.group("subscriptionNumber");
    String cPassword = matcher.group("cPassword");
    String name = matcher.group("name");
    String surname = matcher.group("surname");
    String IDnumber = matcher.group("IDnumber");
    String DateOfBirth = matcher.group("DateOfBirth");
    String address = matcher.group("address");

    if (getUser(subscriptionNumber) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null) {
      System.out.println("not-found");
      return;
    }

    if (!user.getPermission().equals("admin")) {
      System.out.println("permission-denied");
      return;
    }

    if (user.getPassword().equals(password)) {
      users.add(new Customer(subscriptionNumber, cPassword, "customer", name, surname, IDnumber, DateOfBirth, address));
      System.out.println("success");
      return;
    }

    System.out.println("invalid-pass");

  }

  private void addStaff(Matcher matcher) {

    String username = matcher.group("username");
    String password = matcher.group("password");
    User user = getUser(username);

    String subscriptionNumber = matcher.group("subscriptionNumber");
    String pPassword = matcher.group("pPassword");
    String name = matcher.group("name");
    String surname = matcher.group("surname");
    String IDnumber = matcher.group("IDnumber");
    String DateOfBirth = matcher.group("DateOfBirth");
    String address = matcher.group("address");
    String role = matcher.group("role");

    if (getUser(subscriptionNumber) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null) {
      System.out.println("not-found");
      return;
    }

    if (!user.getPermission().equals("admin")) {
      System.out.println("permission-denied");
      return;
    }

    if (user.getPassword().equals(password)) {
      users.add(new Staff(subscriptionNumber, pPassword, "staff", name, surname, IDnumber, DateOfBirth, address, role));
      System.out.println("success");
      return;
    }

    System.out.println("invalid-pass");

  }

  private void addManager(Matcher matcher) {
    String username = matcher.group("username");
    String password = matcher.group("password");
    User user = getUser(username);

    String subscriptionNumber = matcher.group("subscriptionNumber");
    String pPassword = matcher.group("pPassword");
    String name = matcher.group("name");
    String surname = matcher.group("surname");
    String IDnumber = matcher.group("IDnumber");
    String DateOfBirth = matcher.group("DateOfBirth");
    String address = matcher.group("address");
    String studioID = matcher.group("studioID");

    if (getUser(subscriptionNumber) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null || getStudio(studioID) == null) {
      System.out.println("not-found");
      return;
    }

    if (!user.getPermission().equals("admin")) {
      System.out.println("permission-denied");
      return;
    }

    if (user.getPassword().equals(password)) {
      users.add(new Manager(subscriptionNumber, pPassword, "manager", name, surname, IDnumber, DateOfBirth, address,
          studioID));
      System.out.println("success");
      return;
    }

    System.out.println("invalid-pass");
  }

  private void removeUser(Matcher matcher) {
    String username = matcher.group("username");
    String password = matcher.group("password");
    String userID = matcher.group("userID");

    User user = getUser(username);
    Customer userToRemove = getCustomer(userID);

    if (userToRemove == null) {
      System.out.println("not-found");
      return;
    }

    if (userToRemove.getDebt() > 0 || userToRemove.getBorrowedArtworks().size() > 0) {
      System.out.println("not-allowed");
      return;
    }

    if (user == null) {
      System.out.println("not-found");
      return;
    }

    if (!user.getPermission().equals("admin")) {
      System.out.println("permission-denied");
      return;
    }

    if (user.getPassword().equals(password)) {
      users.remove(userToRemove);
      System.out.println("success");
      return;
    }

    System.out.println("invalid-pass");
  }

}
