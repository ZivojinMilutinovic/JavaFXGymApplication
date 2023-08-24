package src.model;

import javafx.beans.property.*;

import java.sql.Date;
import java.sql.Timestamp;

public class GymCard {
    private IntegerProperty gym_card_id;
    private SimpleObjectProperty<Timestamp> insert_date;
    private SimpleObjectProperty<Timestamp> termination_date;
    private DoubleProperty price;
    private IntegerProperty discount;
    private IntegerProperty user_id;

    public GymCard(int gym_card_id,  double price, int discount, int user_id) {
        this.gym_card_id =new SimpleIntegerProperty(gym_card_id) ;
        this.insert_date = new SimpleObjectProperty<>();
        this.termination_date = new SimpleObjectProperty<>();
        this.price = new SimpleDoubleProperty(price) ;
        this.discount = new SimpleIntegerProperty(discount);
        this.user_id = new SimpleIntegerProperty(user_id);
    }

    public GymCard(){
        gym_card_id=new SimpleIntegerProperty();
        insert_date=new SimpleObjectProperty<>();
        termination_date=new SimpleObjectProperty<>();
        price=new SimpleDoubleProperty();
        discount=new SimpleIntegerProperty();
        user_id=new SimpleIntegerProperty();
    }
    public int getUser_id() {
        return user_id.get();
    }

    public IntegerProperty user_idProperty() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id.set(user_id);
    }

    @Override
    public String toString() {
        return "GymCard{" +
                "gym_card_id=" + gym_card_id +
                ", insert_date=" + insert_date +
                ", termination_date=" + termination_date +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    public int getGym_card_id() {
        return gym_card_id.get();
    }

    public IntegerProperty gym_card_idProperty() {
        return gym_card_id;
    }

    public void setGym_card_id(int gym_card_id) {
        this.gym_card_id.set(gym_card_id);
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

    public Timestamp getTermination_date() {
        return termination_date.get();
    }

    public SimpleObjectProperty<Timestamp> termination_dateProperty() {
        return termination_date;
    }

    public void setTermination_date(Timestamp termination_date) {
        this.termination_date.set(termination_date);
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

    public int getDiscount() {
        return discount.get();
    }

    public IntegerProperty discountProperty() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount.set(discount);
    }
}
