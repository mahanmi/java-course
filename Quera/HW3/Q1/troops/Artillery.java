package troops;

public class Artillery extends Troops {

  public Artillery(int amount) {
    this.amount = amount;
    this.forestScore = 2;
    this.plainScore = 1;
    this.hillScore = 1;
    this.mountainScore = 1;
  }

}
