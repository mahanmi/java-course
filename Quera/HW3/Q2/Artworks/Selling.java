package Artworks;

public class Selling extends Artwork {
  private String publisher;
  private int copyNumber;
  private int price;
  private int discount;

  public Selling(String ID, String title, String author, String publisher, String date, int copyNumber, int price,
      int discount, String categoryID, String studioID) {
    super(ID, title, author, date, categoryID, studioID);
    this.publisher = publisher;
    this.copyNumber = copyNumber;
    this.price = price;
    this.discount = discount;
  }

  public String getPublisher() {
    return publisher;
  }

  public int getCopyNumber() {
    return copyNumber;
  }

  public int price() {
    return price;
  }

  public double priceWithDiscount() {
    return price * discount / 100;
  }

  public int getDiscount() {
    return discount;
  }

}
