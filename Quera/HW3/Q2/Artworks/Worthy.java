package Artworks;

public class Worthy extends Artwork {
  private String publisher;
  private String donator;

  public Worthy(String ID, String title, String author, String publisher, String date, String donator,
      String categoryID, String studioID) {
    super(ID, title, author, date, categoryID, studioID);
    this.publisher = publisher;
    this.donator = donator;
  }

  public String getPublisher() {
    return publisher;
  }

  public String getDonator() {
    return donator;
  }
}
