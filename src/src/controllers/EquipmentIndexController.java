package src.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.Constants;
import src.dao.GymEquipmentDAO;
import src.model.GymEquipment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.scene.control.SelectionMode.SINGLE;

public class EquipmentIndexController implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField model;
    @FXML
    TextField info;
    @FXML
    TextField price;

    @FXML
    private TableView table;
    @FXML
    private TableColumn<GymEquipment,String> name_col;
    @FXML
    private TableColumn<GymEquipment,String> model_col;
    @FXML
    private TableColumn<GymEquipment,String> info_col;
    @FXML
    private TableColumn<GymEquipment,Integer> id_col;
    @FXML
    private TableColumn<GymEquipment,Double> price_col;



    private ObservableList<GymEquipment> equipment;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        table.getSelectionModel().setSelectionMode(SINGLE);
        table.getSelectionModel().selectedIndexProperty()
                .addListener((v,oldVal,newVal)->{
                    GymEquipment eq=(GymEquipment) table.getSelectionModel().getSelectedItem();
                    if(eq!=null){

                        name.setText(eq.getName());
                        info.setText(eq.getInfo());
                        model.setText(eq.getModel());
                        price.setText(String.valueOf(eq.getPrice()));
                    }

                });
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        model_col.setCellValueFactory(new PropertyValueFactory<>("model"));
        info_col.setCellValueFactory(new PropertyValueFactory<>("info"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("equipment_id"));
        try {
            equipment= GymEquipmentDAO.searchEquipment();
            table.getItems().addAll(equipment);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public void goToGymStatistics(){
        setStage("../views/gym-statistics.fxml");
    }
    public void onCreate(){
            GymEquipment eq=new GymEquipment();
            eq.setInfo(info.getText());
            eq.setPrice(Double.parseDouble(price.getText()));
            eq.setModel(model.getText());
            eq.setName(name.getText());
        try {
            int key=GymEquipmentDAO.insertEq(eq.getName(),eq.getModel(),eq.getInfo(),eq.getPrice());
           table.getItems().clear();
            equipment= GymEquipmentDAO.searchEquipment();
            table.getItems().addAll(equipment);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void onDelete(){
        GymEquipment eq=(GymEquipment) table.getSelectionModel().getSelectedItem();
        try {
            if(eq!=null){
                GymEquipmentDAO.deleteEqWithId(String.valueOf(eq.getEquipment_id()));
                equipment.remove(eq);
                table.getItems().clear();
                table.getItems().addAll(equipment);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void onUpdate(){
        GymEquipment eq=(GymEquipment) table.getSelectionModel().getSelectedItem();
        try {
            GymEquipmentDAO.updateEquipment(String.valueOf(eq.getEquipment_id()),name.getText(),model.getText(),info.getText(),Double.parseDouble(price.getText()));
            equipment= GymEquipmentDAO.searchEquipment();
            table.getItems().clear();
            table.getItems().addAll(equipment);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void goToManageUsers(){
        setStage("../views/logged-user.fxml");
    }
    public void goToManageMembers(){
        setStage("../views/member-index.fxml");
    }

    public void setStage(String stageName){
        Stage primaryStage = (Stage) this.table.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(stageName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        primaryStage.setScene(new Scene( root, Constants.WIDTH, Constants.HEIGHT));
    }


}
