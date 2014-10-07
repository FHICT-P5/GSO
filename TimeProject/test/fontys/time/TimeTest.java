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
 * @author Bart
 */

 /**
 * creation of a Time-object with year y, month m, day d, hours h and
 * minutes m; if the combination of y-m-d refers to a non-existing date 
 * a correct value of this Time-object will be not guaranteed 
 * @param y
 * @param m 1≤m≤12
 * @param d 1≤d≤31
 * @param h 0≤h≤23
 * @param m 0≤m≤59
 * public Time(int y, int m, int d, int h, int min)
 */

public class TimeTest
{
    
    public TimeTest()
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
    public void testMaand1() {
        // @param m mag niet kleiner zijn dan 1
        Time maand1 = new Time(2014, 0, 1, 0, 0);
        Assert.fail("Maand kan niet minder dan 1 zijn");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testMaand2() {
        // @param m mag niet groter zijn dan 12
        Time maand2 = new Time(2014, 13, 1, 0, 0);
        Assert.fail("Maand kan niet meer dan 12 zijn"); 
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testDag1() {
        // @param d mag niet kleiner zijn dan 1
        Time dag1 = new Time(2014, 1, 0, 0, 0);
        Assert.fail("Dag kan niet minder dan 1 zijn");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testDag2() {
        // @param d mag niet groter zijn dan 31
        Time dag2 = new Time(2014, 1, 32, 0, 0);
        Assert.fail("Dag kan niet meer dan 31 zijn");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testUur1() {
        // @param h mag niet kleiner zijn dan 0
        Time uur1 = new Time(2014, 1, 1, -1, 0);
        Assert.fail("Uur kan niet minder dan 0 zijn");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testUur2() {
        // @param h mag niet groter zijn dan 23
        Time uur2 = new Time(2014, 1, 1, 24, 0);
        Assert.fail("Uur kan niet meer dan 24 zijn");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testMinuut1() {
        // @param min mag niet kleiner zijn dan 0
        Time minuut1 = new Time(2014, 1, 1, 0, -1);
        Assert.fail("Minuut kan niet minder dan 0 zijn");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testMinuut2() {
        // @param min mag niet groter zijn dan 59
        Time minuut2 = new Time(2014, 1, 1, 0, 60);
        Assert.fail("Minuut kan niet meer dan 59 zijn");
    }
    
    @Test
    public void testGetDayInWeek()
    {
        //@return the concerning day in the week of this time
        Time woensdag = new Time(2014, 10, 8, 0, 30);
        String expResult = "WED";
        String result = woensdag.getDayInWeek().toString();
        Assert.assertEquals("Dag incorect", expResult, result);
    }
    
    @Test
    public void testPlus1()
    {
        //@param minutes (a negative value is allowed)
        Time testTime = new Time(2014, 10, 7, 6, 25);
        Time expResult = new Time(2014, 10, 7, 7, 35);
        Time result = testTime.plus(70);
        Assert.assertEquals("Tijdverplaatsing incorrect", expResult, result);
    }
    
    @Test
    public void testPlus2()
    {
        //@param minutes (a negative value is allowed)
        Time testTime = new Time(2014, 10, 7, 7, 35);
        Time expResult = new Time(2014, 10, 7, 6, 25);
        Time result = testTime.plus(-70);
        Assert.assertEquals("Tijdverplaatsing incorrect", expResult, result);
    }
    
    @Test
    public void testDifference1()
    {
        Time testTime1 = new Time(2014, 10, 7, 4, 30);
        Time testTime2 = new Time(2014, 10, 7, 8, 45);
        int expResult = 255;
        int result = testTime1.difference(testTime2);
        Assert.assertEquals("Tijdverschil incorrect", expResult, result);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testDifference2()
    {
        Time testTime1 = new Time(2014, 10, 7, 4, 30);
        Time testTime2 = null;
        int expResult = 255;
        int result = testTime1.difference(testTime2);
        Assert.fail("Time parameter mag niet null zijn");
    }
}
