package src.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.DBUtil;
import src.model.GymEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GymEmployeeDAO {
    public static GymEmployee searchEmployee (String email,String password) throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM employees WHERE email='"+email+"' and password='"+password+"'";

        try {

            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

            GymEmployee employee = getEmployeeFromResultSet(rsEmp);

            return employee;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + email + " email, an error occurred: " + e);
            throw e;
        }
    }
    private static GymEmployee getEmployeeFromResultSet(ResultSet rs) throws SQLException
    {
        GymEmployee emp=null;
        if(rs.next()){
            emp=new GymEmployee();
            emp.setEmployee_id(rs.getInt("employee_id"));
            emp.setEmail(rs.getString("email"));
            emp.setFirst_name(rs.getString("first_name"));
            emp.setPassword(rs.getString("password"));
            emp.setLast_name(rs.getString("last_name"));
            emp.setPhone_number(rs.getString("phone_number"));
            emp.setInsert_date(rs.getTimestamp("insert_date"));
        }

        return emp;
    }

    public static ObservableList<GymEmployee> searchEmployees () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM employees";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<GymEmployee> empList = getEmployeeList(rsEmps);
            //Return employee object
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    //Select * from employees operation
    private static ObservableList<GymEmployee> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<GymEmployee> empList = FXCollections.observableArrayList();
        while (rs.next()) {
          GymEmployee  emp=new GymEmployee();
            emp.setEmployee_id(rs.getInt("employee_id"));
            emp.setEmail(rs.getString("email"));
            emp.setFirst_name(rs.getString("first_name"));
            emp.setPassword(rs.getString("password"));
            emp.setLast_name(rs.getString("last_name"));
            emp.setPhone_number(rs.getString("phone_number"));
            emp.setInsert_date(rs.getTimestamp("insert_date"));
            //Add employee to the ObservableList
            empList.add(emp);
        }
        //return empList (ObservableList of Employees)
        return empList;
    }
    public static void deleteEmpWithId (String empId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =

                        "   DELETE FROM employees" +
                        "         WHERE employee_id ="+ empId +";" ;
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    public static void updateEmpEmail (String empId, String empEmail) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =

                        "   UPDATE employees" +
                        "      SET EMAIL = '" + empEmail + "'" +
                        "    WHERE EMPLOYEE_ID = " + empId + ";";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    public static void insertEmp (String name, String lastname, String email,String password,String phone_number) throws SQLException, ClassNotFoundException {
        //Declare a Create statement
        String updateStmt =
                        "INSERT INTO employees" +
                        "(first_name ,last_name, email, insert_date, password, phone_number)" +
                        "VALUES" +
                        "('"+name+"', '"+lastname+"','"+email+"', SYSDATE(), '"+password+"','"+phone_number+"');";
        //Execute Create operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while Create Operation: " + e);
            throw e;
        }
    }
}
