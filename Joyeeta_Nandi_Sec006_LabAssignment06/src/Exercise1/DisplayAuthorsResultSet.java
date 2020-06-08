package Exercise1;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DisplayAuthorsResultSet {
   public static void main(String args[]) {
	   
	   final String DATABASE_URL = "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD";
	  // final String DATABASE_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
	  final String SELECT_QUERY = 
			  "SELECT authorID, firstName, lastName FROM authors WHERE authorid > 3 ORDER BY firstName";
	  
	    
      // use try-with-resources to connect to and query the database
	  
      try (                                        
            Connection connection = DriverManager.getConnection(DATABASE_URL, "COMP214_F19_zor_47", "password");                     
    	    Statement statement = connection.createStatement();       
    	    ResultSet resultSet = statement.executeQuery(SELECT_QUERY)
         ) 
           {

         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.printf("Authors Result set Table from Books Database:%n%n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++) {
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         }
         System.out.println();
         
         // display query results
         while (resultSet.next()) {
            for (int i = 1; i <= numberOfColumns; i++) {
               System.out.printf("%-8s\t", resultSet.getObject(i));
            }
            System.out.println();
         } 
      }
      catch (SQLException sqlException) {
        // sqlException.printStackTrace();
    	  if(sqlException.getErrorCode() == 1017){
          	 System.out.println("Username or password invalid");
           }
            if(sqlException.getErrorCode() == 17002){
       	 System.out.println("Invalid URL");
       }
      }                                                   
   } 
} 



 