package src.model;

import javafx.beans.property.*;

import java.sql.Date;
import java.sql.Timestamp;

public class GymEmployee {
    private IntegerProperty employee_id;
    private StringProperty first_name;
    private StringProperty last_name;
    private StringProperty email;
    private StringProperty phone_number;
    private SimpleObjectProperty<Timestamp> insert_date;
    private StringProperty password;

    public GymEmployee(int employee_id, String first_name, String last_name, String email, String phone_number, String password) {
        this.employee_id =new SimpleIntegerProperty(employee_id);
        this.first_name =new SimpleStringProperty(first_name) ;
        this.last_name =new SimpleStringProperty(last_name) ;
        this.email =new SimpleStringProperty(email) ;
        this.phone_number =new SimpleStringProperty(phone_number) ;
        this.insert_date = new SimpleObjectProperty<>();
        this.password =new SimpleStringProperty(password) ;
    }

    public GymEmployee() {
        this.employee_id = new SimpleIntegerProperty();
        this.first_name = new SimpleStringProperty();
        this.last_name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone_number = new SimpleStringProperty();
        this.insert_date = new SimpleObjectProperty<>();
        this.password = new SimpleStringProperty();
    }

    @Override
    public String toString() {
        return "GymEmployee{" +
                "employee_id=" + employee_id +
                ", first_name=" + first_name +
                ", last_name=" + last_name +
                ", email=" + email +
                ", phone_number=" + phone_number +
                ", insert_date=" + insert_date +
                ", password=" + password +
                '}';
    }

    public int getEmployee_id() {
        return employee_id.get();
    }

    public IntegerProperty employee_idProperty() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id.set(employee_id);
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public StringProperty first_nameProperty() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public String getLast_name() {
        return last_name.get();
    }

    public StringProperty last_nameProperty() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public StringProperty phone_numberProperty() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.set(phone_number);
    }

    public Timestamp getInsert_date() {
        return insert_date.get();
    }

    public SimpleObjectProperty<Timestamp> insert_dateProperty() {
        return insert_date;
    }

    public void setInsert_date(Timestamp insert_date) {
        this.insert_date.set(insert_date);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
