package Users;

import java.util.ArrayList;
import Artworks.*;

public class User {
  ArrayList<Artwork> borrowedArtworks = new ArrayList<Artwork>();
  ArrayList<Artwork> boughtArtworks = new ArrayList<Artwork>();

  private String username;
  private String password;
  private String permission;
  private int debt;

  public User(String username, String password, String permission) {
    this.username = username;
    this.password = password;
    this.permission = permission;
    this.debt = 0;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getPermission() {
    return permission;
  }

  public ArrayList<Artwork> getBorrowedArtworks() {
    return borrowedArtworks;
  }

  public ArrayList<Artwork> getBoughtArtworks() {
    return boughtArtworks;
  }

  public int getDebt() {
    return debt;
  }

  public void addDebt(int debt) {
    this.debt += debt;
  }

  public void payDebt(int debt) {
    this.debt -= debt;
  }

  public void borrowArtwork(Artwork artwork) {
    borrowedArtworks.add(artwork);
  }

  public void returnArtwork(Artwork artwork) {
    borrowedArtworks.remove(artwork);
  }

  public void buyArtwork(Artwork artwork) {
    boughtArtworks.add(artwork);
  }

  public void setPermission(String input) {
    this.permission = input;
  }
}
