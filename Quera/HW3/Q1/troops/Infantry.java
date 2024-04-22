package troops;

public class Infantry extends Troops {

  public Infantry(int number) {
    this.number = number;
    this.forestScore = 2;
    this.plainScore = 2;
    this.hillScore = 3;
    this.mountainScore = 1;
  }

}
