public class Publication {

  private String name;
  private String author;
  private int price;
  private String language;
  private int amount;

  public Publication(String name, String author, String language, int price) {
    this.name = name;
    this.author = author;
    this.price = price;
    this.language = language;
  }

  public String getName() {
    return name;
  }

  public String getAuthor() {
    return author;
  }

  public String getLanguage() {
    return language;
  }

  public int getPrice() {
    return price;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public void addAmount(int amount) {
    this.amount += amount;
  }

  public int getAmount() {
    return amount;
  }

}
