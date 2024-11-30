public class Admin extends User {

  private String adminUserName;
  private String adminPassword;

  public Admin (int userId, String name, String contactInfo, String adminUserName, String adminPassword){

    super(userId,name,contactInfo);
    this.adminUserName = adminUserName;
    this.adminPassword = adminPassword;

  }  


  //getters


  public String getAdminUserName(){
    return this.adminUserName;
  }

  public String getAdminPassword(){
    return this.adminPassword;
  }

  //setters

  public void setAdminUserName(String adminUserName){
    this.adminUserName = adminUserName;

  }

  public void setAdminPassword(String adminPassword){
    this.adminPassword = adminPassword;
  }

  // validate password

  public boolean validatePassword(String password){
    return adminPassword.equals(password);
  }


}