public class Room {

    private int roomNumber;
    private String roomType; //single , double , suite
    private double roomPrice;
    private boolean isAvailable;
    

    public Room  (int roomNumber,String roomType,double roomPrice){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isAvailable =true;
    }


    //getters

    public int getRoomNumber(){
        return this.roomNumber;
    }

    public String getRoomType(){
        return this.roomType;
    }

    public double getRoomPrice(){
        return this.roomPrice;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    //setters

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public void displayRoomDetails() {
        System.out.printf("Room #%d - %s - $%.2f - %s%n", 
        roomNumber, roomType, roomPrice, (isAvailable ? "Available" : "Occupied"));
    }

    
}