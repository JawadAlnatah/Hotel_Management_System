public class Customer extends User {

    private String customerUserName;
    private String customerPassword;

    public Customer(int userId, String name, String contactInfo, String customerUserName, String customerPassword){
        super(userId, name, contactInfo);
        this.customerUserName =customerUserName;
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