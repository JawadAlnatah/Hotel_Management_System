import java.time.LocalDate;

public class Reservation {
     
    private int reservationId;
    private Customer customer;  // Association: Reservation is associated with a Customer to identify who made the reservation.
    private Room room;  // Association: Reservation is associated with a specific Room, which is used but not owned by Reservation.
    private Payment payment; // Composition: Reservation directly owns the Payment object, and if the reservation is deleted, the payment is too.
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private static int reservationCounter = 1; // Static counter for unique reservation IDs


    public Reservation(Customer customer,Room room, LocalDate checkInDate, LocalDate checkOutDate){

        this.reservationId = reservationCounter++;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    //setters and getters

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Payment getPayment() {
        return payment;
    }
    
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getCheckInDate(){
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate){
        this.checkInDate =checkInDate;
    }

    public LocalDate getCheckOutDate(){
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate){
        this.checkOutDate = checkOutDate;
    }

    //utility

    public void displayReservationDetails() {
        System.out.println("\n--- Reservation Details ---");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Start Date: " + checkInDate);
        System.out.println("End Date: " + checkOutDate);
    
        if (payment != null) {
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Amount Paid: $" + payment.getAmount());
            System.out.println("Payment Method: " + payment.getPaymentMethod());
        } else {
            System.out.println("Payment: Not yet processed.");
        }
    }
    
    
    
    
    
    

    
}