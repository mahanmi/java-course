import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class program {
  Scanner scanner = new Scanner(System.in);

  ArrayList<Country> countries = new ArrayList<Country>();

  public void run() {

    String input = scanner.nextLine().trim();

    Matcher matcher;

    while (!input.equals("end")) {
      if (input.matches("^create country (?<name>) (?<nationality>)$")) {
        matcher = getCommandMatcher(input, "^create country (?<name>) (?<nationality>)$");
        addCountry(matcher.group("name"), matcher.group("nationality"));
      } else if (input.matches(
          "create corps (?<infantry>\\d+) (<cavalry>\\d+) (?<artillery>\\d+) (?<ranked officer>corporal|sergent|lieutenant|capitan|colonel|general|marshal) for (?<country>) (?<number>\\d+)")) {
        matcher = getCommandMatcher(input,
            "create corps (?<infantry>\\d+) (<cavalry>\\d+) (?<artillery>\\d+) (?<ranked officer>corporal|sergent|lieutenant|capitan|colonel|general|marshal) for (?<country>) (?<number>\\d+)");
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

  private void addCountry(String name, String nationality) {
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
}
