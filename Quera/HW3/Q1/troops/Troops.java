package troops;

public abstract class Troops {

  public enum TERRAIN {
    FOREST, PLAIN, HILL, MOUNTAIN
  }

  protected TERRAIN place;
  protected int number;
  protected int forestScore;
  protected int plainScore;
  protected int hillScore;
  protected int mountainScore;

  public int getScore() {
    switch (place) {
      case FOREST:
        return number * forestScore;

      case PLAIN:
        return number * plainScore;

      case HILL:
        return number * hillScore;

      case MOUNTAIN:
        return number * mountainScore;

      default:
        return 0;
    }
  }

}
