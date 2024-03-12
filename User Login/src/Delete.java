import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Delete {

    public static void delete (Connection con){

        try{
        con.setAutoCommit(false);
        Scanner sc = new Scanner(System.in);

        String delete_query = "DELETE FROM login_data WHERE user_name = ?";
        String fetch_account = "SELECT user_name,password FROM login_data WHERE user_name = ?";

        PreparedStatement delete_pst =  con.prepareStatement(delete_query);
        PreparedStatement fetch_pst = con.prepareStatement(fetch_account);

        System.out.println("\nDelete your Account\n");
        System.out.print("Enter Username: ");
        String user_name = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        delete_pst.setString(1, user_name);
        fetch_pst.setString(1, user_name);

        ResultSet credential = fetch_pst.executeQuery();
        

        if(credential.next()){
            int affectedRows = delete_pst.executeUpdate();

            String UserName = credential.getString("user_name");
            String Password = credential.getString("password");

            if(Objects.equals(UserName, user_name) && Objects.equals(Password, password)){

                if(affectedRows == 1){
                System.out.println("\nAccount deleted Successfully\n");
                con.commit();
                }
            }
            else{
                System.out.println("\nInvalid Username and Password");
                con.rollback();
            }

        }

        fetch_pst.close();
        delete_pst.close();
        sc.close();
        con.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
