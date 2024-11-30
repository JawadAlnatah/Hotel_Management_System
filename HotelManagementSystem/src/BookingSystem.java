import java.util.ArrayList;

public class BookingSystem {
    
    private ArrayList<Admin> admins;
    private ArrayList<Customer> customers;
    private ArrayList<Room> rooms;

    public BookingSystem (){
        this.admins = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    //Getters
    public ArrayList<Admin> getAdmins(){
        return admins;
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    //Managing Admins

    public void addAdmin(Admin admin){
        admins.add(admin);

        System.out.println("Admin added: "+ admin.getAdminUserName());

    }

    public void removeAdmin(Admin admin){
        admins.remove(admin);

        System.out.println("Admin removed: "+ admin.getAdminUserName());
    }

    //Managing Customers

    public void addCustomer(Customer customer){
        customers.add(customer);

        System.out.println("Customer added: "+ customer.getCustomerUserName());
    }

    public void removeCustomer(Customer customer){
        customers.remove(customer);

        System.out.println("Customer removed: "+ customer.getCustomerUserName());
    }

    // Managing Rooms

    public void addRoom(Room room){
        rooms.add(room);

        System.out.println("Room added: "+room.getRoomNumber());
    }

    public void removeRoom(Room room){
        rooms.remove(room);
        
        System.out.println("Room removed: "+room.getRoomNumber());
    }

    //utility methods

    public void displayAllRooms(){
        System.out.println("Rooms: ");

        for (Room room : rooms){
            System.out.println("- Room #" + room.getRoomNumber());
        }
    }

    public void displayAllCustomers(){
        System.out.println("Customers: ");
        for(Customer customer : customers){
            System.out.println("- " + customer.getCustomerUserName() + " (ID: " + customer.getUserId() + ")");
        }
    }

    public Customer findCustomerById(int customerId){
        for (Customer customer : customers){
            if (customer.getUserId() == customerId) {
                return customer;
            }
        }

        return null; //customer not found

    }


    // login handling

    public boolean login(String username, String password) {
        // Check admins first
        for (Admin admin : admins) {
            if (admin.getAdminUserName().equals(username) && admin.validatePassword(password)) {
                System.out.println("Admin logged in successfully.");
                return true;
            }
        }
    
        // Check customers
        for (Customer customer : customers) {
            if (customer.getCustomerUserName().equals(username) && customer.validatePassword(password)) {
                System.out.println("Customer logged in successfully.");
                return true;
            }
        }
    
        // If no match is found
        System.out.println("Invalid username or password.");
        return false;
    }






    
}