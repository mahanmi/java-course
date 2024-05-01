public class Studio {
  private String ID;
  private String name;
  private String year;
  private String capacity;
  private String address;

  Studio(String ID,String name,String year,String capacity,String address){
    this.ID = ID;
    this.name = name;
    this.year = year;
    this.capacity = capacity;
    this.address = address;
  }

  public String getID(){
    return ID;
  }
}
