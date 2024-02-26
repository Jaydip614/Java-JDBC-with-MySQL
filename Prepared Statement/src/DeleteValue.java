
import java.sql.*;

class DeleteValue {

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
        String query = "DELETE FROM students WHERE id = ?";

        // Statement st = con.createStatement();
        PreparedStatement pst = con.prepareStatement(query);

        pst.setInt(1, 6);

        int affectedRow = pst.executeUpdate();

        if(affectedRow>0){
            System.out.println("Data deleted successfully!");
        }
        else{
            System.out.println("Data not deleted");
        }
        pst.close();
        con.close();

        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }
}