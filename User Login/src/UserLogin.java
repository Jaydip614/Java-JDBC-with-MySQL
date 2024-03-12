import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

class UserLogin {

    private static String url = "jdbc:mysql://localhost:3306/jaydip115";
    private static String userName = "root";
    private static String password = "@Jedy.614";
    public static void main(String[] args) {

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
        Connection con = DriverManager.getConnection(url, userName, password);
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWelcome to Gec Gandhinagar\n");
        System.out.println("1.Create New Account");
        System.out.println("2.Login");
        System.out.println("3.Delete");

        System.out.print("Enter your choice (1/2): ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1: Create.create_Account(con);
                    break;
            case 2: Login.login(con);
                    break;
            case 3: Delete.delete(con);
                    break;       
        
            default:
                break;
        }

        sc.close();
        con.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
   
}