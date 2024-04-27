public enum Command {
    END("\\s*end\\s*"),
    CREATE_COUNTRY("^create country (?<name>) (?<nationality>)$"),
    CREATE_CORP(
            "\"create corps (?<infantry>\\d+) (<cavalry>\\d+) (?<artillery>\\d+) (?<ranked officer>corporal|sergent|lieutenant|capitan|colonel|general|marshal) for (?<country>) (?<number>I||II||III||VI)\""),
            ;

    public String regex;

    Command(String regex) {
        this.regex = regex;
    }
}