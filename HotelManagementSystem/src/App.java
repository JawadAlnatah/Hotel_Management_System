public class App {
    public static void main(String[] args) throws Exception {
        // Aggregation: App creates a BookingSystem instance
        BookingSystem bookingSystem = new BookingSystem();

        // Aggregation: App creates a Menu instance and passes the BookingSystem to it
        Menu menu = new Menu(bookingSystem);
        menu.start();
        
    }

     
}
