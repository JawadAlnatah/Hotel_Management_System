import java.time.LocalDate;

public class Payment {

    private static int nextPaymentId = 1; // Auto-increment ID generator
    private int paymentId;
    private Reservation reservation; // Composition: Payment cannot exist meaningfully without a Reservation
    private double amount;
    private String paymentMethod; // e.g., Credit Card, Cash, etc.
    private LocalDate paymentDate;


    public Payment(Reservation reservation, double amount, String paymentMethod){
        
        this.paymentId = nextPaymentId++;
        this.reservation = reservation;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }  
    
    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    //Utility 

    public void displayPaymentDetails() {
        System.out.println("\n--- Payment Details ---");
        System.out.println("Customer name: "+reservation.getCustomer().getName());
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Reservation ID: " + reservation.getReservationId());
        System.out.println("Amount: $" + amount);
        System.out.println("Payment Method: " + paymentMethod);
        
    }
}