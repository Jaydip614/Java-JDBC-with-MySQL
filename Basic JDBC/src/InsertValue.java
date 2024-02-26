
import java.sql.*;

class InsertValue {

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
        String query = String.format("INSERT INTO students(name, age, marks) VALUES ('%s', %o, %f)","Akash", 20, 92.5);

        int affectedRow = st.executeUpdate(query);

        if(affectedRow>0){
            System.out.println("Data inserted successfully!");
        }
        else{
            System.out.println("Data not inserted");
        }
        st.close();
        con.close();

        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }
}