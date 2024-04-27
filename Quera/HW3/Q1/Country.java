import java.util.ArrayList;

import troops.*;

public class Country {

  private String name;
  private String nationality;

  private ArrayList<Army> armies = new ArrayList<Army>();
  private ArrayList<Corp> corps = new ArrayList<Corp>();

  public Country(String name, String nationality) {
    this.name = name;
    this.nationality = nationality;
  }

  public String getName() {
    return name;
  }

  public String getNationality() {
    return nationality;
  }

  public int getTotalScore() {
    int totalScore = 0;
    for (Army army : armies) {
      totalScore += army.getTotalScore();
    }
    return totalScore;
  }

  public ArrayList<Army> getArmies() {
    return armies;
  }

  public void addArmy(Army army) {
    armies.add(army);
  }

  public ArrayList<Corp> getCorps() {
    return corps;
  }

  public void addCorp(Corp corp) {
    corps.add(corp);
  }

}