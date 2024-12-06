import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class BookingSystem {

    // Associations with other classes
    
    private ArrayList<Admin> admins; // Association with Admin
    private ArrayList<Customer> customers; // Association with Customer
    private ArrayList<Room> rooms; // Association with Room
    private ArrayList<Reservation> reservations; // Association with Reservation
    private ArrayList<Payment> payments; // Association with Payment

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

        this.rooms = initializeRooms();

        // default admin
        Admin defaultAdmin = new Admin("admin", "admin@gmail.com", "admin", "admin");
        this.admins.add(defaultAdmin);
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

    public ArrayList<Reservation> getReservations() {
        return reservations;
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

    public void addNewAdmin(BookingSystem bookingSystem, String name, String contactInfo, String username, String password) {
        Admin newAdmin = new Admin(name, contactInfo, username, password);
        bookingSystem.addAdmin(newAdmin);
        System.out.println("New admin added successfully! Username: " + username);
    }

    public void removeAdmin(Admin admin){
        admins.remove(admin);

        System.out.println("Admin removed: "+ admin.getAdminUserName());
    }

    //Managing Customers

    public void addCustomer(Customer customer){
        customers.add(customer);
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

    public Customer registerCustomer(String name, String contactInfo, String username, String password) {
        Customer newCustomer = new Customer(name, contactInfo, username, password); 
        customers.add(newCustomer);
        System.out.println("Customer registered successfully! ID: " + newCustomer.getUserId());
        return newCustomer;
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


    public void displayAllRooms() {
        System.out.println("Rooms:");
        for (Room room : rooms) {
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

    public ArrayList<Room> searchAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        ArrayList<Room> availableRooms = new ArrayList<>(rooms); // Start with all rooms
    
        for (Reservation reservation : reservations) {
            Room reservedRoom = reservation.getRoom();
    
            // Check if the requested dates overlap with the reservation
            if ((checkInDate.isBefore(reservation.getCheckOutDate()) 
                && checkOutDate.isAfter(reservation.getCheckInDate()))) {
                availableRooms.remove(reservedRoom); // Remove the reserved room
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

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    
        // Mark the room as unavailable
        reservation.getRoom().setAvailable(false);
        System.out.println("\nReservation added successfully for room #" + reservation.getRoom().getRoomNumber()+"\n");
    }

    public void displayAllReservations() {
        System.out.println("\n=============== All Reservations ===============");
        for (Reservation reservation : reservations) {
            reservation.displayReservationDetails();
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservationToCancel = null;
    
        // Find the reservation to cancel
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                reservationToCancel = reservation;
                break;
            }
        }
    
        if (reservationToCancel != null) {
            // Remove the reservation from the list
            reservations.remove(reservationToCancel);
    
            // Mark the associated room as available
            reservationToCancel.getRoom().setAvailable(true);
    
            System.out.println("Reservation ID " + reservationId + " has been canceled.");
        } else {
            System.out.println("Reservation not found.");
        }
    }
    

    public void displayReservationsByCustomerId(int customerId) {
        boolean hasReservations = false;
        System.out.println("\n=============== Reservations for Customer ID " + customerId + " ===============");
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getUserId() == customerId) {
                reservation.displayReservationDetails();
                hasReservations = true;
            }
        }
    
        if (!hasReservations) {
            System.out.println("No reservations found for this customer.");
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





    
