
import java.sql.*;
import java.util.Scanner;

class Transaction {

    public static final String url = "jdbc:mysql://localhost:3306/jaydip115";
    public static final String userName = "root";
    public static final String password = "@Jedy.614";
    public static void main(String[] args) {

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
        Connection con = DriverManager.getConnection(url, userName, password);
        con.setAutoCommit(false);

        Scanner sc = new Scanner(System.in);
        
        String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE ac_no = ?";
        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE ac_no = ?";

        PreparedStatement debit_pst = con.prepareStatement(debitQuery);
        PreparedStatement credit_pst = con.prepareStatement(creditQuery);

        System.out.print("Enter Sender Account No: ");
        int senderAcNo = sc.nextInt();
        System.out.print("Enter Reciever Account No: ");
        int recieverAcNo = sc.nextInt();
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        debit_pst.setDouble(1, amount);
        debit_pst.setInt(2, senderAcNo);
        credit_pst.setDouble(1, amount); 
        credit_pst.setInt(2, recieverAcNo);

        debit_pst.executeUpdate();
        credit_pst.executeUpdate();

        if(isSufficient(con, senderAcNo, amount)){
            con.commit(); 
            System.out.println("Transaction Successfull");
        }
        else{
            con.rollback();
            System.out.println("Transaction Failed!");
        }
        debit_pst.close();
        credit_pst.close();
        sc.close();
        con.close();
        
        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }

    static boolean isSufficient (Connection con, int senderAcNo, double amount){
    
        try{
        String query = "SELECT balance FROM accounts WHERE ac_no = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, senderAcNo);

        ResultSet ans = pst.executeQuery();
        if(ans.next()){
            double balance = ans.getDouble("balance");
            if(amount > balance){
                return false;
            }
            else{
                return true;
            }
        }
        ans.close();
        pst.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}