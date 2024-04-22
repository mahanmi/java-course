package troops;

public class Cavalry extends Troops {

  public Cavalry(int number) {
    this.number = number;
    this.forestScore = 5;
    this.plainScore = 0;
    this.hillScore = -1;
    this.mountainScore = -2;
  }

}
