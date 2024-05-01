public class User {
  private String username;
  private String password;
  private String permission;

  public User(String username, String password, String permission) {
    this.username = username;
    this.password = password;
    this.permission = permission;
  }

  public String getUsername(){
    return username;
  }

  public String getPassword(){
    return password;
  }

  public String getPermission(){
    return permission;
  }
}
