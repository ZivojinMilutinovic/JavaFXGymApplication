package src.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GymEmployeeTest {

    private GymEmployee gymEmployee;
    @Before
    public void setUp() throws Exception {
        gymEmployee=new GymEmployee(23,"Dalibor","Bojovic","dalibor@gmail.com","0642315234","diamonds");
    }

    @Test
    public void getEmployee_id() {
        int id=gymEmployee.getEmployee_id();
        Assert.assertEquals(23,id);
    }


    @Test
    public void setEmployee_id() {
        gymEmployee.setEmployee_id(2);
        Assert.assertEquals(2,gymEmployee.getEmployee_id());
    }

    @Test
    public void getFirst_name() {
        String firstName= gymEmployee.getFirst_name();
        Assert.assertEquals("Dalibor",firstName);
    }



    @Test
    public void setFirst_name() {
        gymEmployee.setFirst_name("John");
        Assert.assertEquals("John",gymEmployee.getFirst_name());
    }

    @Test
    public void getLast_name() {
        String lastName= gymEmployee.getLast_name();
        Assert.assertEquals("Bojovic",lastName);
    }


    @Test
    public void setLast_name() {
        gymEmployee.setLast_name("Lemon");
        Assert.assertEquals("Lemon",gymEmployee.getLast_name());
    }

    @Test
    public void getEmail() {
        String email= gymEmployee.getEmail();
        Assert.assertEquals("dalibor@gmail.com",email);
    }



    @Test
    public void setEmail() {
        gymEmployee.setEmail("dal@gmail.com");
        Assert.assertEquals("dal@gmail.com",gymEmployee.getEmail());
    }

    @Test
    public void getPhone_number() {
        String phoneNumber= gymEmployee.getPhone_number();
        Assert.assertEquals("0642315234",phoneNumber);
    }



    @Test
    public void setPhone_number() {
        gymEmployee.setPhone_number("0642975765");
        Assert.assertEquals("0642975765",gymEmployee.getPhone_number());
    }



    @Test
    public void getPassword() {
        String password= gymEmployee.getPassword();
        Assert.assertEquals("diamonds",password);
    }



    @Test
    public void setPassword() {
        gymEmployee.setPassword("money");
        Assert.assertEquals("money",gymEmployee.getPassword());
    }
}