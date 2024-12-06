
// user is a super class of admin and customer
public class User {

    private int userId;
    private String name;
    private String contactInfo;

    public User(int userId, String name, String contactInfo) {
        this.userId = userId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    //getters

    public int getUserId (){
        return this.userId;
    }
   

    public String getName (){
        return this.name;
    }

    public String getContactInfo(){
        return this.contactInfo;
    }

    //setters


    public void setName(String name){
        this.name = name;
    }

    public void setContactInfo (String contactInfo){
        this.contactInfo = contactInfo;
    }



}