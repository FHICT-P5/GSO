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
public class Period2Test {
    
    private Time a1;
    private Time a2;
    private Time b1;
    private Time b2;
    private Time c1;
    private Time c2;
    private Time d1;
    private Time d2;
    private Time e1;
    private Time e2;
    private Time f1;
    private Time f2;
    
    private Time p1;
    private Time p2;
    
    private Period2 A;
    private Period2 B;
    private Period2 C;
    private Period2 D;
    private Period2 E;
    private Period2 F;
    
    private Period2 P;
    
    
    public Period2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        a1 = new Time(2014, 1, 1, 12, 0);
        a2 = new Time(2014, 1, 1, 12, 5);
        b1 = new Time(2014, 1, 1, 12, 1);
        b2 = new Time(2014, 1, 1, 12, 7);
        c1 = new Time(2014, 1, 1, 12, 8);
        c2 = new Time(2014, 1, 1, 12, 9);
        d1 = new Time(2014, 1, 1, 12, 4);
        d2 = new Time(2014, 1, 1, 12, 11);
        e1 = new Time(2014, 1, 1, 12, 10);
        e2 = new Time(2014, 1, 1, 12, 14);
        f1 = new Time(2014, 1, 1, 12, 11);
        f2 = new Time(2014, 1, 1, 12, 25);
    
        p1 = new Time(2014, 1, 1, 12, 5);
        p2 = new Time(2014, 1, 1, 12, 10);
        
        A = new Period2(a1, a2);
        B = new Period2(b1, b2);
        C = new Period2(c1, c2);
        D = new Period2(d1, d2);
        E = new Period2(e1, e2);
        F = new Period2(f1, f2);
        
