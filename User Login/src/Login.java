import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Login {
    
    public static void login (Connection con) throws SQLException {

        try{
        Scanner sc = new Scanner(System.in);    
        String login_query = "SELECT user_name,password FROM login_data WHERE user_name = ?";
        PreparedStatement login_pst = con.prepareStatement(login_query);

        System.out.println("\nLogin into your Account\n");
        System.out.print("Enter Username: ");
        String user_name = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        login_pst.setString(1, user_name);

    
        ResultSet credential = login_pst.executeQuery();

        if(credential.next()){
            String Username = credential.getString("user_name");
            String Password = credential.getString("password");
            // System.out.println("Username: "+Username+" Password: "+Password);

            if(Objects.equals(Username, user_name) && Objects.equals(password, Password)){

                System.out.println("\nLogin Successfull\n");
            }
            else{
                System.out.println("\nInvalid password!\n");
            }    
        }
        else{
            System.out.println("\nYour account is not craeted\n");
        }

        sc.close();
        con.close();
        } 
        
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
