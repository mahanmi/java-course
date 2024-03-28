public class Vehicle {

  private String name;
  private String code;
  private String brand;
  private String color;
  private String origin;
  private String state;
  private double discount;
  private double price;

  public Vehicle(String name, String brand, String code) {
    this.name = name;
    this.brand = brand;
    this.code = code;
    this.discount = 0;
  }

  public String getName() {
    return name;
  }

  public String getBrand() {
    return brand;
  }

  public String getCode() {
    return code;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = (this.discount > 0) ? this.discount + discount * (100 - this.discount) / 100 : discount;
    this.price = (price - (price * discount / 100));
  }

  public int getPrice() {
    return (int) price;
  }

  public double getExactPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

}