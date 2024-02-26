import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

class UserLogin {

    private static String url = "jdbc:mysql://localhost:3306/jaydip115";
    private static String userName = "root";
    private static String password = "";
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

        System.out.print("Enter your choice (1/2): ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1: Create_Account(con);
                    break;
            case 2: Login(con);
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

    private static void Create_Account(Connection con){
    
        try{
        con.setAutoCommit(false);
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
        con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void Login (Connection con){

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
                con.rollback();
            }
            else{
                System.out.println("\nInvalid password!\n");
                con.rollback();
            }    
        }
        else{
            System.out.println("\nYour account is not craeted\n");
            con.commit();
        }

        sc.close();
        con.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}