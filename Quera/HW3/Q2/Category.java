import java.util.ArrayList;

public class Category {
  private String name;
  private String ID;
  private ArrayList<Category> subCategories = new ArrayList<Category>();

  public Category(String name, String ID) {
    this.name = name;
    this.ID = ID;
  }

  public String getName() {
    return name;
  }

  public String getID() {
    return ID;
  }

  public void addSubCategory(Category category) {
    subCategories.add(category);
  }

  public ArrayList<Category> getSubCategories() {
    return subCategories;
  }
}
