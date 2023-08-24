package src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import src.Constants;
import src.dao.GymCardDAO;
import src.helpers.AlertBox;
import src.model.GymMember;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ResourceBundle;


public class MemberCardController implements Initializable {


    @FXML
    private Label username;
    @FXML
    private TextField price;
    @FXML
    private TextField discount;
    @FXML
    private DatePicker termination_date;

    private GymMember member;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onCreate(){
        if(termination_date.getValue()==null){
            AlertBox.display("Error","Some of the filed is not in correct format");
            return;
        }
        try{
            Integer.parseInt(discount.getText());
            Double.parseDouble(price.getText());
        }catch (NumberFormatException e){
            AlertBox.display("Error","Some of the filed is not in correct format");
        }

         Timestamp timestamp= Timestamp.valueOf(termination_date.getValue().atStartOfDay()) ;
        try {
            GymCardDAO.insertCard(timestamp,Double.parseDouble(price.getText()),Integer.parseInt(discount.getText()), member.getMember_id());
            goToMembers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setGymMember(GymMember member){
        this.member=member;
        username.setText("Member "+this.member.getFirst_name()+" "+this.member.getFirst_name());
    }
    public void goToMembers(){
        setStage("../views/member-index.fxml");
    }
    public void goToUsers(){
        setStage("../views/logged-user.fxml");
    }
    public void goToEquipment(){
        setStage("../views/equipment-index.fxml");
    }
    public void goToGymStatistics(){
        setStage("../views/gym-statistics.fxml");
    }
    public void setStage(String stageName){
        Stage primaryStage = (Stage) this.username.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(stageName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
    }

}
