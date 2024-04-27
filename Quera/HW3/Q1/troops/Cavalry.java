package troops;

public class Cavalry extends Troops {

  public Cavalry(int amount) {
    this.amount = amount;
    this.forestScore = 5;
    this.plainScore = 0;
    this.hillScore = -1;
    this.mountainScore = -2;
  }

}
