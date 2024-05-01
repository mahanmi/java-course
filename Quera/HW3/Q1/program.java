import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

import troops.*;

public class program {
  Scanner scanner = new Scanner(System.in);

  ArrayList<Country> countries = new ArrayList<Country>();

  public void run() {

    String input = scanner.nextLine().trim();

    Matcher matcher;

    while (!input.matches(Command.END.regex)) {
      if (input.matches(Command.CREATE_COUNTRY.regex)) {
        matcher = getCommandMatcher(input, Command.CREATE_COUNTRY.regex);
        addCountry(matcher);
      } else if (input.matches(Command.CREATE_CORP.regex)) {
        matcher = getCommandMatcher(input, Command.CREATE_CORP.regex);
        addCorp(matcher);
      } else if (input.matches(Command.CREATE_ARMY.regex)) {
        matcher = getCommandMatcher(input, Command.CREATE_ARMY.regex);
        addArmy(matcher);
      } else if (input.matches(Command.CREATE_ARMY_WITH_TERRAIN.regex)) {
        matcher = getCommandMatcher(input, Command.CREATE_ARMY_WITH_TERRAIN.regex);
        addArmyWithTerrain(matcher);
      } else if (input.matches(Command.SET_ARMY_TERRAIN.regex)) {
        matcher = getCommandMatcher(input, Command.SET_ARMY_TERRAIN.regex);
        setPlace(matcher);
      } else if (input.matches(Command.ADD_CORP_TO_ARMY.regex)) {
        matcher = getCommandMatcher(input, Command.ADD_CORP_TO_ARMY.regex);
        addCorpToArmy(matcher);
      } else if (input.matches(Command.PRINT_ARMY.regex)) {
        matcher = getCommandMatcher(input, Command.PRINT_ARMY.regex);
        printArmy(matcher);
      } else if (input.matches(Command.PRINT_ARMY_DETAILS.regex)) {
        matcher = getCommandMatcher(input, Command.PRINT_ARMY_DETAILS.regex);
        printArmyDetails(matcher);
      } else if (input.matches(Command.PRINT_COUNTRY.regex)) {
        matcher = getCommandMatcher(input, Command.PRINT_COUNTRY.regex);
        printCountry(matcher);
      } else if (input.matches(Command.PRINT_COUNTRY_DETAILS.regex)) {
        matcher = getCommandMatcher(input, Command.PRINT_COUNTRY_DETAILS.regex);
        printCountryDetails(matcher);
      } else if (input.matches(Command.PRINT_CORP_SCORE.regex)) {
        matcher = getCommandMatcher(input, Command.PRINT_CORP_SCORE.regex);
        printCorpScore(matcher);
      } else if (input.matches(Command.PRINT_ARMY_SCORE.regex)) {
        matcher = getCommandMatcher(input, Command.PRINT_ARMY_SCORE.regex);
        printArmyScore(matcher);
      } else if (input.matches(Command.PRINT_COUNTRY_SCORE.regex)) {
        matcher = getCommandMatcher(input, Command.PRINT_COUNTRY_SCORE.regex);
        printCountryScore(matcher);
      } else if (input.matches(Command.UNION_COUNTRIES.regex)) {
        matcher = getCommandMatcher(input, Command.UNION_COUNTRIES.regex);
        unionCountries(matcher);
      } else if (input.matches(Command.UNION.regex)) {
        matcher = getCommandMatcher(input, Command.UNION.regex);
        union(matcher);
      } else if (input.matches(Command.MADE_ENEMY.regex)) {
        matcher = getCommandMatcher(input, Command.MADE_ENEMY.regex);
        madeEnemy(matcher);
      } else if (input.matches(Command.SHOW_ALLIES.regex)) {
        matcher = getCommandMatcher(input, Command.SHOW_ALLIES.regex);
        showAllies(matcher);
      } else if (input.matches(Command.SHOW_ENEMIES.regex)) {
        matcher = getCommandMatcher(input, Command.SHOW_ENEMIES.regex);
        showEnemies(matcher);
      } else if (input.matches(Command.WAR.regex)) {
        matcher = getCommandMatcher(input, Command.WAR.regex);
        war(matcher);
      } else if (input.matches(Command.WAR_WITH_PLACE.regex)) {
        matcher = getCommandMatcher(input, Command.WAR_WITH_PLACE.regex);
        warWithPlace(matcher);
      } else {
        System.out.println("invalid input!");
      }

      input = scanner.nextLine().trim();
    }

  }

