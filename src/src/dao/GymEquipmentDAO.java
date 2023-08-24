package src.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.DBUtil;
import src.model.GymEquipment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GymEquipmentDAO {


    public static ObservableList<GymEquipment> searchEquipment () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM equipment";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<GymEquipment> eqList = getEquipmentList(rsEmps);
            //Return employee object
            return eqList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    //Select * from employees operation
    private static ObservableList<GymEquipment> getEquipmentList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<GymEquipment> eqList = FXCollections.observableArrayList();
        while (rs.next()) {
            GymEquipment  eq=new GymEquipment();
            eq.setEquipment_id(rs.getInt("equipment_id"));
            eq.setName(rs.getString("name"));
            eq.setModel(rs.getString("model"));
            eq.setPrice(rs.getDouble("price"));
            eq.setInfo(rs.getString("info"));

            //Add equipment to the ObservableList
            eqList.add(eq);
        }
        //return equpment (ObservableList of Employees)
        return eqList;
    }
    public static void deleteEqWithId (String empId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =

                "   DELETE FROM equipment" +
                        "         WHERE equipment_id ="+ empId +";" ;
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    public static void updateEquipment (String eqId, String name,String model,String info,double price) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =

                "   UPDATE equipment" +
                        "      SET name = '" + name + "',model='" +model+"',info='"+info+"',price='"+price+
                        "'    WHERE equipment_id = " + eqId + ";";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    public static int insertEq (String name, String model,String info,double price) throws SQLException, ClassNotFoundException {
        //Declare a Create statement
        String updateStmt =
                "INSERT INTO equipment" +
                        "(name ,model, info, price)" +
                        "VALUES" +
                        "('"+name+"', '"+model+"','"+info+"','"+price+"');";

        String selectStmt="SELECT LAST_INSERT_ID()";
        //Execute Create operation
        try {
            int key=DBUtil.dbExecuteUpdate(updateStmt);
            return key;
        } catch (SQLException e) {
            System.out.print("Error occurred while Create Operation: " + e);
            throw e;
        }
    }
}
