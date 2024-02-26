
import java.sql.*;

class FetchValue {

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
        String query = "SELECT marks FROM students WHERE id = ?";

        // Statement st = con.createStatement();
        PreparedStatement pst = con.prepareStatement(query);

        pst.setDouble(1, 1);
        

        // ResultSet ans = st.executeQuery(query);
        ResultSet ans = pst.executeQuery();

        if(ans.next()){
            double marks = ans.getDouble("marks");
            System.out.println("Marks: "+marks);
        }
        else{
            System.out.println("Marks not found!");
        }

        // while (ans.next()) {
        //     int id = ans.getInt("id");
        //     String name = ans.getString("name");
        //     int age = ans.getInt("age");

        //     System.out.println("id: "+ id);
        //     System.out.println("name: "+ name);
        //     System.out.println("age: "+ age);
        // }

        ans.close();
        pst.close();
        con.close();

        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }
}