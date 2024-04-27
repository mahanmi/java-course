package troops;

public enum Officer {

  CORPORAL(1),
  SERGEANT(4),
  LIEUTENANT(7),
  CAPTAIN(12),
  COLONEL(18),
  GENERAL(22),
  MARSHAL(25);

  private int rank;

  private Officer(int rank) {
    this.rank = rank;

  }

  public int getScore() {
    return 1000 * rank;
  }

}
