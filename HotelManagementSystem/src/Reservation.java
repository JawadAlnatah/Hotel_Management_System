import java.time.LocalDate;

public class Reservation {
     
    private int reservationId;
    private Customer customer;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(int reservation, Customer customer,Room room, LocalDate checkInDate, LocalDate checkOutDate){

        this.reservationId = reservation;
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
        System.out.println("Start Date: " + checkInDate);
        System.out.println("End Date: " + checkOutDate);
    }
    
    
    
    
    

    
}