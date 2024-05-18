package Users;

import java.util.ArrayList;
import Artworks.Borrower;

public class Customer extends User {
  private ArrayList<Borrower> borrowedResources = new ArrayList<Borrower>();

  private String name;
  private String surname;
  private String IDnumber;
  private String DateOfBirth;
  private String address;

  public Customer(String subscriptionNumber, String password, String permission, String name, String surname,
      String IDnumber, String DateOfBirth, String address) {
    super(subscriptionNumber, password, permission);
    this.name = name;
    this.surname = surname;
    this.IDnumber = IDnumber;
    this.DateOfBirth = DateOfBirth;
    this.address = address;
    setPermission("customer");
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getIDnumber() {
    return IDnumber;
  }

  public String getDateOfBirth() {
    return DateOfBirth;
  }

  public String getAddress() {
    return address;
  }

  public ArrayList<Borrower> getBorrowedResources() {
    return borrowedResources;
  }

  public void addBorrowedResource(Borrower borrowedResource) {
    borrowedResources.add(borrowedResource);
  }

  public void removeBorrowedResource(Borrower borrowedResource) {
    borrowedResources.remove(borrowedResource);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setIDnumber(String IDnumber) {
    this.IDnumber = IDnumber;
  }

  public void setDateOfBirth(String DateOfBirth) {
    this.DateOfBirth = DateOfBirth;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean canBorrow(String date, String hour) {
    if (borrowedResources.size() >= 3) {
      return false;
    }

    for (Borrower borrower : borrowedResources) {
      if (Integer.parseInt(borrower.getDate()) < Integer.parseInt(date)
          && Integer.parseInt(borrower.getHour()) < Integer.parseInt(hour)) {
        return false;
      }
    }

    return true;
  }

}
