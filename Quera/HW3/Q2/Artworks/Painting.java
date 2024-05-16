package Artworks;

class Painting extends Artwork {

  private String investor;
  private int copyNumber;

  public Painting(String ID, String title, String painter, String investor, String date, int copyNumber,
      String categoryID, String studioID) {
    super(ID, title, painter, date, categoryID, studioID);
    this.investor = investor;
    this.copyNumber = copyNumber;
  }

  public String getInvestor() {
    return investor;
  }

  public int getCopyNumber() {
    return copyNumber;
  }

}