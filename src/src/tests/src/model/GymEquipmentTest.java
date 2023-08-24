package src.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GymEquipmentTest {

    private GymEquipment gymEquipment;
    @Before
    public void setUp() throws Exception {
        gymEquipment=new GymEquipment(1,"Benc","ZX20","Benc klupa sa dodacima",23.4221);
    }
    @Test
    public void getPrice() {
        double price= gymEquipment.getPrice();
        Assert.assertEquals(23.4221,price,0.0);
    }

    @Test
    public void setPrice() {
        gymEquipment.setPrice(68.2312);
        Assert.assertEquals(68.2312,gymEquipment.getPrice(),0.0);
    }

    @Test
    public void getEquipment_id() {
        int id= gymEquipment.getEquipment_id();
        Assert.assertEquals(1,id);
    }

    @Test
    public void setEquipment_id() {
        gymEquipment.setEquipment_id(4);
        Assert.assertEquals(4,gymEquipment.getEquipment_id());
    }

    @Test
    public void getName() {
        String name=gymEquipment.getName();
        Assert.assertEquals(name,gymEquipment.getName());
    }


    @Test
    public void setName() {
        gymEquipment.setName("Stalak benc");
        Assert.assertEquals("Stalak benc",gymEquipment.getName());
    }

    @Test
    public void getModel() {
        String model=gymEquipment.getModel();
        Assert.assertEquals("ZX20",model);
    }

    @Test
    public void setModel() {
        gymEquipment.setModel("New bench");
        Assert.assertEquals("New bench",gymEquipment.getModel());
    }

    @Test
    public void getInfo() {
        String info=gymEquipment.getInfo();
        Assert.assertEquals("Benc klupa sa dodacima",info);
    }

    @Test
    public void setInfo() {
        gymEquipment.setInfo("Benc klupa bez dodataka");
        Assert.assertEquals("Benc klupa bez dodataka",gymEquipment.getInfo());
    }
}