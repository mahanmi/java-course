package troops;

public class Army {

  Officer officer;
  Artillery artillery;
  Cavalry cavalry;
  Infantry infantry;

  public Army(Officer officer, int artilleryNumber, int cavalryNumber, int infantryNumber) {
    this.artillery = new Artillery(artilleryNumber);
    this.cavalry = new Cavalry(cavalryNumber);
    this.infantry = new Infantry(infantryNumber);
    this.officer = officer;
  }

  public int getScore() {
    return artillery.getScore() + cavalry.getScore() + infantry.getScore() + officer.getScore();
  }

}
