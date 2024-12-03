import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class BookingSystem {
    
    private ArrayList<Admin> admins;
    private ArrayList<Customer> customers;
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private ArrayList<Payment> payments;

    private Admin loggedInAdmin;
    private Customer loggedInCustomer;


    public BookingSystem (){
        this.admins = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.payments = new ArrayList<>();

        this.loggedInAdmin = null;
        this.loggedInCustomer = null;
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

    public Customer getLoggedInCustomer(){
        return loggedInCustomer;
    }

    public Admin getLoggedInAdmin(){
        return loggedInAdmin;
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

    // Managing Rooms

    public void addRoom(Room room){
        rooms.add(room);

        System.out.println("Room added: "+room.getRoomNumber());
    }

    public void removeRoom(Room room){
        rooms.remove(room);
        
        System.out.println("Room removed: "+room.getRoomNumber());
    }


    public void displayAllRooms(){
        System.out.println("Available Rooms: ");

        for (Room room : rooms){
            room.displayRoomDetails();
        }
    }

    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null; // Room not found
    }

    public ArrayList<Room> searchAvailableRooms(LocalDate startDate, LocalDate endDate) {
        // Your existing logic for searching available rooms based on the dates
        // Example: Loop through rooms and check availability based on the dates
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);  // Assuming isAvailable is enough for now
            }
        }
        return availableRooms;
    }

    private ArrayList<Room> initializeRooms() {
        ArrayList<Room> roomList = new ArrayList<>();
        int roomNumber = 1;
    
        // Add 50 single bedrooms with price $50
        for (int i = 0; i < 50; i++) {
            roomList.add(new Room(roomNumber++, "Single Bedroom", 50.0));
        }
    
        // Add 30 double bedrooms with price $75
        for (int i = 0; i < 30; i++) {
            roomList.add(new Room(roomNumber++, "Double Bedroom", 75.0));
        }
    
        // Add 20 suites with price $120
        for (int i = 0; i < 20; i++) {
            roomList.add(new Room(roomNumber++, "Suite", 120.0));
        }
    
        return roomList;
    }

    // Managing payment

    public void addPayment(Payment payment){
        payments.add(payment);
        System.out.println("Payment added: "+ "Payment ID #"+ payment.getPaymentId());
    }

    public void removePayment(Payment payment){
        payments.remove(payment);
        System.out.println("Payment removed: Payment ID #"+payment.getPaymentId());
    }

    public void displayAllPayments(){
        System.out.println("\n--- All Payments ---");

        for(Payment payment : payments){
            payment.displayPaymentDetails();
        }
    }

    public Payment findPaymentByReservaionId(int reservationId){
        for(Payment payment : payments){
            if(payment.getReservation().getReservationId() == reservationId){
                return payment;
            }
            
        }

        return null; // no payment found
    }



    //Managing reservations


    public void displayAllReservations() {
        System.out.println("\n=============== All Reservations ===============");
        for (Reservation reservation : reservations) {
            reservation.displayReservationDetails();
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservationToCancel = null;

        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel != null) {
            reservations.remove(reservationToCancel);
            System.out.println("Reservation ID " + reservationId + " has been canceled.");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public void displayReservationsByCustomerId(int customerId) {
        System.out.println("\n=============== Reservations for Customer ID " + customerId + " ===============");
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getUserId() == customerId) {
                reservation.displayReservationDetails();
            }
        }
    }









    // login handling

    public boolean login(String username, String password) {
        // Check admins first
        for (Admin admin : admins) {
            if (admin.getAdminUserName().equals(username) && admin.validatePassword(password)) {
                loggedInAdmin = admin;
                loggedInCustomer = null;
                System.out.println("Admin logged in successfully.");
                return true;
            }
        }
    
        // Check customers
        for (Customer customer : customers) {
            if (customer.getCustomerUserName().equals(username) && customer.validatePassword(password)) {
                loggedInCustomer = customer;
                loggedInAdmin = null;
                System.out.println("Customer logged in successfully.");
                return true;
            }
        }
    
        // If no match is found
        System.out.println(" Invalid username or password.");
        return false;
    }

    public void logout() {
        loggedInAdmin = null;
        loggedInCustomer = null;
        System.out.println("Logged out successfully.");
    }

    // Check login state
    public boolean isLoggedInAdmin() {
        return loggedInAdmin != null;
    }

    public boolean isLoggedInCustomer() {
        return loggedInCustomer != null;
    }
}





    
