public class Room {

    private int roomNumber;
    private String roomType;
    private double roomPrice;
    private boolean isAvailable;
    

    public Room  (int roomNumber,String roomType,double roomPrice){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
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

    //setters

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType){
        this.roomType = roomType;
    }

    public void setRoomPrice(double roomPrice){
        this.roomPrice = roomPrice;
    }

    
}