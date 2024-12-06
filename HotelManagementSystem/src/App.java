public class App {
    public static void main(String[] args) throws Exception {
        // Composition: App creates and owns the BookingSystem instance directly in main
        BookingSystem bookingSystem = new BookingSystem();

        // Aggregation: Menu uses BookingSystem but does not own or manage its lifecycle
        Menu menu = new Menu(bookingSystem);
        menu.start();
        
    }

     
}
