import java.util.Scanner;;
public class Menu {
    int breakPoint = 0;
    int choice;
    private Admin admin;
    private Customer customer;

    public Menu(Admin admin, Customer customer){
        this.admin = admin;
        this.customer = customer;
    }

    public void displayMenu(){

        Scanner input = new Scanner(System.in);

        do{

            System.out.println("===========================================");
            System.out.println("|            Welcome to X Hotel            |" );
            System.out.println("============================================");

            System.out.print("[0] Exit");
            System.out.print("[1] Login");
            System.out.print("[2] Signup");

            choice = input.nextInt();












        }while (breakPoint != 0);
    }

    private void adminMenu(Scanner input){

        System.out.println("===========================================");
        System.out.println("|            Welcome to admin page            |" );
        System.out.println("============================================\n");

    }

    private void customerMenu(Scanner input){
        System.out.print("[1] search for Available Rooms");
        System.out.print("[2] Make a Reservation");
        System.out.print("[3] Cancel a Reservation");
        System.out.print("[4] View my Reservation");
        System.out.print("[5] Logout");
        System.out.print("[6] ");
    }
}
