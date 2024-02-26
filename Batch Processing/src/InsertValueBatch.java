//Batch processing with simple Statement

import java.sql.*;
import java.util.Scanner;

class InsertValueBatch {

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
        Scanner sc = new Scanner(System.in);

        Statement st = con.createStatement();
        while (true) {

            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %o, %f)", name, age, marks);
            st.addBatch(query);

            System.out.print("Do you want add more data(Y/N): ");
            String choice = sc.next();
            if(choice.toUpperCase().equals("N")){
                break;
            }
            
        }
        st.close();
        sc.close();
        con.close();

        // int affectedRow = st.executeUpdate();
        int[] affectedRowArr = st.executeBatch();

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