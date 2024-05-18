package Artworks;

import java.util.ArrayList;

public class Painting extends Artwork {

  ArrayList<Borrower> Borrowers = new ArrayList<Borrower>();

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

  public ArrayList<Borrower> getBorrowers() {
    return Borrowers;
  }

  public void addBorrower(Borrower borrower) {
    Borrowers.add(borrower);
  }

  public void removeBorrower(Borrower borrower) {
    Borrowers.remove(borrower);
  }

}