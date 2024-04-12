import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;

public class ProgramController {

  private Library library;

  public void run() {

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    Matcher command;

    boolean isLibraryCreated = false;

    while (!input.equals("end") || !isLibraryCreated) {

      if (input.matches("Create Library")) {
        // #region Create Library
        library = new Library();
        isLibraryCreated = true;
        System.out.println("Library created successfully");
      } else if (!isLibraryCreated) {
        System.out.println("You have to create the library first");
      } else if (input
          .matches("Add account Account-Name\\s+(?<Name>[\\D]+)\\s+Account-Number (?<ID>\\d{5})")) {
        command = getCommandMatcher(input,
            "Add account Account-Name\\s+(?<Name>[\\D]+)\\s+Account-Number (?<ID>\\d{5})");
        addAccount(command);
      } else if (input.matches("Increase balance Account-Number (?<ID>\\d{5}) Amount (?<Amount>\\d+)")) {
        command = getCommandMatcher(input,
            "Increase balance Account-Number (?<ID>\\d{5}) Amount (?<Amount>\\d+)");
        increaseBalance(command);
      } else if (input.matches("Cashout Account-Number (?<ID>\\d{5})")) {
        command = getCommandMatcher(input, "Cashout Account-Number (?<ID>\\d{5})");
        cashout(command);
      } else if (input.matches(
          "Add book Book-Name\\s+(?<BookName>[\\w ]+)\\s+ISBN (?<ISBN>\\d+) Author\\s+(?<Author>[A-z ]+)\\s+Language\\s+(?<Language>[A-z]+)\\s+Price (?<Price>\\d+) Amount (?<Amount>\\d+)")) {
        command = getCommandMatcher(input,
            "Add book Book-Name\\s+(?<BookName>[\\w ]+)\\s+ISBN (?<ISBN>\\d+) Author\\s+(?<Author>[A-z ]+)\\s+Language\\s+(?<Language>[A-z]+)\\s+Price (?<Price>\\d+) Amount (?<Amount>\\d+)");
        addBook(command);
      } else if (input.matches(
          "Add magazine Magazine-Name\\s+(?<MagazineName>[\\w ]+)\\s+ISSN (?<ISSN>\\d+) Author\\s+(?<Author>[A-z ]+)\\s+Language (?<Language>[A-z]+) Price (?<Price>\\d+) Amount (?<Amount>\\d+) Number (?<Number>\\d+)")) {

        command = getCommandMatcher(input,
            "Add magazine Magazine-Name\\s+(?<MagazineName>[\\w ]+)\\s+ISSN (?<ISSN>\\d+) Author\\s+(?<Author>[A-z ]+)\\s+Language (?<Language>[A-z]+) Price (?<Price>\\d+) Amount (?<Amount>\\d+) Number (?<Number>\\d+)");

        addMagazine(command);

      } else if (input.matches("Borrow book ISBN (?<ISBN>\\d+) Account-Number (?<AccountNumber>\\d{5})"))

      {
        command = getCommandMatcher(input, "Borrow book ISBN (?<ISBN>\\d+) Account-Number (?<AccountNumber>\\d{5})");
        borrowBook(command);
      } else if (input.matches(
          "Borrow magazine ISSN (?<ISSN>\\d+) Account-Number (?<AccountNumber>\\d{5}) Number (?<Number>\\d+)")) {
        command = getCommandMatcher(input,
            "Borrow magazine ISSN (?<ISSN>\\d+) Account-Number (?<AccountNumber>\\d{5}) Number (?<Number>\\d+)");
        borrowMagazine(command);
      } else if (input.matches("Return book ISBN (?<ISBN>\\d+) Account-Number (?<AccountNumber>\\d{5})"))

      {
        command = getCommandMatcher(input, "Return book ISBN (?<ISBN>\\d+) Account-Number (?<AccountNumber>\\d{5})");
        returnBook(command);
      } else if (input.matches(
          "Return magazine ISSN (?<ISSN>\\d+) Account-Number (?<AccountNumber>\\d{5}) Number (?<Number>\\d+)")) {
        command = getCommandMatcher(input,
            "Return magazine ISSN (?<ISSN>\\d+) Account-Number (?<AccountNumber>\\d{5}) Number (?<Number>\\d+)");
        returnMagazine(command);
      } else if (input.matches(
          "Donate book Account-Number (?<AccountNumber>\\d{5}) Book-Name\\s+(?<BookName>[\\w ]+)\\s+ISBN (?<ISBN>\\d+) Author\\s+(?<Author>[A-z ]+)\\s+Language (?<Language>[A-z]+) Price (?<Price>\\d+) Amount (?<Amount>\\d+)")) {
        command = getCommandMatcher(input,
            "Donate book Account-Number (?<AccountNumber>\\d{5}) Book-Name\\s+(?<BookName>[\\w ]+)\\s+ISBN (?<ISBN>\\d+) Author\\s+(?<Author>[A-z ]+)\\s+Language (?<Language>[A-z]+) Price (?<Price>\\d+) Amount (?<Amount>\\d+)");
        donateBook(command);
      } else if (input.matches(
          "Return magazine and borrow next ISSN (?<ISSN>\\d+) Account-Number (?<AccountNumber>\\d{5}) Number (?<Number>\\d+)")) {

        command = getCommandMatcher(input,
            "Return magazine and borrow next ISSN (?<ISSN>\\d+) Account-Number (?<AccountNumber>\\d{5}) Number (?<Number>\\d+)");
        returnMagazineAndBorrowNext(command);

      } else if (input.matches("Print books"))

      {
        printBooks();
      } else if (input.matches("Print magazines")) {
        printMagazines();
      } else {
        System.out.println("invalid command");
      }

      input = scanner.nextLine();

    }

    scanner.close();

  }

