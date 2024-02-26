
import java.sql.*;

class DeleteValue {

    public static final String url = "jdbc:mysql://localhost:3306/jaydip115";
    public static final String userName = "root";
    public static final String password = "";
    public static void main(String[] args) {

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
        Connection con = DriverManager.getConnection(url, userName, password);
        Statement st = con.createStatement();
        String query = "DELETE FROM students WHERE id = 2";

        int affectedRow = st.executeUpdate(query);

        if(affectedRow>0){
            System.out.println("Data deleted successfully!");
        }
        else{
            System.out.println("Data not deleted");
        }
        st.close();
        con.close();

        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }
}