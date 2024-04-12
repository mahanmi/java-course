import java.util.ArrayList;

public class Library {

  private ArrayList<Book> books = new ArrayList<Book>();
  private ArrayList<Magazine> magazines = new ArrayList<Magazine>();
  private ArrayList<Member> members = new ArrayList<Member>();

  public void addBook(Book book) {
    books.add(book);
  }

  public void addMagazine(Magazine magazine) {
    magazines.add(magazine);
  }

  public void addMember(Member member) {
    members.add(member);
  }

  public Member getMemberByID(int id) {
    for (Member member : members) {
      if (member.getID() == id) {
        return member;
      }
    }
    return null;
  }

  public Book getBook(int ISBN) {
    for (Book book : books) {
      if (book.getISBN() == ISBN) {
        return book;
      }
    }
    return null;
  }

  public Magazine getMagazine(int ISSN, int number) {
    for (Magazine magazine : magazines) {
      if (magazine.getISSN() == ISSN && magazine.getNumber() == number) {
        return magazine;
      }
    }
    return null;
  }

  public void removeBook(Book book) {
    books.remove(book);
  }

  public void removeMagazine(Magazine magazine) {
    magazines.remove(magazine);
  }

  public ArrayList<Book> getBooks() {
    return books;
  }

  public ArrayList<Magazine> getMagazines() {
    return magazines;
  }

}
