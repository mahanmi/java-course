package Artworks;

public class Statue extends Artwork {

  private String professorName;

  public Statue(String ID, String title, String author, String professorName, String date, String categoryID,
      String studioID) {
    super(ID, title, author, date, categoryID, studioID);
    this.professorName = professorName;
  }

  public String getProfessorName() {
    return professorName;
  }

}