        P = new Period2(p1, p2);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test (expected=IllegalArgumentException.class)
    public void testPeriod21()
    {
        /**
        * creation of a period with begin time bt and end time et
        * @param bt begin time bt must be earlier than end time et
        * @param et 
        */
        Time bt = a2;
        Time et = a1;
        Period2 A = new Period2(bt, et);
        Assert.fail("begintijd moet eerder zijn dan eindtijd");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testPeriod22()
    {
        /**
        * creation of a period with begin time bt and end time et
        * @param bt begin time bt must be earlier than end time et
        * @param et 
        */
        Time bt = a1;
        Time et = a1;
        Period2 A = new Period2(bt, et);
        Assert.fail("begintijd moet eerder zijn dan eindtijd");
    }
    
    @Test
    public void testLength()
    {
        /**
        * 
        * @return the length of this period expressed in minutes (always positive)
        */
        int length = a2.getMinutes() - a1.getMinutes();
        Assert.assertEquals("Onjuiste lengte", length, A.length());
    }
    
    @Test
    public void testSetBeginTime1()
    {
        /**
        * beginTime will be the new begin time of this period
        * @param beginTime must be earlier than the current end time
        * of this period
        */
        A.setBeginTime(b1);
        Assert.assertTrue("Onjuiste begintijd", A.getBeginTime().compareTo(b1) == 0);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testSetBeginTime2()
    {
        /**
        * beginTime will be the new begin time of this period
        * @param beginTime must be earlier than the current end time
        * of this period
        */
        A.setBeginTime(a2);
        Assert.fail("begintijd mag niet gelijk zijn aan de eindtijd");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testSetBeginTime3()
    {
        /**
        * beginTime will be the new begin time of this period
        * @param beginTime must be earlier than the current end time
        * of this period
        */
        A.setBeginTime(b2);
        Assert.fail("begintijd mag niet later zijn dan de eindtijd");
    }
    
    @Test
    public void testSetEndTime1()
    {
        /**
        * endTime will be the new end time of this period
        * @param beginTime (FOUT: dit moet zijn endTime) must be later than the current begin time
        * of this period
        */
        A.setEndTime(b2);
        Assert.assertTrue("onjuiste eindtijd", A.getEndTime().compareTo(b2) == 0);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testSetEndTime2()
    {
        /**
        * endTime will be the new end time of this period
        * @param beginTime (FOUT: dit moet zijn endTime) must be later than the current begin time
        * of this period
        */
        A.setEndTime(a1);
        Assert.fail("eindtijd mag niet gelijk zijn aan de begintijd");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testSetEndTime3()
    {
        /**
        * endTime will be the new end time of this period
        * @param beginTime (FOUT: dit moet zijn endTime) must be later than the current begin time
        * of this period
        */
        B.setEndTime(a1);
        Assert.fail("eindtijd mag niet eerder zijn dan de begintijd");
    }
    
    @Test
    public void testMove()
    {
        /**
        * the begin and end time of this period will be moved up both with [minutes]
        * minutes
        * @param minutes (a negative value is allowed)
        */
        A.move(3);
        
        Period2 movedA = new Period2(new Time(2014, 1, 1, 12, 3), new Time(2014, 1, 1, 12, 8));
        
        Assert.assertTrue("onjuiste verplaating", equalPeriod2(movedA, A));
    }
    
    @Test
    public void testChangeLengthWith1()
    {
        /**
        * the end time of this period will be moved up with [minutes] minutes
        * @param minutes  minutes + length of this period must be positive  
        */
        int preChangeLength = A.length();
        A.changeLengthWith(3);
        int postChangeLength = A.length();
        Assert.assertEquals("onjuiste lengte veranderig", preChangeLength + 3, postChangeLength);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testChangeLengthWith2()
    {
        /**
        * the end time of this period will be moved up with [minutes] minutes
        * @param minutes  minutes + length of this period must be positive  
        */
        A.changeLengthWith(-6);
        Assert.fail("de nieuwe lengte moet positief zijn");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testIsPartOf1()
    {
        /**
        * 
        * @param period
        * @return true if all moments within this period are included within [period], 
        * otherwise false
        */
        A.isPartOf(null);
        Assert.fail("period mag niet null zijn");
    }
    
    @Test
    public void testIsPartOf2()
    {
        /**
        * 
        * @param period
        * @return true if all moments within this period are included within [period], 
        * otherwise false
        */
        boolean expResult = false;
        boolean result = A.isPartOf(B);
        Assert.assertEquals("Period2 A zit niet volledig in period B", expResult, result);
    }
    
    @Test
    public void testIsPartOf3()
    {
        /**
        * 
        * @param period
        * @return true if all moments within this period are included within [period], 
        * otherwise false
        */
        boolean expResult = false;
        boolean result = B.isPartOf(A);
        Assert.assertEquals("Period2 B zit niet volledig in period A", expResult, result);
    }
    
    @Test
    public void testIsPartOf4()
    {
        /**
        * 
        * @param period
        * @return true if all moments within this period are included within [period], 
        * otherwise false
        */
        boolean expResult = true;
        boolean result = C.isPartOf(P);
        Assert.assertEquals("Period2 C is een deel van P", expResult, result);
    }
    
    @Test
    public void testUnionWith()
    {
        /**
        * 
        * @param period
        * @return if this period and [period] are consecutive or possess a
        * common intersection, then the smallest period p will be returned, 
        * for which this period and [period] are part of p, 
        * otherwise null will be returned 
        */
        Assert.assertNull("Geen union met zichzelf", P.unionWith(P));
                       
        Assert.assertTrue("Onjuiste periode", equalPeriod2(new Period2(a1, p2), P.unionWith(A)));
        Assert.assertTrue("Onjuiste periode", equalPeriod2(new Period2(b1, p2), P.unionWith(B)));
        Assert.assertTrue("Onjuiste periode", equalPeriod2(P, P.unionWith(C)));
        Assert.assertTrue("Onjuiste periode", equalPeriod2(D, P.unionWith(D)));
        Assert.assertTrue("Onjuiste periode", equalPeriod2(new Period2(p1, e2), P.unionWith(E)));
        Assert.assertNull("Geen periode", P.unionWith(F));
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testUnionWith2()
    {
        /**
        * 
        * @param period
        * @return the largest period which is part of this period 
        * and [period] will be returned; if the intersection is empty null will 
        * be returned
        */
        Period2 testPeriod2 = P.unionWith(null);
        Assert.fail("Period2 mag niet null zijn");
    }
    
    
    @Test
    public void testIntersectionWith()
    {
        /**
        * 
        * @param period
        * @return the largest period which is part of this period 
        * and [period] will be returned; if the intersection is empty null will 
        * be returned
        */
        Assert.assertNull("Geen intersectie met zichzelf", P.intersectionWith(P));
        
        Assert.assertNull("Geen intersectie", P.intersectionWith(A));
        Assert.assertTrue("Onjuiste periode", equalPeriod2(new Period2(p1, b2), P.intersectionWith(B)));
        Assert.assertTrue("Onjuiste periode", equalPeriod2(C, P.intersectionWith(C)));
        Assert.assertTrue("Onjuiste periode", equalPeriod2(P, P.intersectionWith(D)));
        Assert.assertNull("Geen intersectie", P.intersectionWith(E));
        Assert.assertNull("Geen intersectie", P.intersectionWith(F));
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testIntersectionWith2()
    {
        /**
        * 
        * @param period
        * @return the largest period which is part of this period 
        * and [period] will be returned; if the intersection is empty null will 
        * be returned
        */
        Period2 testPeriod2 = P.intersectionWith(null);
        Assert.fail("Period2 mag niet null zijn");
    }
    
    private boolean equalPeriod2(Period2 p1, Period2 p2)
    {
        if (p1.getBeginTime().compareTo(p2.getBeginTime()) == 0 && p1.getEndTime().compareTo(p2.getEndTime()) == 0)
        {
            return true;
        }
        return false;
    }
}
