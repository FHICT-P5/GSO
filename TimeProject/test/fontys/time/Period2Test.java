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
 * @author Bart Bouten(A2)
 */
public class Period2Test {
    
    private Time a1;
    private long aDuration;
    private Time b1;
    private long bDuration;
    private Time c1;
    private long cDuration;
    private Time d1;
    private long dDuration;
    private Time e1;
    private long eDuration;
    private Time f1;
    private long fDuration;
    
    private Time p1;
    private long pDuration;
    
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
        aDuration = 5;
        b1 = new Time(2014, 1, 1, 12, 1);
        bDuration = 6;
        c1 = new Time(2014, 1, 1, 12, 8);
        cDuration = 1;
        d1 = new Time(2014, 1, 1, 12, 4);
        dDuration = 7;
        e1 = new Time(2014, 1, 1, 12, 10);
        eDuration = 4;
        f1 = new Time(2014, 1, 1, 12, 11);
        fDuration = 14;
    
        p1 = new Time(2014, 1, 1, 12, 5);
        pDuration = 5;
        
        A = new Period2(a1, aDuration);
        B = new Period2(b1, bDuration);
        C = new Period2(c1, cDuration);
        D = new Period2(d1, dDuration);
        E = new Period2(e1, eDuration);
        F = new Period2(f1, fDuration);
        
        P = new Period2(p1, pDuration);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test (expected=IllegalArgumentException.class)
    public void testPeriod21()
    {
        /**
        * creation of a period with begin time bt and duration et
        * @param bt
        * @param et duration has to larger than 0.
        */
        Time bt = a1;
        long et = -aDuration;
        Period2 A = new Period2(bt, et);
        Assert.fail("begintijd moet eerder zijn dan eindtijd");
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testPeriod22()
    {
        /**
        * creation of a period with begin time bt and duration et
        * @param bt
        * @param et duration has to larger than 0.
        */
        Time bt = a1;
        long et = 0;
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
        int length = (int) (aDuration - a1.getMinutes());
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
    

    @Test
    public void testSetEndTime1()
    {
        /**
        * endTime will be the new end time of this period
        * @param beginTime (FOUT: dit moet zijn endTime) must be later than the current begin time
        * of this period
        */
        A.setEndTime(c1);
        Assert.assertTrue("onjuiste eindtijd", A.getEndTime().compareTo(c1) == 0);
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
        
        Period2 movedA = new Period2(new Time(2014, 1, 1, 12, 3), 3);
        
        Assert.assertTrue("onjuiste verplaating", equalPeriod(movedA, A));
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
        Assert.assertEquals("Period A zit niet volledig in period B", expResult, result);
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
        Assert.assertEquals("Period B zit niet volledig in period A", expResult, result);
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
        Assert.assertEquals("Period C is een deel van P", expResult, result);
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
                       
        Assert.assertTrue("Onjuiste periode", equalPeriod(new Period2(a1, pDuration), P.unionWith(A)));
        Assert.assertTrue("Onjuiste periode", equalPeriod(new Period2(b1, pDuration), P.unionWith(B)));
        Assert.assertTrue("Onjuiste periode", equalPeriod(P, P.unionWith(C)));
        Assert.assertTrue("Onjuiste periode", equalPeriod(D, P.unionWith(D)));
        Assert.assertTrue("Onjuiste periode", equalPeriod(new Period2(p1, eDuration), P.unionWith(E)));
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
        Period2 testPeriod = P.unionWith(null);
        Assert.fail("Period mag niet null zijn");
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
        Assert.assertTrue("Onjuiste periode", equalPeriod(new Period2(p1, bDuration), P.intersectionWith(B)));
        Assert.assertTrue("Onjuiste periode", equalPeriod(C, P.intersectionWith(C)));
        Assert.assertTrue("Onjuiste periode", equalPeriod(P, P.intersectionWith(D)));
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
        Period2 testPeriod = P.intersectionWith(null);
        Assert.fail("Period mag niet null zijn");
    }
    
    private boolean equalPeriod(Period2 p1, Period2 p2)
    {
        if (p1.getBeginTime().compareTo(p2.getBeginTime()) == 0 && p1.getEndTime().compareTo(p2.getEndTime()) == 0)
        {
            return true;
        }
        return false;
    }
}
