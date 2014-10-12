/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Julius op den Brouw (B2)
 */
public class AppointmentTest {
    
    Appointment app;
    Period p;
    String subject;
    
    public AppointmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new Period(new Time(2014, 10, 11, 6, 0), new Time(2014, 10, 11, 7,0));
        subject = "Test subject";
        app = new Appointment(subject, p);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test (expected=IllegalArgumentException.class)
    public void testAppointment1()
    {
        Appointment a = new Appointment(null, p);
        Assert.fail("Subject is null");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testAppointment2()
    {
        Appointment a = new Appointment(subject, null);
        Assert.fail("Period is null");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testAddContact1()
    {
        Contact c = null;
        app.addContact(c);
        Assert.fail("Contact c is null");
    }
    
    @Test
    public void testAddContact2()
    {
        Contact c = new Contact("testContact");
        
        boolean expResult = true;
        boolean result = app.addContact(c);
        Assert.assertEquals("Add contact failed", expResult, result);
    }
    
    @Test
    public void testAddContact3()
    {
        Contact c = new Contact("testContact");
        
        app.addContact(c);
        
        boolean expResult = false;
        boolean result = app.addContact(c);
        Assert.assertEquals("Contact added twice", expResult, result);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void tesRemoveContact()
    {
        Contact c = null;
        app.removeContact(c);
        Assert.fail("Contact c is null");
    }
    
    @Test
    public void testRemoveContact2()
    {
        Contact c = new Contact("testContact");
        
        app.addContact(c);
        
        boolean expResult = true;
        boolean result = app.removeContact(c);
        Assert.assertEquals("Remove contact failed", expResult, result);
    }
    
    @Test
    public void testRemoveContact3()
    {
        Contact c = new Contact("testContact");
        
        boolean expResult = false;
        boolean result = app.removeContact(c);
        Assert.assertEquals("Contact c does not exist in appointment app", expResult, result);
    }
}
