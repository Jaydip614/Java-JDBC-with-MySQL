
import java.sql.*;

class FetchValue {

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
        String query = "SELECT *FROM myinfo";

        ResultSet ans = st.executeQuery(query);

        while (ans.next()) {
            int id = ans.getInt("id");
            String name = ans.getString("name");
            int age = ans.getInt("age");

            System.out.println("id: "+ id);
            System.out.println("name: "+ name);
            System.out.println("age: "+ age);
        }
        ans.close();
        st.close();
        con.close();


        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }
}