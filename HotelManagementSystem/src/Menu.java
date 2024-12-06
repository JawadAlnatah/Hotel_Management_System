import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;;
public class Menu {

    // Aggregation: Menu uses BookingSystem for functionality and operations, but Menu doesn’t manage BookingSystem.
    private BookingSystem bookingSystem; 
    private Scanner input;
    boolean exit = false;

    
    
    // Constructor accepting a BookingSystem instance
    public Menu(BookingSystem bookingSystem){
        this.bookingSystem = bookingSystem;
        this.input = new Scanner(System.in);
    }



    public void start(){

        do {
            displayMainMenu();
            int choice = getIntInput("Enter you choice: ");

            switch (choice) {


               case 0 -> exit = true;
               case 1 -> login();
               case 2 -> signUp();
               default -> System.out.println("Invalid choice. Please try again.\n");
            }
            
        } while (!exit);
        System.out.println("Thank you for using X Hotel!");
    }

    private void displayMainMenu() {
        System.out.println();
        System.out.println("\n===========================================");
        System.out.println("|            Welcome to X Hotel            |");
        System.out.println("===========================================\n");
        System.out.println("[0] Exit");
        System.out.println("[1] Login");
        System.out.println("[2] Sign Up");
    }

    private void customerMenu() {
        boolean customerExit = false;
        do {
            System.out.println("\n=============== Customer Menu ===============");
            System.out.println("[0] Logout");
            System.out.println("[1] Make a Reservation");
            System.out.println("[2] Cancel a Reservation");
            System.out.println("[3] View My Reservations");
            System.out.println("[4] Search Available Rooms");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 0 -> customerExit = true;
                case 1 -> makeReservation();
                case 2 -> cancelReservation();
                case 3 -> viewCustomerReservations();
                case 4 -> searchAvailableRooms();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (!customerExit);
    }

    private void adminMenu() {
        boolean adminExit = false;
        do {
            System.out.println("\n=============== Admin Menu ===============");
            System.out.println("[0] Logout");
            System.out.println("[1] Add Room");
            System.out.println("[2] Remove Room");
            System.out.println("[3] View All Reservations");
            System.out.println("[4] Cancel Reservation");
            System.out.println("[5] View All Rooms");
            System.out.println("[6] Add Admin");
            System.out.println("[7] View All Payments");
    
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 0 -> adminExit = true;
                case 1 -> addRoom();
                case 2 -> removeRoom();
                case 3 -> viewAllReservations();
                case 4 -> cancelReservation();
                case 5 -> displayRooms();
                case 6 -> addAdmin();
                case 7 -> viewAllPayments();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (!adminExit);
    }





    // login handling

    private void login(){
        String username = getStringInput("Enter username: ");
        String password = getStringInput("Enter password: ");
        boolean success = bookingSystem.login(username, password);

        if (success) {
            System.out.println("\n =============== Welcome ===============");
            if (bookingSystem.isLoggedInAdmin()) {
                adminMenu();
            }
            else{
                customerMenu();
            }
        }
        else{
            System.out.println("Login failed Invalid credentials or you don't have an account please sign up");
        }
    }

    private void signUp() {
        System.out.println("\n=============== Sign Up ===============");
    
        String name = getStringInput("Enter your name: ");
        String contactInfo = getStringInput("Enter your contact info: ");
        String username = getStringInput("Enter a username: ");
        String password = getStringInput("Enter a password: ");
    
        // Create a new Customer 
        Customer customer = new Customer(name, contactInfo, username, password);
    
        // Add the customer to the booking system
        bookingSystem.addCustomer(customer);
        System.out.println("Your account created successfully! ");
    }




    // managing rooms
    private void addRoom() {
        System.out.println("\n=============== Add Room ===============");
        int roomNumber = getIntInput("Enter room number: ");
        System.out.println("Choose room type:");
        System.out.println("[1] Single Bedroom ($50)");
        System.out.println("[2] Double Bedroom ($75)");
        System.out.println("[3] Suite ($120)");
        int typeChoice = getIntInput("Enter your choice: ");
        String roomType = switch (typeChoice) {
            case 1 -> "Single Bedroom";
            case 2 -> "Double Bedroom";
            case 3 -> "Suite";
            default -> {
                System.out.println("Invalid choice. Defaulting to Single Bedroom.");
                yield "Single Bedroom";
            }
        };
        double price = switch (typeChoice) {
            case 1 -> 50.0;
            case 2 -> 75.0;
            case 3 -> 120.0;
            default -> 50.0;
        };

        Room room = new Room(roomNumber, roomType, price);
        bookingSystem.addRoom(room);
        System.out.println("Room #" + roomNumber + " (" + roomType + ") added successfully.");
    }

    private void removeRoom() {
        System.out.println("\n=============== Remove Room ===============");
        int roomNumber = getIntInput("Enter room number to remove: ");
        Room room = bookingSystem.findRoomByNumber(roomNumber);

        if (room != null) {
            bookingSystem.removeRoom(room);
            System.out.println("Room #" + roomNumber + " removed successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }
    
    private void displayRooms(){
        System.out.println("\n =============== All rooms ===============");
        bookingSystem.displayAllRooms();
    }

    private void searchAvailableRooms() {
        System.out.println("\n=============== Search Available Rooms ===============");
        String startDateStr = getStringInput("Enter start date (yyyy-MM-dd): ");
        String endDateStr = getStringInput("Enter end date (yyyy-MM-dd): ");
    
        try {
            // Convert the String to LocalDate
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
    
            // Call the updated BookingSystem method
            ArrayList<Room> availableRooms = bookingSystem.searchAvailableRooms(startDate, endDate);
    
            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for the given dates.");
            } else {
                System.out.println("Available Rooms:");
                for (Room room : availableRooms) {
                    room.displayRoomDetails();
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }





    //Managing reservations

    private void viewAllReservations() {
        System.out.println("\n=============== All Reservations ===============");
        bookingSystem.displayAllReservations();
    }


    private void cancelReservation() {
        if (bookingSystem.isLoggedInAdmin()) {
            // Admin functionality
            System.out.println("\n=============== Cancel Reservation ===============");
            bookingSystem.displayAllReservations();
            int reservationId = getIntInput("Enter reservation ID to cancel: ");
            bookingSystem.cancelReservation(reservationId);
            System.out.println("Reservation cancellation complete.");
        } else if (bookingSystem.isLoggedInCustomer()) {
            // Customer functionality
            System.out.println("\n=============== Cancel Reservation ===============");
            Customer customer = bookingSystem.getLoggedInCustomer();
            if (customer == null) {
                System.out.println("Error: No customer is logged in.");
                return;
            }
            bookingSystem.displayReservationsByCustomerId(customer.getUserId());
            int reservationId = getIntInput("Enter reservation ID to cancel: ");
            bookingSystem.cancelReservation(reservationId);
            System.out.println("Reservation cancellation complete.");
        } else {
            System.out.println("Error: No user is logged in.");
        }
    }
    

    private void viewCustomerReservations() {
        System.out.println("\n=============== My Reservations ===============");
    
        // Get the logged-in customer
        Customer loggedInCustomer = bookingSystem.getLoggedInCustomer();
    
        if (loggedInCustomer == null) {
            System.out.println("Error: No customer is logged in. Please log in to view reservations.");
            return;
        }
    
        // Use the logged-in customer's ID to display reservations
        bookingSystem.displayReservationsByCustomerId(loggedInCustomer.getUserId());
    }
    


    private void makeReservation() {
        System.out.println("\n=============== Make a Reservation ===============");
    
        // Retrieve the logged-in customer
        Customer customer = bookingSystem.getLoggedInCustomer();
        if (customer == null) {
            System.out.println("Error: No customer is logged in. Please log in to make a reservation.");
            return;
        }
    
        String startDateStr = getStringInput("Enter start date (yyyy-MM-dd): ");
        String endDateStr = getStringInput("Enter end date (yyyy-MM-dd): ");
    
        try {
            // Parse the input dates
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
    
            // Search for available rooms
            ArrayList<Room> availableRooms = bookingSystem.searchAvailableRooms(startDate, endDate);
    
            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for the given dates.");
                return;
            }
    
            System.out.println("Available Rooms:");
            for (Room room : availableRooms) {
                room.displayRoomDetails();
            }
    
            int roomNumber = getIntInput("Enter the room number you wish to book: ");
            Room selectedRoom = bookingSystem.findRoomByNumber(roomNumber);
    
            if (selectedRoom == null || !availableRooms.contains(selectedRoom)) {
                System.out.println("Invalid room selection. Please try again.");
                return;
            }
    
            // Select payment method
            System.out.println("Choose a payment method:");
            System.out.println("[1] Credit Card");
            System.out.println("[2] Cash");
            System.out.println("[3] Apple pay");
            int paymentChoice = getIntInput("Enter your choice: ");
            String paymentMethod = switch (paymentChoice) {
                case 1 -> "Credit Card";
                case 2 -> "Cash";
                case 3 -> "Apple pay";
                default -> {
                    System.out.println("Invalid choice. Defaulting to Cash.");
                    yield "Cash";
                }
            };
    
            // Create a new reservation
            Reservation reservation = new Reservation(customer, selectedRoom, startDate, endDate);
            bookingSystem.addReservation(reservation);
    
            // Process payment
            double amount = selectedRoom.getRoomPrice();
            Payment payment = new Payment(reservation, amount, paymentMethod);
            bookingSystem.addPayment(payment);
    
            // Link the payment to the reservation
            reservation.setPayment(payment);
    
            System.out.println("Reservation created successfully! Your reservation ID is " + reservation.getReservationId());
            System.out.println("Payment of $" + amount + " has been processed successfully using " + paymentMethod + ".");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }


    //Managing admins

    private void addAdmin() {
        System.out.println("\n=============== Add New Admin ===============");
        String name = getStringInput("Enter name: ");
        String contactInfo = getStringInput("Enter contact info: ");
        String username = getStringInput("Enter username: ");
        String password = getStringInput("Enter password: ");
    
        Admin loggedInAdmin = bookingSystem.getLoggedInAdmin();
        if (loggedInAdmin != null) {
            loggedInAdmin.addNewAdmin(bookingSystem, name, contactInfo, username, password);
        } else {
            System.out.println("Error: You must be logged in as an admin to perform this action.");
        }
    }






    //Managing payments


    private void viewAllPayments() {
        System.out.println("\n=============== All Payments ===============");
        bookingSystem.displayAllPayments();
    }





    //Managing inputs

    private String getStringInput(String message) {
        System.out.print(message);
        return input.nextLine();
    }

    private int getIntInput(String message) {
        System.out.print(message);
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            input.next();
        }
        int result = input.nextInt();
        input.nextLine(); // Clear newline character
        return result;
    }

}
