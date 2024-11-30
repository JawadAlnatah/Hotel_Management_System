import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    //date code 
    int year,month,day;
    int year2,month2,day2;
    LocalDate checkIn = LocalDate.of(year,month,day);
    LocalDate checkOut = LocalDate.of(year2,month2,day2);
    private double DaysInRoom = ChronoUnit.DAYS.between(checkIn,checkOut);
    
    
    private int reservationId;
    private double totalPrice = 175;
    
    

    double calculateTotalPrice()
    {
        totalPrice = totalPrice*DaysInRoom;
    }
    public Reservation(){
        
    }
}