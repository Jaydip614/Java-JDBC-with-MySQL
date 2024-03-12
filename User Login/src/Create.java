import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Create {
    
     public static void create_Account(Connection con){
    
        try{
        Scanner sc = new Scanner(System.in);    
        String create_query = "INSERT INTO login_data (email, user_name, password) VALUES (?, ?, ?)";
        String fetch_email = "SELECT  email FROM login_data WHERE email = ?";
        String fetch_user = "SELECT user_name FROM login_data WHERE user_name = ?";

        PreparedStatement create_pst = con.prepareStatement(create_query);
        PreparedStatement email_pst = con.prepareStatement(fetch_email);
        PreparedStatement user_pst = con.prepareStatement(fetch_user);


        System.out.println("\nCreate New Account\n");
        System.out.print("Enter Email ID: ");
        String email = sc.next();
        System.out.print("Enter UserName: ");
        String user_name = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();

        create_pst.setString(1, email);
        create_pst.setString(2, user_name);
        create_pst.setString(3, password);

        email_pst.setString(1, email);
        user_pst.setString(1, user_name);

        ResultSet email_exist = email_pst.executeQuery();
        ResultSet user_exist = user_pst.executeQuery();
        int affectedRows = create_pst.executeUpdate();

        if(email_exist.next()){
            System.out.println("\n"+email+" is already exists\n");
        }
        else if(user_exist.next()){
            System.out.println("\n"+user_name+" is taken by someone! Try onther Username\n");
        }
        else if(affectedRows>0){
            System.out.println("\nYour Account is created successfully\n");
        }

        
        sc.close();
        user_pst.close();
        email_pst.close();
        create_pst.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
