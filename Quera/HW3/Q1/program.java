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
      }

      input = scanner.nextLine().trim();
    }

  }

  private Matcher getCommandMatcher(String input, String regex) {

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);

    return matcher;

  }

  private void addCountry(Matcher matcher) {
    String name = matcher.group("name");
    String nationality = matcher.group("nationality");
    Country country = new Country(name, nationality);
    for (Country c : countries) {
      if (c.getName().equals(country.getName())) {
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
    String rank = matcher.group("ranked officer");
    String country = matcher.group("country");
    String number = matcher.group("number");
    Corp corp = new Corp(Officer.valueOf(rank.toUpperCase()), artillery, cavalry, infantry, number);
    if (corp.getTotal() > 30000) {
      System.out.println("cannot have more than 30k in a corps!");
    } else {
      boolean found = false;
      for (Country c : countries) {
        if (c.getName().equals(country)) {
          found = true;
          for (Corp co : c.getCorps()) {
            if (co.getNumber().equals(number)) {
              System.out.println("this country already has this corps!");
              break;
            }
          }
          break;
        }
      }
      if (!found) {
        System.out.println("country was not found!");
      } else {
        System.out.println("corps " + number + " created successfully!");
      }
    }
  }
}
