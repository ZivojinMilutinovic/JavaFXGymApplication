package src.model;

import javafx.beans.property.*;

import java.sql.Date;

public class GymEquipment {
    private IntegerProperty equipment_id;
    private StringProperty name;
    private StringProperty model;
    private StringProperty info;
    private DoubleProperty price;

    public GymEquipment(int equipment_id, String name, String model, String info, double price) {
        this.equipment_id = new SimpleIntegerProperty(equipment_id) ;
        this.name =new SimpleStringProperty(name);
        this.model =new SimpleStringProperty(model) ;
        this.info = new SimpleStringProperty(info);
        this.price =new SimpleDoubleProperty(price) ;
    }

    public GymEquipment(){
        equipment_id=new SimpleIntegerProperty();
        name=new SimpleStringProperty();
        model=new SimpleStringProperty();
        info=new SimpleStringProperty();
        price=new SimpleDoubleProperty();
    }
    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getEquipment_id() {
        return equipment_id.get();
    }

    public IntegerProperty equipment_idProperty() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id.set(equipment_id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getInfo() {
        return info.get();
    }

    public StringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }





    @Override
    public String toString() {
        return "GymEquipment{" +
                "equipment_id=" + equipment_id +
                ", name=" + name +
                ", model=" + model +
                ", info=" + info +
                ", phone_number=" + price +
                '}';
    }
}
