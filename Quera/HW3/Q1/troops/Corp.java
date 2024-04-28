package troops;

public class Corp {

  private int artillery;
  private int cavalry;
  private int infantry;
  private Officer officer;
  private String number;
  private boolean inArmy = false;

  public Corp(Officer officer, int artilleryNumber, int cavalryNumber, int infantryNumber, String number) {
    this.artillery = artilleryNumber;
    this.cavalry = cavalryNumber;
    this.infantry = infantryNumber;
    this.officer = officer;
    this.number = number;
  }

  public int getTotal() {
    return artillery + cavalry + infantry + 1; // 1 officer
  }

  public String getNumber() {
    return number;
  }

  public int getIntNumber() {
    switch (this.number) {
      case "I":
        return 1;

      case "II":
        return 2;

      case "III":
        return 3;

      case "IV":
        return 4;

      default:
        return 0;
    }
  }

  public boolean isInArmy() {
    return inArmy;
  }

  public void setInArmy(boolean inArmy) {
    this.inArmy = inArmy;
  }

  public int getArtillery() {
    return artillery;
  }

  public int getCavalry() {
    return cavalry;
  }

  public int getInfantry() {
    return infantry;
  }

  public int getScore(TERRAIN place) {
    return place.artillery * artillery + place.cavalry * cavalry + place.infantry * infantry + officer.getScore()
        + getTotal() - 1;
  }

  public Officer getOfficer() {
    return officer;
  }

  public void divide() {
    artillery /= 2;
    cavalry /= 2;
    infantry /= 2;
  }

}
