
import java.sql.*;

class InsertValue {

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
        String query = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
        // Statement st = con.createStatement();
        PreparedStatement pst = con.prepareStatement(query);

        pst.setString(1, "Ajay");
        pst.setInt(2, 21);
        pst.setDouble(3, 97.5);
        

        // int affectedRow = st.executeUpdate(query);
        int affectedRow = pst.executeUpdate();

        if(affectedRow>0){
            System.out.println("Data inserted successfully!");
        }
        else{
            System.out.println("Data not inserted");
        }
        pst.close();
        con.close();

        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }
}