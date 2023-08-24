package src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.Constants;
import src.dao.GymEmployeeDAO;

import java.io.IOException;
import java.sql.SQLException;

public class CreateUserController {
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    TextField phoneNumber;

    public void createUser(){
        Stage primaryStage = (Stage) this.firstName.getScene().getWindow();
        try {
            GymEmployeeDAO.insertEmp(firstName.getText(),lastName.getText(),email.getText(),password.getText(),phoneNumber.getText());
            System.out.println("User saved in database");
            Parent root = FXMLLoader.load(getClass().getResource("../views/login-user.fxml"));
            primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void goToLoginPage(){
        Stage primaryStage = (Stage) this.firstName.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/login-user.fxml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
    }
}
