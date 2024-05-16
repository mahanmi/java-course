package Users;
public class Customer extends User {
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

}
