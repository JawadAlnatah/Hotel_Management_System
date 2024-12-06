import java.util.concurrent.atomic.AtomicInteger;

//customer inherit from user
public class Customer extends User {

    private String customerUserName;
    private String customerPassword;

    private static final AtomicInteger idCounter = new AtomicInteger(1); // Unique ID generator



    public Customer(String name, String contactInfo, String customerUserName, String customerPassword) {
        super(idCounter.getAndIncrement(), name, contactInfo); // Generates a unique ID
        this.customerUserName = customerUserName;
        this.customerPassword = customerPassword;
    }

    //getters

    public String getCustomerUserName(){
        return this.customerUserName;
    }

    public String getCustomerPassword(){
        return this.customerPassword;
    }

    
    //setters
    
    public void setCustomerUserName(String customerUserName){
        this.customerUserName = customerUserName;
    }

    public void setCustomerPassword(String customerPassword){
        this.customerPassword = customerPassword;
    }

    //validate the password

    public boolean validatePassword(String password){
        return customerPassword.equals(password);
    }

   
}