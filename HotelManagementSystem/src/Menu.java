import java.util.Scanner;;
public class Menu {

    private BookingSystem bookingSystem;
    private Scanner input;

    int choice = -1;
    

    public Menu(BookingSystem bookingSystem){
        this.bookingSystem = bookingSystem;
        this.input = new Scanner(System.in);
    }



    public void start(){
        boolean exit = false;

        while (!exit) {
            displayMainMenu();


        }
    }

    private void displayMainMenu() {
        System.out.println("===========================================");
        System.out.println("|            Welcome to X Hotel            |" );
        System.out.println("============================================\n"); 
        System.out.println("[0] Exit");
        System.out.println("[1] Login");
        System.out.println("[2] Sign Up");
    }

    private void customerMenu(){
        System.out.println("[1] Make a Reservation");
        System.out.println("[2] Cancel a Reservation");

        if(choice == 1){
            System.out.println("Enter the date: ");
            
        }
    }

    private void login(){
        String username = getStringInput("Enter username: ");
        String password = getStringInput("Enter password: ");
        boolean success = bookingSystem.login(username, password);

        if (success) {
            System.out.println("\n =============== Welcome ===============");
        }
        else{
            System.out.println("Login failed Invalid credentials");
        }
    }

    private void signUp(){
        System.out.println("\n =============== Sign Up ===============");

        String name = getStringInput("Enter your name: ");
        String contactInfo = getStringInput("Enter your contact info: ");
        String username = getStringInput("Enter a username");
        String password = getStringInput("Enter a password: ");

        Customer customer = new Customer(bookingSystem.getCustomers().size()+1, name, contactInfo, username,password);

        bookingSystem.addCustomer(customer);
        System.out.println("Your account created successfully");
    }

    private void addRoom(){
        System.out.println("\n =============== Add Room ===============");
        int roomNumber = getIntInput("Enter room number: ")
        String roomType = getStringInput("Enter room type: ");
        int roomPrice = //i want to make the each room type has a price ..
        boolean isAvailable = true;

        Room room = new Room(roomNumber,roomType,roomPrice, isAvailable);
        bookingSystem.addRoom(room);
        System.out.println("Room #" + roomNumber + " added successfully.");
    }

    private void displayRooms(){
        System.out.println("\n =============== All rooms ===============");
        bookingSystem.displayAllRooms();
    }

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
        return input.nextInt();
    }

    
    private void adminMenu(Scanner input){

        System.out.println("===========================================");
        System.out.println("|            Welcome to admin page            |" );
        System.out.println("============================================\n");

    }

    private void customerMenu(Scanner input){
        System.out.println("[1] Make a reservation");
        System.out.println("[2] Cancel a reservation");
        System.out.println("[3] View my reservation");
        System.out.println("[3] Search for available rooms");
        System.out.println("[5] Logout");
        
    }
    private void customerMenuReservation(Scanner input){
        System.out.println("Enter the date of stay: ");


    }

    private void customerMenuCancelReservation(Scanner input){
        System.out.println();
    }
}
