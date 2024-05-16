public enum Commands {
  ADD_STUDIO(
      "add-studio#(?<username>.+)\\|(?<password>.+)\\|(?<studioID>.+)\\|(?<studioName>.+)\\|(?<year>.+)\\|(?<capacity>.+)\\|(?<address>.+)"),
  ADD_CATEGORY(
      "^add-category#(?<username>.+)\\|(?<password>.+)\\|(?<categoryID>.+)\\|(?<categoryName>.+)\\|(?<superCategory>.+)$"),
  ADD_CUSTOMER(
      "^add-costumer#(?<username>.+)\\|(?<password>.+)\\|\\((?<subscriptionNumber>.+)\\)\\|\\((?<cPassword>.+)\\)\\|\\((?<name>.+)\\)\\|\\((?<surname>.+)\\)\\|\\((?<IDnumber>.+)\\)\\|\\((?<DateOfBirth>.+)\\)\\|\\((?<address>.+))$"),
  ADD_STAFF(
      "^add-staff#(?<username>.+)\\|(?<password>.+)\\|\\((?<subscriptionNumber>.+)\\)\\|\\((?<pPassword>.+)\\)\\|\\((?<name>.+)\\)\\|\\((?<surname>.+)\\)\\|\\((?<IDnumber>.+)\\)\\|\\((?<DateOfBirth>.+)\\)\\|\\((?<address>.+))\\|(?<role>staff|professor)$"),
  ADD_MANAGER(
      "^add-staff#(?<username>.+)\\|(?<password>.+)\\|\\((?<subscriptionNumber>.+)\\)\\|\\((?<pPassword>.+)\\)\\|\\((?<name>.+)\\)\\|\\((?<surname>.+)\\)\\|\\((?<IDnumber>.+)\\)\\|\\((?<DateOfBirth>.+)\\)\\|\\((?<address>.+))\\|(?<studioID>.+)$"),
  REMOVE_USER("^remove-user#(?<username>.+)\\|(?<password>.+)\\|\\((?<userID>.+)\\)$"),
  ;

  public String regex;

  private Commands(String regex) {
    this.regex = regex;
  }
}
