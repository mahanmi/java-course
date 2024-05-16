package Users;
public class Manager extends Customer {
  private String studioID;

  public Manager(String subscriptionNumber, String pPassword, String permission, String name, String surname,
      String IDnumber, String DateOfBirth, String address, String studioID) {
    super(subscriptionNumber, pPassword, permission, name, surname, IDnumber, DateOfBirth, address);
    this.studioID = studioID;
  }

  public String getStudioID() {
    return studioID;
  }
}
