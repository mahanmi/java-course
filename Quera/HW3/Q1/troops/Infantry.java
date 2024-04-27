package troops;

public class Infantry extends Troops {

  public Infantry(int amount) {
    this.amount = amount;
    this.forestScore = 2;
    this.plainScore = 2;
    this.hillScore = 3;
    this.mountainScore = 1;
  }

}
