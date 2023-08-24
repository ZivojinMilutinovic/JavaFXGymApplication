package src;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBUtil {
    public Connection databaseLink;

    //Connection
    private static Connection conn=null;
    public static void dbConnect(){
        String databaseName="gym-management";
        String databaseUser="root";
        String databasePassword="Kaladontar123!";
        String url="jdbc:mysql://localhost/"+databaseName;
        try{

                Class.forName("com.mysql.cj.jdbc.Driver");
                conn= DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println("Connection with database was successful");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  void dbDisconnect(){
        try{
            if(conn!=null && !conn.isClosed())
                conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");
            //Create statement
            stmt = conn.createStatement();
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }
    //DB Execute Update (For Update/Insert/Delete) Operation
    public static int dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            int key=stmt.executeUpdate(sqlStmt,Statement.RETURN_GENERATED_KEYS);
            return key;
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}
