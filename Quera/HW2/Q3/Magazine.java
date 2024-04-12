public class Magazine extends Publication {

  private int ISSN;
  private int number;

  public Magazine(String name, int ISSN, String author, String language, int price, int number) {
    super(name, author, language, price);
    this.ISSN = ISSN;
    this.number = number;
  }

  public int getISSN() {
    return ISSN;
  }

  public int getNumber() {
    return number;
  }

}
