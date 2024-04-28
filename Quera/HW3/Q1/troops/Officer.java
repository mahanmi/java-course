package troops;

public enum Officer {

  CORPORAL(1),
  SERGENT(4),
  LIEUTENANT(7),
  CAPITAN(12),
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

  public String getRank() {
    return this.name().substring(0, 0) + this.name().substring(1, this.name().length() - 1).toLowerCase();
  }

}
