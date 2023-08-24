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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.Constants;
import src.DBUtil;
import src.dao.GymEmployeeDAO;
import src.helpers.AlertBox;
import src.model.GymEmployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;


import static javafx.scene.control.SelectionMode.SINGLE;

public class LoggedUserController  implements Initializable {
    private GymEmployee employee;
    private ObservableList<GymEmployee> employees;

    @FXML
    private TableView table;
    @FXML
    private TableColumn<GymEmployee,String> first_name;
    @FXML
    private TableColumn<GymEmployee,String> last_name;
    @FXML
    private TableColumn<GymEmployee,String> email;
    @FXML
    private TableColumn<GymEmployee, Timestamp> insert_date;
    @FXML
    private TableColumn<GymEmployee,String> phone_number;
    @FXML
    TableColumn<GymEmployee,Integer> employee_id;

    private URL url;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        table.getSelectionModel().setSelectionMode(SINGLE);
        try {
            url=new URL("https://randomuser.me/api/");
            employees=GymEmployeeDAO.searchEmployees();
            employee_id.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
           first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
            last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            phone_number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            insert_date.setCellValueFactory(new PropertyValueFactory<>("insert_date"));
            table.getItems().addAll(employees);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void deleteButtonPressed(){
            GymEmployee emp=(GymEmployee) table.getSelectionModel().getSelectedItem();
            if(emp==null){
                AlertBox.display("Error","No employee was selected!");
                return;
            }
        try {
            GymEmployeeDAO.deleteEmpWithId(String.valueOf(emp.getEmployee_id()));
           employees.remove(emp);
           table.getItems().clear();
           table.getItems().addAll(employees);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addEmployeeFromAnotherGym(){
        try {
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            String line="";
            InputStreamReader inputStreamReader=new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            JSONObject jsonObject=new JSONObject(bufferedReader.readLine());
            System.out.println(jsonObject.toString());
            jsonObject=jsonObject.getJSONArray("results").getJSONObject(0);
            System.out.println(jsonObject.toString());

            String firstName=jsonObject.getJSONObject("name").getString("first");
            String lastName=jsonObject.getJSONObject("name").getString("last");
            String email=jsonObject.getString("email");
            String password=jsonObject.getJSONObject("login").getString("password");
            String phone_number=jsonObject.getString("phone");
            GymEmployeeDAO.insertEmp(firstName,lastName,email,password,phone_number);
            employees=GymEmployeeDAO.searchEmployees();
            table.getItems().clear();
            table.getItems().addAll(employees);

        } catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    public void goToGymStatistics(){
        setStage("../views/gym-statistics.fxml");
    }
    public void goToManageMembers(){
        setStage("../views/member-index.fxml");
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
