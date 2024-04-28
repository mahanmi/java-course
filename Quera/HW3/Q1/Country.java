import java.util.ArrayList;

import troops.*;

public class Country {

  private String name;
  private String nationality;

  private ArrayList<Army> armies = new ArrayList<Army>();
  private ArrayList<Corp> corps = new ArrayList<Corp>();
  private ArrayList<String> allies = new ArrayList<String>();
  private ArrayList<String> enemies = new ArrayList<String>();

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

  public int getScore() {
    int score = 0;
    for (Army army : armies) {
      score += army.getScore();
    }
    return score;
  }

  public int getScore(TERRAIN place) {
    int score = 0;
    for (Army army : armies) {
      if (army.getPlace().equals(place)) {
        score += army.getScore();
      }
    }
    return score;
  }

  public ArrayList<Army> getArmies() {
    return armies;
  }

  public void addArmy(Army army) {
    armies.add(army);
  }

  public void addArmy(int index, Army army) {
    armies.add(index, army);
  }

  public ArrayList<Corp> getCorps() {
    return corps;
  }

  public void addCorp(Corp corp) {
    corps.add(corp);
  }

  public void addCorp(int index, Corp corp) {
    corps.add(index, corp);
  }

  public ArrayList<String> getAllies() {
    return allies;
  }

  public void addAlly(String ally) {
    allies.add(ally);
  }

  public ArrayList<String> getEnemies() {
    return enemies;
  }

  public void addEnemy(String enemy) {
    enemies.add(enemy);
  }

  public void lostWar() {
    for (Army army : armies) {
      for (Corp corp : army.getCorps()) {
        corp.divide();
      }
    }
  }

  public void lostWar(TERRAIN place) {
    for (Army army : armies) {
      if (army.getPlace().equals(place)) {
        for (Corp corp : army.getCorps()) {
          corp.divide();
        }
      }
    }
  }

}