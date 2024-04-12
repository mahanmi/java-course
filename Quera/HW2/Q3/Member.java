import java.util.ArrayList;

public class Member {

  private String name;
  private int ID;
  private int balance = 0;
  private ArrayList<Book> borrowedBooks = new ArrayList<Book>();
  private ArrayList<Magazine> borrowedMagazines = new ArrayList<Magazine>();

  public Member(String name, int ID) {
    this.name = name;
    this.ID = ID;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public void addBalance(int balance) {
    this.balance += balance;
  }

  public int getBalance() {
    return balance;
  }

  public String getName() {
    return name;
  }

  public int getID() {
    return ID;
  }

  public void borrowBook(Book book) {
    balance -= book.getPrice();
    borrowedBooks.add(book);
  }

  public void borrowMagazine(Magazine magazine) {
    balance -= magazine.getPrice();
    borrowedMagazines.add(magazine);
  }

  public Book getBookFromBorrowedBooks(int ISBN) {

    for (Book book : borrowedBooks) {
      if (book.getISBN() == ISBN) {
        return book;
      }
    }
    return null;

  }

  public Magazine getMagazineFromBorrowedMagazines(int ISSN, int number) {

    for (Magazine magazine : borrowedMagazines) {
      if (magazine.getISSN() == ISSN && magazine.getNumber() == number) {
        return magazine;
      }
    }
    return null;

  }

  public void returnBook(Book book) {
    balance += book.getPrice() * 0.9;
    borrowedBooks.remove(book);
  }

  public void returnMagazine(Magazine magazine) {
    balance += magazine.getPrice() * 0.8;
    borrowedMagazines.remove(magazine);
  }

}
