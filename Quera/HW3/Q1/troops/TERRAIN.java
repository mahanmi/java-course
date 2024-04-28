package troops;

public enum TERRAIN {

  Forest(2, 5, 2),
  Plain(1, 0, 2),
  Hill(1, -1, 3),
  Mountain(1, -2, 1);

  public int infantry;
  public int cavalry;
  public int artillery;

  private TERRAIN(int artillery, int cavalry, int infantry) {
    this.infantry = infantry;
    this.cavalry = cavalry;
    this.artillery = artillery;
  }

}
