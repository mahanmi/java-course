import java.util.ArrayList;

import troops.Army;

public class Country {

  private String name;
  private String nationality;
  private ArrayList<Army> armies = new ArrayList<Army>();
  

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

  public int getTotalScore()
  {
    int totalScore = 0;
    for (Army army : armies)
    {
      totalScore += army.getScore();
    }
    return totalScore;
  }

}