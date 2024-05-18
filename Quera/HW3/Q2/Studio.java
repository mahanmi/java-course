import java.util.ArrayList;
import Artworks.*;

public class Studio {
  ArrayList<Artwork> Artworks = new ArrayList<Artwork>();

  private String ID;
  private String name;
  private String year;
  private String capacity;
  private String address;

  Studio(String ID, String name, String year, String capacity, String address) {
    this.ID = ID;
    this.name = name;
    this.year = year;
    this.capacity = capacity;
    this.address = address;
  }

  public String getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public String getYear() {
    return year;
  }

  public String getCapacity() {
    return capacity;
  }

  public String getAddress() {
    return address;
  }

  public ArrayList<Artwork> getArtworks() {
    return Artworks;
  }

  public void addArtwork(Artwork artwork) {
    Artworks.add(artwork);
  }

  public void removeArtwork(Artwork artwork) {
    Artworks.remove(artwork);
  }
}
