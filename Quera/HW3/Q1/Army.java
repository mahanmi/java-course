import java.util.ArrayList;

import troops.Corp;

public class Army {

  private ArrayList<Corp> corps = new ArrayList<Corp>();

  String leader;
  int number;

  public Army(int number, String leader) {
    this.number = number;
    this.leader = leader;
  }

  public void addCorp(Corp corp) {
    corps.add(corp);
  }

  public ArrayList<Corp> getCorps() {
    return corps;
  }

  public int getTotalScore() {
    int totalScore = 0;
    for (Corp corp : corps) {
      totalScore += corp.getScore();
    }
    return totalScore;
  }

  public int getTotal() {
    int total = 0;
    for (Corp corp : corps) {
      total += corp.getTotal();
    }
    return total;
  }

}
