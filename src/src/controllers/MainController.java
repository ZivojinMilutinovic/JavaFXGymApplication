package src.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import src.Constants;
import src.DBUtil;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label label;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loginButtonPressed(){
        Stage primaryStage = (Stage) this.label.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/login-user.fxml"));
            primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void registerButtonPressed(){
        Stage primaryStage = (Stage) this.label.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/create-user.fxml"));
            primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
