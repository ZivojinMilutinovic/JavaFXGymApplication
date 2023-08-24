package src.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GymCardTest {

    private GymCard gymCard;
    @Before
    public void setUp() throws Exception {
        gymCard=new GymCard(2,2500.212,11,1);
    }

    @Test
    public void getUser_id() {
        int id=gymCard.getUser_id();
        Assert.assertEquals(1,id);
    }


    @Test
    public void setUser_id() {
        gymCard.setUser_id(11);
        Assert.assertEquals(11,gymCard.getUser_id());
    }

    @Test
    public void getGym_card_id() {
        int id=gymCard.getGym_card_id();
        Assert.assertEquals(2,id);
    }



    @Test
    public void setGym_card_id() {
        gymCard.setGym_card_id(1);
        Assert.assertEquals(1,gymCard.getGym_card_id());
    }

    @Test
    public void getPrice() {
        double price=gymCard.getPrice();
        Assert.assertEquals(2500.212,price,0.0);
    }


    @Test
    public void setPrice() {
        gymCard.setPrice(1221.4342);
        Assert.assertEquals(1221.4342,gymCard.getPrice(),0.0);

    }

    @Test
    public void getDiscount() {
        int discount= gymCard.getDiscount();
        Assert.assertEquals(11,discount);
    }

    @Test
    public void setDiscount() {
        gymCard.setDiscount(50);
        Assert.assertEquals(50,gymCard.getDiscount());
    }
}