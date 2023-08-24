package src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.Constants;
import src.dao.GymEmployeeDAO;
import src.model.GymEmployee;

import java.io.IOException;
import java.sql.SQLException;


public class LoginUserController {

    @FXML
    TextField email;

    @FXML
    TextField password;

    public void loginUser(){
        Stage primaryStage = (Stage) this.email.getScene().getWindow();
        try {
           GymEmployee emp= GymEmployeeDAO.searchEmployee(email.getText(),password.getText());
            if(emp!=null){
                Parent root = FXMLLoader.load(getClass().getResource("../views/logged-user.fxml"));
                primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
                System.out.println("Employee found:"+emp);
                primaryStage.setUserData(emp);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void goToRegisterPage(){
        Stage primaryStage = (Stage) this.email.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/create-user.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
    }

}
