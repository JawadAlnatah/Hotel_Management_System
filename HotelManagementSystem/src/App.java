public class App {
    public static void main(String[] args) throws Exception {
        BookingSystem bookingSystem = new BookingSystem();
        Menu menu = new Menu(bookingSystem);
        menu.start();
        
    }

     
}
