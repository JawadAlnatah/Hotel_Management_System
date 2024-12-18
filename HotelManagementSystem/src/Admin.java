
//admin inherit from user
public class Admin extends User {

  private String adminUserName;
  private String adminPassword;

  private static int adminIdCounter = 1000; // Start admin IDs from a different range

  public Admin (String name, String contactInfo, String adminUserName, String adminPassword){

    super(generateAdminId(),name,contactInfo);

    this.adminUserName = adminUserName;
    this.adminPassword = adminPassword;

  }  


  //getters

  private static int generateAdminId() {
    return adminIdCounter ++;
  }


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
    return this.adminPassword.equals(password);
  }

  //functionality

  //(association) Admin uses the BookingSystem to perform administrative actions.
  public void addRoom(BookingSystem bookingSystem , Room room){
    bookingSystem.addRoom(room);
  }

  public void removeRoom(BookingSystem bookingSystem, Room room){
    bookingSystem.removeRoom(room);
  }

  public void viewAllCustomers(BookingSystem bookingSystem){
    bookingSystem.displayAllCustomers();
  }

  public void addNewAdmin(BookingSystem bookingSystem, String name, String contactInfo, String username, String password) {
    Admin newAdmin = new Admin(name, contactInfo, username, password);
    bookingSystem.addAdmin(newAdmin);
    System.out.println("New admin added successfully! Username: " + username);
}




}