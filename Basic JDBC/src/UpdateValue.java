
import java.sql.*;

class UpdateValue {

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
        Statement st = con.createStatement();
        String query = String.format("UPDATE students SET marks = %f WHERE id = %o",85.5,2);

        int affectedRow = st.executeUpdate(query);

        if(affectedRow>0){
            System.out.println("Data Updated successfully!");
        }
        else{
            System.out.println("Data not Updated");
        }
        st.close();
        con.close();

        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }
}