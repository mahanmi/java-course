package troops;

public abstract class Troops {

  public enum TERRAIN {
    FOREST, PLAIN, HILL, MOUNTAIN
  }

  protected TERRAIN place;
  protected int amount;
  protected int forestScore;
  protected int plainScore;
  protected int hillScore;
  protected int mountainScore;

  public int getScore() {
    switch (place) {
      case FOREST:
        return amount * forestScore;

      case PLAIN:
        return amount * plainScore;

      case HILL:
        return amount * hillScore;

      case MOUNTAIN:
        return amount * mountainScore;

      default:
        return 0;
    }
  }

  public int getAmount() {
    return amount;
  }

}
