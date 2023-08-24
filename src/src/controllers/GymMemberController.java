package src.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.Constants;
import src.DBUtil;
import src.dao.GymEmployeeDAO;
import src.dao.GymMemeberDAO;
import src.helpers.AlertBox;
import src.helpers.ConfirmBox;
import src.model.GymEmployee;
import src.model.GymMember;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import static javafx.scene.control.SelectionMode.SINGLE;

public class GymMemberController implements Initializable {
    @FXML
    private TableView table;
    @FXML
    private TableColumn<GymMember,String> firstName_col;
    @FXML
    private TableColumn<GymMember,String> lastName_col;
    @FXML
    private TableColumn<GymMember,String> email_col;
    @FXML
    private TableColumn<GymMember,Integer> id_col;
    @FXML
    private TableColumn<GymMember,String> phoneNumber_col;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField email;
    @FXML
    TextField phoneNumber;

    private ObservableList<GymMember> members;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.getSelectionModel().setSelectionMode(SINGLE);
        firstName_col.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumber_col.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
       id_col.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        table.getSelectionModel().selectedIndexProperty()
                .addListener((v,oldVal,newVal)->{
                   GymMember member=(GymMember) table.getSelectionModel().getSelectedItem();
                    if(member!=null){

                        firstName.setText(member.getFirst_name());
                        lastName.setText(member.getLast_name());
                        email.setText(member.getEmail());
                        phoneNumber.setText(member.getPhone_number());
                    }

                });
        try {
            members= GymMemeberDAO.searchMembers();
            table.getItems().addAll(members);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void onCreate(){
        GymMember member=new GymMember();
        member.setFirst_name(firstName.getText());
        member.setLast_name(lastName.getText());
        member.setEmail(email.getText());
        member.setPhone_number(phoneNumber.getText());
        try {
           int key=GymMemeberDAO.insertMember(member.getFirst_name(),member.getLast_name(),member.getEmail(),member.getPhone_number());
            table.getItems().clear();
            members=GymMemeberDAO.searchMembers();
            table.getItems().addAll(members);
            firstName.clear();
            lastName.clear();
            email.clear();
            phoneNumber.clear();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void onDelete(){
        GymMember member=(GymMember) table.getSelectionModel().getSelectedItem();
        try {
            if(member!=null){
                GymMemeberDAO.deleteMemberWithId(String.valueOf(member.getMember_id()));
                members.remove(member);
                table.getItems().clear();
                table.getItems().addAll(members);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void onUpdate(){
        GymMember member=(GymMember) table.getSelectionModel().getSelectedItem();
        try {
            GymMemeberDAO.updateMember(String.valueOf(member.getMember_id()),firstName.getText(),lastName.getText(),email.getText(),phoneNumber.getText());
           members= GymMemeberDAO.searchMembers();
            table.getItems().clear();
            table.getItems().addAll(members);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void manageGymMembership(){
        GymMember member=(GymMember) table.getSelectionModel().getSelectedItem();
        if(member!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/member-card.fxml"));
            try {
                Stage primaryStage = (Stage) this.table.getScene().getWindow();

                Parent root = loader.load();
                MemberCardController memberCardController=loader.getController();
                memberCardController.setGymMember(member);
                Stage stage = new Stage();
                stage.setScene(new Scene(root,Constants.WIDTH,Constants.HEIGHT));
                stage.setTitle("Gym Management System");
                stage.show();
                primaryStage.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }else{
           AlertBox.display("Member is not selected","You must select a member");
        }
    }
    public void goToGymStatistics(){
        setStage("../views/gym-statistics.fxml");
    }
   public void goToManageUsers(){
        setStage("../views/logged-user.fxml");
    }
    public void goToManageEquipment(){
        setStage("../views/equipment-index.fxml");
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
