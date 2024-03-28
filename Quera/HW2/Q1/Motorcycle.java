public class Motorcycle extends Vehicle {
  double strength;
  double speed;

  public Motorcycle(String name, String brand, String code) {
    super(name, brand, code);
  }

  public double getStrength() {
    return strength;
  }

  public void setStrength(double strength) {
    this.strength = strength;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }
}
