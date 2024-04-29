public enum Command {
    END("\\s*end\\s*"),
    CREATE_COUNTRY("^create country (?<name>\\S+) (?<nationality>\\S+)$"),
    CREATE_CORP(
            "^create corps (?<infantry>\\d+) (?<cavalry>\\d+) (?<artillery>\\d+) (?<rankedOfficer>corporal|sergent|lieutenant|capitan|colonel|general|marshal) for (?<country>\\S+) (?<number>I||II||III||IV)$"),
    CREATE_ARMY("^create army (?<number>\\S+) (?<leader>\\S+) for (?<country>\\S+)$"),
    CREATE_ARMY_WITH_TERRAIN("^create army (?<number>\\S+) (?<leader>\\S+) for (?<country>\\S+) in (?<place>\\S+)$"),
    SET_ARMY_TERRAIN("^set place for (?<country>\\S+) (?<armyNumber>\\S+) in (?<place>\\S+)$"),
    ADD_CORP_TO_ARMY("^add corps (?<corpsNumber>\\S+) to army (?<armyNumber>\\S+) of (?<country>\\S+)$"),
    PRINT_ARMY("^print army (?<number>\\S+) (?<country>\\S+)$"),
    PRINT_ARMY_DETAILS("^print army with details (?<number>\\S+) (?<country>\\S+)$"),
    PRINT_COUNTRY("^print country (?<country>\\S+)$"),
    PRINT_COUNTRY_DETAILS("^print country with details (?<country>\\S+)$"),
    PRINT_CORP_SCORE("^print score of (?<country>\\S+) (?<armyNumber>\\S+) (?<corpsNumber>I||II||III||IV)$"),
    PRINT_ARMY_SCORE("^print score of (?<country>\\S+) (?<armyNumber>\\S+)$"),
    PRINT_COUNTRY_SCORE("^print score of (?<country>\\S+)$"),
    UNION("^(?<country1>\\S+) join union with (?<country2>\\S+)$"),
    UNION_COUNTRIES("^(?<country1>\\S+) join union with \\[(?<listOfCountries>\\S+)\\]$"),
    MADE_ENEMY("^(?<country1>\\S+) made enemy of (?<country2>\\S+)$"),
    SHOW_ALLIES("^show friends of (?<country>\\S+)$"),
    SHOW_ENEMIES("^show enemies of (?<country>\\S+)$"),
    WAR("^war between (?<country1>\\S+) and (?<country2>\\S+)$"),
    WAR_WITH_PLACE("^war between (?<country1>\\S+) and (?<country2>\\S+) in (?<place>\\S+)$");

    public String regex;

    Command(String regex) {
        this.regex = regex;
    }
}