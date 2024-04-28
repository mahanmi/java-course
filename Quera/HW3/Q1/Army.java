import java.util.ArrayList;

import troops.Corp;
import troops.TERRAIN;

public class Army {

  private ArrayList<Corp> corps = new ArrayList<Corp>();

  String leader;
  int number;
  TERRAIN place;

  public Army(int number, String leader) {
    this.number = number;
    this.leader = leader;
  }

  public Army(int number, String leader, TERRAIN place) {
    this.number = number;
    this.leader = leader;
    this.place = place;
  }

  public void setPlace(TERRAIN place) {
    this.place = place;
  }

  public TERRAIN getPlace() {
    return place;
  }

  public String getLeader() {
    return leader;
  }

  public int getNumber() {
    return number;
  }

  public int getScore() {
    int score = 0;
    for (Corp corp : corps) {
      score += corp.getScore(place);
    }
    return score;
  }

  public void addCorp(Corp corp) {
    corp.setInArmy(true);
    corps.add(corp);
  }

  public void addCorp(int index, Corp corp) {
    corp.setInArmy(true);
    corps.add(index, corp);
  }

  public ArrayList<Corp> getCorps() {
    return corps;
  }

}
