public class Bike extends Vehicle {
  double weight;
  double wheelSize;
  String type;

  public Bike(String name, String brand, String code, String type) {
    super(name, brand, code);
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getWheelSize() {
    return wheelSize;
  }

  public void setWheelSize(double wheelSize) {
    this.wheelSize = wheelSize;
  }
}
