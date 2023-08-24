package src.model;

import javafx.beans.property.*;

import java.sql.Date;

public class GymMember {
    private IntegerProperty member_id;
    private StringProperty first_name;
    private StringProperty last_name;
    private StringProperty email;
    private StringProperty phone_number;


    public GymMember(int member_id, String first_name, String last_name, String email, String phone_number) {
        this.member_id =new SimpleIntegerProperty(member_id) ;
        this.first_name =new SimpleStringProperty(first_name);
        this.last_name =new SimpleStringProperty(last_name);
        this.email =new SimpleStringProperty(email);
        this.phone_number = new SimpleStringProperty(phone_number);
    }

    public GymMember(){
            member_id=new SimpleIntegerProperty();
            first_name=new SimpleStringProperty();
            last_name=new SimpleStringProperty();
            email=new SimpleStringProperty();
            phone_number=new SimpleStringProperty();
    }

    public int getMember_id() {
        return member_id.get();
    }

    public IntegerProperty member_idProperty() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id.set(member_id);
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



    @Override
    public String toString() {
        return "GymMember{" +
                "employee_id=" + member_id +
                ", first_name=" + first_name +
                ", last_name=" + last_name +
                ", email=" + email +
                ", phone_number=" + phone_number +
                '}';
    }
}
