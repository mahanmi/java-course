public enum Commands {
  ADD_STUDIO(
      "^add studio#(?<username>.+)|(?<password>.+)|(?<studioID>.+)|(?<studioName>.+)|(?<year>.+)|(?<capacity>.+)|(?<address>.+)$");

  public String regex;

  private Commands(String regex) {
    this.regex = regex;
  }
}
