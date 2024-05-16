package Artworks;

public abstract class Artwork {
  private String ID;
  private String title;
  private String Author;
  private String date;
  private String categoryID;
  private String studioID;

  public Artwork(String ID, String title, String Author, String date, String categoryID, String studioID) {
    this.ID = ID;
    this.title = title;
    this.Author = Author;
    this.date = date;
    this.categoryID = categoryID;
    this.studioID = studioID;
  }

  public String getID() {
    return ID;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return Author;
  }

  public String getDate() {
    return date;
  }

  public String getCategoryID() {
    return categoryID;
  }

  public String getStudioID() {
    return studioID;
  }
}
