/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fontys.time;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Bart
 */
public class ContactTest
{
    private Contact a;
    
    private Time a1;
    private Time a2;
    private Time b1;
    private Time b2;
    private Time c1;
    private Time c2;
    
    private Period A;
    private Period B;
    private Period C;
    
    private Appointment test1;
    private Appointment test2;
    private Appointment test3;
    
    public ContactTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        a = new Contact("Piet");
        
        a1 = new Time(2014, 1, 1, 12, 0);
        a2 = new Time(2014, 1, 1, 12, 5);
        b1 = new Time(2014, 1, 1, 12, 1);
        b2 = new Time(2014, 1, 1, 12, 7);
        c1 = new Time(2014, 1, 1, 12, 8);
        c2 = new Time(2014, 1, 1, 12, 9);
        
        A = new Period(a1, a2);
        B = new Period(b1, b2);
        C = new Period(c1, c2);
        
        test1 = new Appointment("Test1", A);
        test2 = new Appointment("Test2", B);
        test3 = new Appointment("Test3", C);
    }
    
    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test (expected=IllegalArgumentException.class)
    public void testContact()
    {
        Contact test = new Contact(null);
        Assert.fail("Naam van contact kan niet null zijn");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testAddAppointment1()
    {
        a.addAppointment(null);
        Assert.fail("Appointment mag niet null zijn");
    }
    
    public void testAddAppointment2()
    {
        boolean expResult = false;
        boolean result = a.addAppointment(test2);
        Assert.assertEquals("Appointment mag niet overlappen met een andere Appointment", expResult, result);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testRemoveAppointment1()
    {
        a.removeAppointment(null);
        Assert.fail("Appointment mag niet null zijn");
    }
    
    @Test
    public void testRemoveAppointment2()
    {
        boolean result = a.removeAppointment(test3);
        boolean expResult = false;
        Assert.assertEquals("Appointment test3 bestaat niet voor dit contact",expResult, result);
    }
}
