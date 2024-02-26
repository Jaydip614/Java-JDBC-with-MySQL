//Batch processing with Preapared Statement

import java.sql.*;
import java.util.Scanner;

class InsertBatch_pst {

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
        Scanner sc = new Scanner(System.in);

        // Statement st = con.createStatement();
        String query = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        while (true) {

            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setDouble(3, marks);

            // st.addBatch(query);
            pst.addBatch();

            System.out.print("Do you want add more data(Y/N): ");
            String choice = sc.next();
            if(choice.toUpperCase().equals("N")){
                break;
            }
            
        }
        pst.close();
        sc.close();
        con.close();

        // int affectedRow = st.executeUpdate();
        int[] affectedRowArr = pst.executeBatch();

        for(int i=0; i<affectedRowArr.length; i++){
            if(affectedRowArr[i] == 0){
                System.out.println("Query: "+ i+1 +" not executed successfully!");
            }
        }

        } catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        
    }
}