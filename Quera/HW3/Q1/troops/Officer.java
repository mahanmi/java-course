package troops;

public class Officer {

  enum RANK {
    CORPORAL, SERGEANT, LIEUTENANT, CAPTAIN, COLONEL, GENERAL, MARSHAL
  }

  private RANK rank;
  private int number;

  public Officer(String rankString, int number) {
    this.number = number;
    switch (rankString) {
      case "corporal":
        rank = RANK.CORPORAL;
        break;

      case "sergeant":
        rank = RANK.SERGEANT;
        break;

      case "lieutenant":
        rank = RANK.LIEUTENANT;
        break;

      case "captain":
        rank = RANK.CAPTAIN;
        break;

      case "colonel":
        rank = RANK.COLONEL;
        break;

      case "general":
        rank = RANK.GENERAL;
        break;

      case "marshal":
        rank = RANK.MARSHAL;
        break;

      default:
        break;
    }
  }

  public int getRank() {
    switch (rank) {
      case RANK.CORPORAL:
        return 1;

      case RANK.SERGEANT:
        return 4;

      case RANK.LIEUTENANT:
        return 7;

      case RANK.CAPTAIN:
        return 12;

      case RANK.COLONEL:
        return 18;

      case RANK.GENERAL:
        return 22;

      case RANK.MARSHAL:
        return 25;

      default:
        return 0;
    }
  }

  public int getScore() {
    return 1000 * number * getRank();
  }

}