  private Matcher getCommandMatcher(String input, String regex) {

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);

    matcher.find();

    return matcher;

  }

  private void addCountry(Matcher matcher) {
    String name = matcher.group("name");
    String nationality = matcher.group("nationality");
    Country country = new Country(name, nationality);
    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.getName().toLowerCase())) {
        System.out.println("country was created");
        return;
      }
    }
    countries.add(country);
    System.out.println("country " + name + " created");
  }

  private void addCorp(Matcher matcher) {
    int infantry = Integer.parseInt(matcher.group("infantry")) * 1000;
    int cavalry = Integer.parseInt(matcher.group("cavalry")) * 400;
    int artillery = Integer.parseInt(matcher.group("artillery")) * 10;
    String rank = matcher.group("rankedOfficer");
    String country = matcher.group("country");
    String number = matcher.group("number");
    Corp corp = new Corp(Officer.valueOf(rank.toUpperCase()), artillery, cavalry, infantry, number);
    if (corp.getTotal() > 30000) {
      System.out.println("cannot have more than 30k in a corps!");
      return;
    } else {
      for (Country c : countries) {
        if (c.getName().toLowerCase().equals(country.toLowerCase())) {
          for (Corp co : c.getCorps()) {
            if (co.getNumber().equals(number)) {
              System.out.println("this country already has this corps!");
              return;
            }
          }
          if (c.getCorps().isEmpty()) {
            c.addCorp(corp);
            System.out.println("corps " + number + " created successfully!");
            return;
          }
          for (int i = 0; i < c.getCorps().size(); i++) {
            if (c.getCorps().get(i).getIntNumber() > corp.getIntNumber()) {
              c.addCorp(i, corp);
              System.out.println("corps " + number + " created successfully!");
              return;
            }
          }
          c.addCorp(corp);
          System.out.println("corps " + number + " created successfully!");
          return;
        }
      }
      System.out.println("country was not found!");
    }
  }

  private void addArmy(Matcher matcher) {
    int number = matcher.group("number").matches("\\d+") ? Integer.parseInt(matcher.group("number")) : -1;
    String leader = matcher.group("leader");
    String country = matcher.group("country");
    Army army = new Army(number, leader);

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        if (number != -1) {
          for (Army a : c.getArmies()) {
            if (a.getNumber() == number) {
              System.out.println("this country already has this army!");
              return;
            }
          }
          if (c.getArmies().isEmpty()) {
            c.addArmy(army);
            System.out.println("army created successfully!");
            return;
          }
          for (int i = 0; i < c.getArmies().size(); i++) {
            if (c.getArmies().get(i).getNumber() > army.getNumber()) {
              c.addArmy(i, army);
              System.out.println("army created successfully!");
              return;
            }
          }
          c.addArmy(army);
          System.out.println("army created successfully!");
          return;
        }
        System.out.println("army was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void addArmyWithTerrain(Matcher matcher) {
    int number = matcher.group("number").matches("\\d+") ? Integer.parseInt(matcher.group("number")) : -1;
    String leader = matcher.group("leader");
    String country = matcher.group("country");
    TERRAIN place = TERRAIN.valueOf(matcher.group("place"));
    Army army = new Army(number, leader, place);

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        if (number != -1) {
          for (Army a : c.getArmies()) {
            if (a.getNumber() == number) {
              System.out.println("this country already has this army!");
              return;
            }
          }
          c.addArmy(army);
          System.out.println("army created successfully!");
          return;
        }
        System.out.println("army was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void setPlace(Matcher matcher) {
    int number = matcher.group("armyNumber").matches("\\d+") ? Integer.parseInt(matcher.group("armyNumber")) : -1;
    String country = matcher.group("country");
    TERRAIN terrain = TERRAIN.valueOf(matcher.group("place"));

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        for (Army a : c.getArmies()) {
          if (a.getNumber() == number) {
            a.setPlace(terrain);
            System.out.println("set successfully!");
            return;
          }
        }
        System.out.println("army was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void addCorpToArmy(Matcher matcher) {
    String country = matcher.group("country");
    int armyNumber = matcher.group("armyNumber").matches("\\d+") ? Integer.parseInt(matcher.group("armyNumber")) : -1;
    String corpsNumber = matcher.group("corpsNumber");

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        for (Army a : c.getArmies()) {
          if (a.getNumber() == armyNumber) {
            for (Corp co : c.getCorps()) {
              if (co.getNumber().equals(corpsNumber)) {
                if (co.isInArmy()) {
                  System.out.println("this corps is in an army!");
                  return;
                }
                if (a.getCorps().size() == 0) {
                  a.addCorp(co);
                  System.out.println("corps added to army successfully!");
                  return;
                }
                for (int i = 0; i < a.getCorps().size(); i++) {
                  if (co.getIntNumber() < a.getCorps().get(i).getIntNumber()) {
                    a.addCorp(i, co);
                    System.out.println("corps added to army successfully!");
                    return;
                  }
                }
                a.addCorp(co);
                System.out.println("corps added to army successfully!");
                return;
              }
            }
            System.out.println("corps was not found!");
            return;
          }
        }
        System.out.println("army was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void printArmy(Matcher matcher) {
    String country = matcher.group("country");
    int armyNumber = matcher.group("number").matches("\\d+") ? Integer.parseInt(matcher.group("number")) : -1;

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        for (Army a : c.getArmies()) {
          if (a.getNumber() == armyNumber) {
            System.out.println(a.getLeader() + " " + a.getCorps().size());
            return;
          }
        }
        System.out.println("army was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void printArmyDetails(Matcher matcher) {
    String country = matcher.group("country");
    int armyNumber = matcher.group("number").matches("\\d+") ? Integer.parseInt(matcher.group("number")) : -1;

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        for (Army a : c.getArmies()) {
          if (a.getNumber() == armyNumber) {
            System.out.println(a.getLeader() + " " + a.getCorps().size());
            for (int i = 0; i < a.getCorps().size() && i < 3; i++) {
              System.out.println(
                  "    " + a.getCorps().get(i).getInfantry() + " " + a.getCorps().get(i).getCavalry() + " "
                      + a.getCorps().get(i).getArtillery() + " "
                      + a.getCorps().get(i).getOfficer().toString().toLowerCase() + " "
                      + a.getCorps().get(i).getTotal());
            }
            return;
          }
        }
        System.out.println("army was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void printCountry(Matcher matcher) {
    String country = matcher.group("country");
    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        System.out.print(c.getNationality());
        System.out.print(" " + c.getArmies().size());
        for (Army a : c.getArmies()) {
          System.out.print(" " + a.getCorps().size());
        }
        System.out.println();
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void printCountryDetails(Matcher matcher) {
    String country = matcher.group("country");
    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        System.out.print(c.getNationality());
        System.out.print(" " + c.getArmies().size());
        for (Army a : c.getArmies()) {
          System.out.print(" " + a.getCorps().size());
        }
        System.out.println();
        System.out.print(c.getArmies().get(0).getLeader());
        for (int i = 1; i < c.getArmies().size(); i++) {
          System.out.print(" " + c.getArmies().get(i).getLeader());
        }
        System.out.println();
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void printCorpScore(Matcher matcher) {
    String country = matcher.group("country");
    int armyNumber = matcher.group("armyNumber").matches("\\d+") ? Integer.parseInt(matcher.group("armyNumber")) : -1;
    String corpsNumber = matcher.group("corpsNumber");

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        for (Army a : c.getArmies()) {
          if (a.getNumber() == armyNumber) {
            for (Corp co : a.getCorps()) {
              if (co.getNumber().equals(corpsNumber)) {
                System.out.println(co.getScore(a.getPlace()));
                return;
              }
            }
            for (Corp co : c.getCorps()) {
              if (co.getNumber().equals(corpsNumber)) {
                System.out.println("this corps is not in this army!");
                return;
              }
            }
            System.out.println("corps was not found!");
            return;
          }
        }
        System.out.println("army was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void printArmyScore(Matcher matcher) {
    String country = matcher.group("country");
    int armyNumber = matcher.group("armyNumber").matches("\\d+") ? Integer.parseInt(matcher.group("armyNumber")) : -1;

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        for (Army a : c.getArmies()) {
          if (a.getNumber() == armyNumber) {
            System.out.println(a.getScore());
            return;
          }
        }
        System.out.println("army was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void printCountryScore(Matcher matcher) {
    String country = matcher.group("country");

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        System.out.println(c.getScore());
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private boolean canUnion(String country1, String country2) {
    for (Country c1 : countries) {
      if (c1.getName().toLowerCase().equals(country1.toLowerCase())) {
        for (String country : c1.getAllies()) {
          if (country.equals(country2)) {
            return false;
          }
          for (Country c : countries) {
            if (c.getName().toLowerCase().equals(country.toLowerCase())) {
              for (String ca : c.getEnemies()) {
                if (ca.equals(country2)) {
                  return false;
                }
              }
            }
          }
        }
        for (String country : c1.getEnemies()) {
          if (country.toLowerCase().equals(country2.toLowerCase())) {
            return false;
          }
        }

        return true;
      }
    }
    return false;
  }

  private void union(Matcher matcher) {
    String country1 = matcher.group("country1");
    String country2 = matcher.group("country2");

    boolean found = false;

    for (Country c2 : countries) {
      if (c2.getName().toLowerCase().equals(country2.toLowerCase())) {
        found = true;
        break;
      }
    }

    if (!found) {
      System.out.println("country was not found!");
      return;
    }

    for (Country c1 : countries) {
      if (c1.getName().toLowerCase().equals(country1.toLowerCase())) {
        if (!canUnion(country1, country2)) {
          System.out.println("something went wrong!");
          return;
        }
        c1.addAlly(country2);
        for (Country c : countries) {
          if (c.getName().toLowerCase().equals(country2.toLowerCase())) {
            c.addAlly(country1);
            System.out.println("unionized successfully!");
            return;
          }
        }
      }
    }
    System.out.println("country was not found!");
  }

  private void unionCountries(Matcher matcher) {
    String country1 = matcher.group("country1");
    String[] newAllies = matcher.group("listOfCountries").split("\\,");

    for (int i = 0; i < newAllies.length; i++) {
      newAllies[i] = newAllies[i].substring(1, newAllies[i].length() - 1);
    }

    for (String country : newAllies) {
      boolean isFound = false;
      for (Country c : countries) {
        if (c.getName().toLowerCase().equals(country.toLowerCase())) {
          isFound = true;
          break;
        }
      }
      if (!isFound) {
        System.out.println("country was not found!");
        return;
      }
    }

    for (Country c1 : countries) {
      if (c1.getName().toLowerCase().equals(country1.toLowerCase())) {
        for (int j = 0; j < newAllies.length; j++) {
          if (!canUnion(country1, newAllies[j])) {
            System.out.println("something went wrong!");
            return;
          }
          for (int i = 0; i < newAllies.length; i++) {
            if (!canUnion(newAllies[i], newAllies[j])) {
              System.out.println("something went wrong!");
              return;
            }
          }
        }
        for (String country : newAllies) {
          c1.addAlly(country);
          for (Country c : countries) {
            if (c.getName().toLowerCase().equals(country.toLowerCase())) {
              c.addAlly(country1);
            }
          }
        }
        System.out.println("unionized successfully!");
        return;
      }
    }
    System.out.println("country was not found!");

  }

  private boolean canMadeEnemy(String country1, String country2) {
    for (Country c1 : countries) {
      if (c1.getName().toLowerCase().equals(country1.toLowerCase())) {
        for (String country : c1.getAllies()) {
          if (country.toLowerCase().equals(country2.toLowerCase())) {
            return false;
          }
          for (Country c : countries) {
            if (c.getName().toLowerCase().equals(country.toLowerCase())) {
              for (String ca : c.getAllies()) {
                if (ca.toLowerCase().equals(country2.toLowerCase())) {
                  return false;
                }
              }
            }
          }
        }
        for (String country : c1.getEnemies()) {
          if (country.toLowerCase().equals(country2.toLowerCase())) {
            return false;
          }

        }
        return true;
      }
    }
    return false;
  }

  private void madeEnemy(Matcher matcher) {
    String country1 = matcher.group("country1");
    String country2 = matcher.group("country2");

    boolean found = false;

    for (Country c2 : countries) {
      if (c2.getName().toLowerCase().equals(country2.toLowerCase())) {
        found = true;
      }
    }

    if (!found) {
      System.out.println("country was not found!");
      return;
    }

    for (Country c1 : countries) {
      if (c1.getName().toLowerCase().equals(country1.toLowerCase())) {
        if (!canMadeEnemy(country1, country2)) {
          System.out.println("something went wrong!");
          return;
        }
        c1.addEnemy(country2);
        for (Country c : countries) {
          if (c.getName().toLowerCase().equals(country2.toLowerCase())) {
            c.addEnemy(country1);
          }
        }
        System.out.println("enemy made successfully!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private int getCountryScore(String country) {
    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        return c.getScore();
      }
    }
    return -1;
  }

  private void showAllies(Matcher matcher) {
    String country = matcher.group("country");

    ArrayList<String> sortedAllies = new ArrayList<String>();

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        for (String ally : c.getAllies()) {
          if (sortedAllies.isEmpty()) {
            sortedAllies.add(ally);
          } else {
            boolean inserted = false;
            for (int i = 0; i < sortedAllies.size(); i++) {
              if (getCountryScore(ally) > getCountryScore(sortedAllies.get(i))) {
                sortedAllies.add(i, ally);
                inserted = true;
                break;
              }
            }
            if (!inserted) {
              sortedAllies.add(ally);
            }
          }
        }
        for (int i = sortedAllies.size() - 1; i >= 0; i--) {
          System.out.println(sortedAllies.get(i));
        }
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void showEnemies(Matcher matcher) {
    String country = matcher.group("country");

    ArrayList<String> sortedEnemies = new ArrayList<String>();

    for (Country c : countries) {
      if (c.getName().toLowerCase().equals(country.toLowerCase())) {
        for (String enemy : c.getEnemies()) {
          if (sortedEnemies.isEmpty()) {
            sortedEnemies.add(enemy);
          } else {
            boolean inserted = false;
            for (int i = 0; i < sortedEnemies.size(); i++) {
              if (getCountryScore(enemy) > getCountryScore(sortedEnemies.get(i))) {
                sortedEnemies.add(i, enemy);
                inserted = true;
                break;
              }
            }
            if (!inserted) {
              sortedEnemies.add(enemy);
            }
          }
        }
        for (int i = sortedEnemies.size() - 1; i >= 0; i--) {
          System.out.println(sortedEnemies.get(i));
        }
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void war(Matcher matcher) {
    String country1 = matcher.group("country1");
    String country2 = matcher.group("country2");

    for (Country c1 : countries) {
      if (c1.getName().toLowerCase().equals(country1.toLowerCase())) {
        for (Country c2 : countries) {
          if (c2.getName().toLowerCase().equals(country2.toLowerCase())) {
            if (c1.getScore() > c2.getScore()) {
              c2.lostWar();
              System.out.println(country1);
              return;
            } else {
              c1.lostWar();
              System.out.println(country2);
              return;
            }
          }
        }
        System.out.println("country was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }

  private void warWithPlace(Matcher matcher) {
    String country1 = matcher.group("country1");
    String country2 = matcher.group("country2");
    TERRAIN place = TERRAIN.valueOf(matcher.group("place"));

    for (Country c1 : countries) {
      if (c1.getName().toLowerCase().equals(country1.toLowerCase())) {
        for (Country c2 : countries) {
          if (c2.getName().toLowerCase().equals(country2.toLowerCase())) {
            if (c1.getScore(place) > c2.getScore(place)) {
              c2.lostWar();
              System.out.println(country1);
              return;
            } else {
              c1.lostWar();
              System.out.println(country2);
              return;
            }
          }
        }
        System.out.println("country was not found!");
        return;
      }
    }
    System.out.println("country was not found!");
  }
}
