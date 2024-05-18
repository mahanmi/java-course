import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

import Users.*;
import Artworks.*;

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
      } else if (input.matches(Commands.ADD_PAINTING.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_PAINTING.regex);
        addPainting(matcher);
      } else if (input.matches(Commands.ADD_STATUE.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_STATUE.regex);
        addStatue(matcher);
      } else if (input.matches(Commands.ADD_WORTHY.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_WORTHY.regex);
        addWorthy(matcher);
      } else if (input.matches(Commands.ADD_SELLING.regex)) {
        matcher = getCommandMatcher(input, Commands.ADD_SELLING.regex);
        addSelling(matcher);
      } else if (input.matches(Commands.REMOVE_ARTWORK.regex)) {
        matcher = getCommandMatcher(input, Commands.REMOVE_ARTWORK.regex);
        removeArtwork(matcher);
      } else if (input.matches(Commands.BORROW.regex)) {
        matcher = getCommandMatcher(input, Commands.BORROW.regex);
        borrow(matcher);
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

  Category getCategory(String ID, ArrayList<Category> categoriesArrayList) {
    for (Category category : categoriesArrayList) {
      if (category.getSubCategories().size() > 0) {
        Category c = getCategory(ID, category.getSubCategories());
        if (c != null) {
          return c;
        }
      }
      if (category.getID().equals(ID)) {
        return category;
      }
    }
    return null;
  }

  Artwork getArtwork(String ID, String studioID) {
    Studio studio = getStudio(studioID);
    if (studio == null) {
      return null;
    }
    for (Artwork artwork : studio.getArtworks()) {
      if (artwork.getID().equals(ID)) {
        return artwork;
      }
    }
    return null;
  }

  Painting getPainting(String ID, String studioID) {
    Studio studio = getStudio(studioID);
    if (studio == null) {
      return null;
    }
    for (Artwork artwork : studio.getArtworks()) {
      if (artwork instanceof Painting) {
        Painting painting = (Painting) artwork;
        if (painting.getID().equals(ID)) {
          return painting;
        }
      }
    }
    return null;
  }

  Statue getStatue(String ID, String studioID) {
    Studio studio = getStudio(studioID);
    if (studio == null) {
      return null;
    }
    for (Artwork artwork : studio.getArtworks()) {
      if (artwork instanceof Statue) {
        Statue statue = (Statue) artwork;
        if (statue.getID().equals(ID)) {
          return statue;
        }
      }
    }
    return null;
  }

  void addStudio(Matcher matcher) {
    String username = matcher.group("username");
    String password = matcher.group("password");
    User user = getUser(username);

    String ID = matcher.group("studioID");
    String name = matcher.group("studioName");
    String year = matcher.group("year");
    String capacity = matcher.group("capacity");
    String address = matcher.group("address");

    if (getStudio(ID) != null) {
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
      studios.add(new Studio(ID, name, year, capacity, address));
      System.out.println("success");
      return;
    }
    System.out.println("invalid-pass");
    return;

  }

  private void addCategory(Matcher matcher) {
    String username = matcher.group("username");
    String password = matcher.group("password");
    User user = getUser(username);

    String ID = matcher.group("categoryID");
    String name = matcher.group("categoryName");
    String superCategory = matcher.group("superCategory");
    Category supCategory = (superCategory.equals("null")) ? null : getCategory(superCategory, categories);

    if (getCategory(name, categories) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null || (supCategory == null && !superCategory.equals("null"))) {
      System.out.println("not-found");
      return;
    }

    if (!user.getPermission().equals("admin")) {
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

  private void addPainting(Matcher matcher) {
    String managerID = matcher.group("managerID");
    String password = matcher.group("password");
    Manager user = getManager(managerID);

    String ID = matcher.group("ID");
    String name = matcher.group("name");
    String painter = matcher.group("painter");
    String investor = matcher.group("investor");
    String date = matcher.group("date");
    int copyNumber = Integer.valueOf(matcher.group("copyNumber"));
    String categoryID = matcher.group("categoryID");
    String studioID = matcher.group("studioID");

    if (getArtwork(ID, studioID) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null || getStudio(studioID) == null || getCategory(categoryID, categories) == null) {
      System.out.println("not-found");
      return;
    }

    if (user.getPermission().equals("manager") & user.getStudioID().equals(studioID)) {
      if (user.getPassword().equals(password)) {
        getStudio(studioID)
            .addArtwork(new Painting(ID, name, painter, investor, date, copyNumber, categoryID, studioID));
        System.out.println("success");
        return;
      }
      System.out.println("invalid-pass");
      return;
    }
    System.out.println("permission-defied");

  }

  private void addStatue(Matcher matcher) {
    String managerID = matcher.group("managerID");
    String password = matcher.group("password");
    Manager user = getManager(managerID);

    String ID = matcher.group("ID");
    String name = matcher.group("name");
    String sculptor = matcher.group("sculptor");
    String professorName = matcher.group("professorName");
    String date = matcher.group("date");
    String category = matcher.group("category");
    String studioID = matcher.group("studioID");

    if (getArtwork(ID, studioID) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null || getStudio(studioID) == null
        || (getCategory(category, categories) == null && !category.equals("null"))) {
      System.out.println("not-found");
      return;
    }

    if (user.getPermission().equals("manager") & user.getStudioID().equals(studioID)) {
      if (user.getPassword().equals(password)) {
        getStudio(studioID)
            .addArtwork(new Statue(ID, name, sculptor, professorName, date, category, studioID));
        System.out.println("success");
        return;
      }
      System.out.println("invalid-pass");
      return;
    }
    System.out.println("permission-defied");
  }

  private void addWorthy(Matcher matcher) {
    String managerID = matcher.group("managerID");
    String password = matcher.group("password");
    Manager user = getManager(managerID);

    String ID = matcher.group("ID");
    String name = matcher.group("name");
    String painter = matcher.group("painter");
    String printer = matcher.group("printer");
    String date = matcher.group("date");
    String donator = matcher.group("donator");
    String categoryID = matcher.group("categoryID");
    String studioID = matcher.group("studioID");

    if (getArtwork(ID, studioID) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null || getStudio(studioID) == null || getCategory(categoryID, categories) == null) {
      System.out.println("not-found");
      return;
    }

    if (user.getPermission().equals("manager") & user.getStudioID().equals(studioID)) {
      if (user.getPassword().equals(password)) {
        getStudio(studioID)
            .addArtwork(new Worthy(ID, name, painter, printer, date, donator, categoryID, studioID));
        System.out.println("success");
        return;
      }
      System.out.println("invalid-pass");
      return;
    }
    System.out.println("permission-defied");
  }

  private void addSelling(Matcher matcher) {
    String managerID = matcher.group("managerID");
    String password = matcher.group("password");
    Manager user = getManager(managerID);

    String ID = matcher.group("ID");
    String name = matcher.group("name");
    String painter = matcher.group("painter");
    String printer = matcher.group("printer");
    String date = matcher.group("date");
    int copyNumber = Integer.valueOf(matcher.group("copyNumber"));
    int price = Integer.valueOf(matcher.group("price"));
    int discount = Integer.valueOf(matcher.group("discount"));
    String category = matcher.group("category");
    String studioID = matcher.group("studioID");

    if (getArtwork(ID, studioID) != null) {
      System.out.println("duplicate-id");
      return;
    }

    if (user == null || getStudio(studioID) == null || getCategory(category, categories) == null) {
      System.out.println("not-found");
      return;
    }

    if (user.getPermission().equals("manager") & user.getStudioID().equals(studioID)) {
      if (user.getPassword().equals(password)) {
        getStudio(studioID)
            .addArtwork(new Selling(ID, name, painter, printer, date, copyNumber, price, discount, category, studioID));
        System.out.println("success");
        return;
      }
      System.out.println("invalid-pass");
      return;
    }
    System.out.println("permission-defied");
  }

  private void removeArtwork(Matcher matcher) {
    String managerID = matcher.group("managerID");
    String password = matcher.group("password");
    Manager user = getManager(managerID);

    String ID = matcher.group("ID");
    String studioID = matcher.group("studioID");

    if (getArtwork(ID, studioID) == null | user == null || getStudio(studioID) == null) {
      System.out.println("not-found");
      return;
    }

    if (user.getPermission().equals("manager") & user.getStudioID().equals(studioID)) {
      if (user.getPassword().equals(password)) {
        if (getArtwork(ID, studioID) instanceof Painting) {
          Painting painting = getPainting(ID, studioID);
          if (painting.getBorrowers().size() > 0) {
            System.out.println("not-allowed");
            return;
          }
        }
        getStudio(studioID).removeArtwork(getArtwork(ID, studioID));
        System.out.println("success");
        return;
      }
      System.out.println("invalid-pass");
      return;
    }
    System.out.println("permission-defied");
  }

  private void borrow(Matcher matcher) {
    String ID = matcher.group("ID");
    String password = matcher.group("password");
    String studioID = matcher.group("studioID");
    String sourceID = matcher.group("sourceID");
    String date = matcher.group("date");
    String hour = matcher.group("hour");

    User user = getUser(ID);
    Customer customer = getCustomer(ID);
    Painting painting = getPainting(sourceID, studioID);
    Artwork artwork = getArtwork(sourceID, studioID);

    if (user == null || customer == null || artwork == null || getStudio(studioID) == null) {
      System.out.println("not-found");
      return;
    }

    if (customer.getPassword().equals(password)) {
      if (!customer.canBorrow(date, hour)) {
        System.out.println("not-allowed");
        return;
      }
      if (painting.getBorrowers().size() >= painting.getCopyNumber()) {
        System.out.println("not-allowed");
        return;
      }
      if (!(artwork instanceof Painting)) {
        System.out.println("not-allowed");
        return;
      }
      customer.borrowArtwork(artwork);
      customer.addBorrowedResource(new Borrower(ID, date, hour));
      painting.addBorrower(new Borrower(ID, date, hour));
      System.out.println("success");
      return;
    }
    System.out.println("invalid-pass");
  }

  private void returnArtwork(Matcher matcher) {
    String ID = matcher.group("ID");
    String password = matcher.group("password");
    String studioID = matcher.group("studioID");
    String sourceID = matcher.group("sourceID");
    String date = matcher.group("date");
    String hour = matcher.group("hour");

    User user = getUser(ID);
    Customer customer = getCustomer(ID);
    Painting painting = getPainting(sourceID, studioID);
    Artwork artwork = getArtwork(sourceID, studioID);

    if (user == null || customer == null || artwork == null || getStudio(studioID) == null) {
      System.out.println("not-found");
      return;
    }

    if (customer.getPassword().equals(password)) {
      int out = 0;
      for (Borrower borrower : painting.getBorrowers()) {
        if (borrower.getID().equals(ID)) {
          int passedDays = Integer.valueOf(borrower.getDate()) - Integer.valueOf(date);
          int passedHours = Integer.valueOf(borrower.getHour()) - Integer.valueOf(hour);
          if (user instanceof Customer) {
            if (artwork instanceof Painting && passedDays > 10) {
              out += ((passedDays - 10) * 24 + passedHours) * 50;
            } else if (artwork instanceof Statue && passedDays > 7) {
              out += ((passedDays - 7) * 24 + passedHours) * 50;
            }
          } else {
            if (artwork instanceof Painting && passedDays > 14) {
              out += ((passedDays - 14) * 24 + passedHours) * 100;
            } else if (artwork instanceof Statue && passedDays > 10) {
              out += ((passedDays - 10) * 24 + passedHours) * 100;
            }
          }
          break;
        }

      }
      customer.returnArtwork(artwork);
      customer.removeBorrowedResource(new Borrower(ID, date, hour));
      painting.removeBorrower(new Borrower(ID, date, hour));
      System.out.println("success");
      customer.addDebt(out);
      System.out.println(out);
      return;
    }
    System.out.println("invalid-pass");
  }

}
