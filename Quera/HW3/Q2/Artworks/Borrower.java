package Artworks;

public class Borrower {
  String ID;
  String date;
  String hour;

  public Borrower(String ID, String date, String hour) {
    this.ID = ID;
    this.date = date;
    this.hour = hour;
  }

  public String getID() {
    return ID;
  }

  public String getDate() {
    return date;
  }

  public String getHour() {
    return hour;
  }
}
