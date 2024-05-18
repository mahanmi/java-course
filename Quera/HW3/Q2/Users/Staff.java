package Users;

public class Staff extends Customer {
  private String role;

  public Staff(String subscriptionNumber, String pPassword, String permission, String name, String surname,
      String IDnumber, String DateOfBirth, String address, String role) {
    super(subscriptionNumber, pPassword, permission, name, surname, IDnumber, DateOfBirth, address);
    this.role = role;
    setPermission("staff");
  }

  public String getRole() {
    return role;
  }
}
