package troops;

public class Corp {

  private Officer officer;
  private Artillery artillery;
  private Cavalry cavalry;
  private Infantry infantry;
  private String number;

  public Corp(Officer officer, int artilleryNumber, int cavalryNumber, int infantryNumber, String number) {
    this.artillery = new Artillery(artilleryNumber);
    this.cavalry = new Cavalry(cavalryNumber);
    this.infantry = new Infantry(infantryNumber);
    this.officer = officer;
    this.number = number;
  }

  public int getScore() {
    return artillery.getScore() + cavalry.getScore() + infantry.getScore() + officer.getScore();
  }

  public int getTotal() {
    return artillery.amount + cavalry.amount + infantry.amount + 1; // 1 officer
  }

  public String getNumber() {
    return number;
  }

}
