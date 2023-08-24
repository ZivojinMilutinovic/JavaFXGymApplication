package src.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.DBUtil;
import src.model.GymMember;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GymMemeberDAO {

    public static ObservableList<GymMember> searchMembers () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM member";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<GymMember> eqList = getMemberList(rsEmps);
            //Return employee object
            return eqList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    //Select * from members operation
    private static ObservableList<GymMember> getMemberList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of member objects
        ObservableList<GymMember> memberList = FXCollections.observableArrayList();
        while (rs.next()) {
            GymMember  member=new GymMember();
            member.setMember_id(rs.getInt("member_id"));
            member.setFirst_name(rs.getString("first_name"));
            member.setLast_name(rs.getString("last_name"));
            member.setEmail(rs.getString("email"));
            member.setPhone_number(rs.getString("phone_number"));

            //Add employee to the ObservableList
            memberList.add(member);
        }
        //return empList (ObservableList of Employees)
        return memberList;
    }
    public static void deleteMemberWithId (String memberId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =

                "   DELETE FROM member" +
                        "         WHERE member_id ="+ memberId +";" ;
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
    public static void updateMember (String memberId, String firstName,String lastName,String email,String phonenumber) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =

                "   UPDATE member" +
                        "      SET first_name = '" + firstName + "',last_name='" +lastName+"',email='"+email+"',phone_number='"+phonenumber+
                        "'    WHERE member_id = " + memberId + ";";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
    public static int insertMember (String firstName,String lastName,String email,String phonenumber) throws SQLException, ClassNotFoundException {
        //Declare a Create statement
        String updateStmt =
                "INSERT INTO member" +
                        "(first_name ,last_name,email, phone_number)" +
                        "VALUES" +
                        "('"+firstName+"', '"+lastName+"','"+email+"','"+phonenumber+"');";

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
