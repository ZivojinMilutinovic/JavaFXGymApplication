package src.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import src.DBUtil;
import src.model.GymCard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GymCardDAO {


    public static ObservableList<GymCard> searchCards () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM cards";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<GymCard> cardsList = getCardsList(rsEmps);
            //Return employee object
            return cardsList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    //Select * from employees operation
    private static ObservableList<GymCard> getCardsList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Card objects
        ObservableList<GymCard> cardsList = FXCollections.observableArrayList();
        while (rs.next()) {
            GymCard card=new GymCard();
            card.setGym_card_id(rs.getInt("gym_card_id"));
            card.setInsert_date(rs.getTimestamp("insert_date"));
            card.setTermination_date(rs.getTimestamp("termination_date"));
            card.setPrice(rs.getDouble("price"));
            card.setDiscount(rs.getInt("discount"));
            card.setUser_id(rs.getInt("user_id"));

            //Add card to the ObservableList
            cardsList.add(card);
        }
        //return cardsList (ObservableList of Employees)
        return cardsList;
    }
    public static void deleteCardWithId (String cardId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =

                "   DELETE FROM cards" +
                        "         WHERE gym_card_id ="+ cardId +";" ;
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
//    public static void updateCard (String empId, String empEmail) throws SQLException, ClassNotFoundException {
//        //Declare a UPDATE statement
//        String updateStmt =
//
//                "   UPDATE employees" +
//                        "      SET EMAIL = '" + empEmail + "'" +
//                        "    WHERE EMPLOYEE_ID = " + empId + ";";
//        //Execute UPDATE operation
//        try {
//            DBUtil.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Error occurred while UPDATE Operation: " + e);
//            throw e;
//        }
//    }
    public static void insertCard (Timestamp termination_date,double price,int discount,int user_id) throws SQLException, ClassNotFoundException {
        //Declare a Create statement
        String updateStmt =
                "INSERT INTO cards" +
                        "(insert_date ,termination_date, price,discount, user_id)" +
                        "VALUES" +
                        "(SYSDATE(), '"+termination_date+"','"+price+"', '"+discount+"','"+user_id+"');";
        //Execute Create operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while Create Operation: " + e);
            throw e;
        }
    }
}
