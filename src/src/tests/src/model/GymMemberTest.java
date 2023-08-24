package src.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GymMemberTest {

    private GymMember gymMember;
    @Before
    public void setUp() throws Exception {
        gymMember=new GymMember(1,"Nikola","Petrovic","nikola@gmail.com","066 5431132");
    }
    @Test
    public void getMember_id() {
        int member_id=gymMember.getMember_id();
        Assert.assertEquals(1,member_id);
    }

    @Test
    public void setMember_id() {
        gymMember.setMember_id(2);
        Assert.assertEquals(2,gymMember.getMember_id());
    }

    @Test
    public void getFirst_name() {
        String firstName=gymMember.getFirst_name();
        Assert.assertEquals("Nikola",firstName);
    }



    @Test
    public void setFirst_name() {
        gymMember.setFirst_name("Milan");
        Assert.assertEquals("Milan",gymMember.getFirst_name());
    }

    @Test
    public void getLast_name() {
        String lastName=gymMember.getLast_name();
        Assert.assertEquals("Petrovic",lastName);
    }

    @Test
    public void setLast_name() {
        gymMember.setLast_name("Kitanovic");
        Assert.assertEquals("Kitanovic",gymMember.getLast_name());
    }

    @Test
    public void getEmail() {
        String email=gymMember.getEmail();
        Assert.assertEquals("nikola@gmail.com",email);
    }


    @Test
    public void setEmail() {
        gymMember.setEmail("nik@gmail.com");
        Assert.assertEquals("nik@gmail.com",gymMember.getEmail());
    }

    @Test
    public void getPhone_number() {
        String phoneNumber=gymMember.getPhone_number();
        Assert.assertEquals("066 5431132",phoneNumber);
    }


    @Test
    public void setPhone_number() {
        gymMember.setPhone_number("066 362452");
        Assert.assertEquals("066 362452",gymMember.getPhone_number());
    }
}