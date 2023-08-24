package src.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.Constants;
import src.dao.GymCardDAO;
import src.dao.GymEmployeeDAO;
import src.dao.GymMemeberDAO;
import src.model.GymCard;
import src.model.GymEmployee;
import src.model.GymMember;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class GymStatisticsController implements Initializable {

    @FXML
    private Label numOfMembers;
    @FXML
    private Label numOfEmployees;
    @FXML
    private Label numOfGymMemberships;
    @FXML
    private Label totalIncome;

    private ObservableList<GymEmployee> employees;
    private ObservableList<GymMember> members;
    private ObservableList<GymCard> cards;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            members= GymMemeberDAO.searchMembers();
            cards= GymCardDAO.searchCards();
            employees= GymEmployeeDAO.searchEmployees();

            numOfMembers.setText("Number of members: "+members.stream().count());
            numOfEmployees.setText("Number of employees: "+employees.stream().count());
            numOfGymMemberships.setText("Total number of gym memberships: "+cards.stream().count());
            totalIncome.setText("Total income: "+calculateTotalIncome());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public double calculateTotalIncome(){
        double sum= cards.stream().mapToDouble(card -> card.getPrice() * card.getDiscount() / 100).sum();
        return sum;
    }

    public void goToUsers(){
        setStage("../views/logged-user.fxml");
    }
    public void goToEquipment(){
        setStage("../views/equipment-index.fxml");
    }
    public void goToMembers(){
        setStage("../views/member-index.fxml");
    }
    public void setStage(String stageName){
       Stage primaryStage=(Stage) numOfMembers.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(stageName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
    }


}