  private Matcher getCommandMatcher(String input, String regex) {

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);

    matcher.find();

    return matcher;

  }

  private void addAccount(Matcher matcher) {

    Member member = new Member(matcher.group("Name"), Integer.valueOf(matcher.group("ID")));

    if (library.getMemberByID(member.getID()) != null)
      System.out.println("A member with this ID already exists");
    else {
      library.addMember(member);
      System.out.println("Account created successfully");
    }

  }

  private void increaseBalance(Matcher matcher) {

    Member member = library.getMemberByID(Integer.valueOf(matcher.group("ID")));

    if (member == null)
      System.out.println("No member with this ID exists");
    else {
      library.getMemberByID(member.getID()).addBalance(Integer.valueOf(matcher.group("Amount")));
      System.out.println("Balance increased successfully");
    }

  }

  private void cashout(Matcher matcher) {

    Member member = library.getMemberByID(Integer.valueOf(matcher.group("ID")));

    if (member == null)
      System.out.println("No member with this ID exists");
    else {
      System.out.println(member.getName() + " cashed out successfully. Amount: " + member.getBalance());
      library.getMemberByID(member.getID()).setBalance(0);
    }

  }

  private void addBook(Matcher matcher) {

    Book book = new Book(matcher.group("BookName"), Integer.valueOf(matcher.group("ISBN")), matcher.group("Author"),
        matcher.group("Language"), Integer.valueOf(matcher.group("Price")));

    if (library.getBook(book.getISBN()) != null) {
      library.getBook(book.getISBN()).addAmount(Integer.valueOf(matcher.group("Amount")));
      ;
    } else {
      library.addBook(book);
      library.getBook(book.getISBN()).setAmount(Integer.valueOf(matcher.group("Amount")));
    }

    System.out.println(matcher.group("Amount") + " books were added to the library successfully");

  }

  private void addMagazine(Matcher matcher) {

    Magazine magazine = new Magazine(matcher.group("MagazineName"), Integer.valueOf(matcher.group("ISSN")),
        matcher.group("Author"), matcher.group("Language"), Integer.valueOf(matcher.group("Price")),
        Integer.valueOf(matcher.group("Number")));

    if (library.getMagazine(magazine.getISSN(), magazine.getNumber()) != null) {

      library.getMagazine(magazine.getISSN(), magazine.getNumber()).addAmount(Integer.valueOf(matcher.group("Amount")));

    } else {

      library.addMagazine(magazine);
      library.getMagazine(magazine.getISSN(), magazine.getNumber()).setAmount(Integer.valueOf(matcher.group("Amount")));

    }

    System.out.println(matcher.group("Amount") + " magazines were added to the library successfully");

  }

  private void borrowBook(Matcher matcher) {

    Member member = library.getMemberByID(Integer.valueOf(matcher.group("AccountNumber")));

    if (member == null)
      System.out.println("No member with this ID exists");
    else {
      Book book = library.getBook(Integer.valueOf(matcher.group("ISBN")));

      if (book == null || book.getAmount() == 0)
        System.out.println("No book with this ISBN was found in the library");
      else if (member.getBalance() < book.getPrice())
        System.out.println("The member's balance is not enough");
      else {
        library.getBook(book.getISBN()).addAmount(-1);
        library.getMemberByID(member.getID()).borrowBook(book);
        System.out.println(book.getName() + " was borrowed by " + member.getName() + " successfully");
      }

    }

  }

  public void borrowMagazine(Matcher matcher) {

    Member member = library.getMemberByID(Integer.valueOf(matcher.group("AccountNumber")));

    if (member == null) {

      System.out.println("No member with this ID exists");

    } else {

      Magazine magazine = library.getMagazine(Integer.valueOf(matcher.group("ISSN")),
          Integer.valueOf(matcher.group("Number")));

      if (magazine == null || magazine.getAmount() == 0) {
        System.out.println("No magazine with this ISSN and number was found in the library");
      } else if (member.getBalance() < magazine.getPrice()) {
        System.out.println("The member's balance is not enough");
      } else {
        library.getMagazine(magazine.getISSN(), magazine.getNumber()).addAmount(-1);
        library.getMemberByID(member.getID()).borrowMagazine(magazine);
        System.out.println(magazine.getName() + " was borrowed by " + member.getName() + " successfully");
      }
    }

  }

  public void returnBook(Matcher matcher) {

    Member member = library.getMemberByID(Integer.valueOf(matcher.group("AccountNumber")));

    if (member == null) {
      System.out.println("No member with this ID exists");
    } else {
      Book book = member.getBookFromBorrowedBooks(Integer.valueOf(matcher.group("ISBN")));

      if (book == null) {
        System.out.println("This member has never borrowed this book or has returned it");
      } else {
        library.getMemberByID(member.getID()).returnBook(book);
        library.getBook(book.getISBN()).addAmount(1);
        System.out.println(member.getName() + " returned " + book.getName() + " successfully");
      }
    }

  }

  public void returnMagazine(Matcher matcher) {

    Member member = library.getMemberByID(Integer.valueOf(matcher.group("AccountNumber")));

    if (member == null) {
      System.out.println("No member with this ID exists");
    } else {
      Magazine magazine = member.getMagazineFromBorrowedMagazines(Integer.valueOf(matcher.group("ISSN")),
          Integer.valueOf(matcher.group("Number")));

      if (magazine == null) {
        System.out.println("This member has never borrowed this magazine or has returned it");
      } else {
        library.getMemberByID(member.getID()).returnMagazine(magazine);
        library.getMagazine(magazine.getISSN(), magazine.getNumber()).addAmount(1);
        System.out.println(member.getName() + " returned " + magazine.getName() + " successfully");
      }
    }

  }

  public void donateBook(Matcher matcher) {

    Member member = library.getMemberByID(Integer.valueOf(matcher.group("AccountNumber")));

    if (member == null) {
      System.out.println("No member with this ID exists");
    } else {

      Book book = new Book(matcher.group("BookName"), Integer.valueOf(matcher.group("ISBN")), matcher.group("Author"),
          matcher.group("Language"), Integer.valueOf(matcher.group("Price")));

      int Amount = Integer.valueOf(matcher.group("Amount"));

      if (library.getBook(book.getISBN()) != null) {
        library.getBook(book.getISBN()).addAmount(Amount);
      } else {
        library.addBook(book);
        library.getBook(book.getISBN()).setAmount(Amount);
      }

      double bonus = Amount * book.getPrice() * 0.4;

      library.getMemberByID(member.getID())
          .addBalance((int) bonus);

      System.out.println(member.getName() + " donated " + matcher.group("Amount") + " books successfully");

    }

  }

  private void returnMagazineAndBorrowNext(Matcher matcher) {

    Member member = library.getMemberByID(Integer.valueOf(matcher.group("AccountNumber")));

    if (member == null) {
      System.out.println("No member with this ID exists");
    } else {
      Magazine magazine = member.getMagazineFromBorrowedMagazines(Integer.valueOf(matcher.group("ISSN")),
          Integer.valueOf(matcher.group("Number")));

      if (magazine == null) {
        System.out.println("This member has never borrowed this magazine or has returned it");
      } else {
        Magazine nextMagazine = library.getMagazine(magazine.getISSN(), magazine.getNumber() + 1);
        if (nextMagazine == null) {
          System.out.println("The library does not have the next issue of this magazine");
        } else {
          library.getMemberByID(member.getID()).returnMagazine(magazine);
          library.getMemberByID(member.getID()).addBalance((int) (magazine.getPrice() * 0.8));
          library.getMagazine(magazine.getISSN(), magazine.getNumber()).addAmount(1);
          library.getMemberByID(member.getID()).borrowMagazine(nextMagazine);
          System.out.println(member.getName() + " returned " + magazine.getName() + " and borrowed the next issue");
        }
      }
    }

  }

  private void printBooks() {
    System.out.println("List of all books:");

    ArrayList<Book> books = library.getBooks();

    books.sort((book1, book2) -> {
      int nameComparison = book1.getName().compareTo(book2.getName());
      if (nameComparison != 0) {
        return nameComparison;
      } else {
        int authorComparison = book1.getAuthor().compareTo(book2.getAuthor());
        if (authorComparison != 0) {
          return authorComparison;
        } else {
          return book1.getLanguage().compareTo(book2.getLanguage());
        }
      }
    });

    for (Book book : books)
      for (int i = 0; i < book.getAmount(); i++)
        System.out.println(book.getISBN() + ": " + book.getName());

  }

  private void printMagazines() {
    System.out.println("List of all magazines:");

    ArrayList<Magazine> magazines = library.getMagazines();

    magazines.sort((magazine1, magazine2) -> {
      int nameComparison = magazine1.getName().compareTo(magazine2.getName());
      if (nameComparison != 0) {
        return nameComparison;
      } else {
        int numberComparison = magazine1.getNumber() - magazine2.getNumber();
        if (numberComparison != 0) {
          return numberComparison;
        } else {
          int authorComparison = magazine1.getAuthor().compareTo(magazine2.getAuthor());
          if (authorComparison != 0) {
            return authorComparison;
          } else {
            return magazine1.getLanguage().compareTo(magazine2.getLanguage());
          }
        }
      }
    });

    for (Magazine magazine : magazines) {
      for (int i = 0; i < magazine.getAmount(); i++) {
        System.out.println(magazine.getISSN() + ": " + magazine.getName() + " " + magazine.getNumber());
      }
    }

  }

}
