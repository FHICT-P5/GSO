/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fontys.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 *
 * @author Julius
 */
public class Time implements ITime {
    
    Calendar date;
    
    public Time(int y, int m, int d, int h, int min) {
        // get the supported ids for GMT-08:00 (Pacific Standard Time)
        String[] ids = TimeZone.getAvailableIDs(1 * 60 * 60 * 1000);
        // if no ids were returned, something is wrong. get out.
        if (ids.length == 0)
        {
            System.exit(0);
        }
        
        // create a Pacific Standard Time time zone
        SimpleTimeZone met = new SimpleTimeZone(1 * 60 * 60 * 1000, ids[0]);
        
        met.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
        met.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
        
        // create a GregorianCalendar with the Pacific Daylight time zone
        // and the current date and time
        //date = new GregorianCalendar(met);
        
        if(m >= 1 && m <= 12 && d >= 1 && d <= 31 && h >= 0 && h <= 23 && min >= 0 && min <= 59)
        {
            date = new GregorianCalendar(y, m, d, h, min);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public int getYear()
    {
        return date.YEAR;
    }
    
    @Override
    public int getMonth()
    {
        return date.MONTH;
    }
    
    public int getDay()
    {
        return date.DAY_OF_MONTH;
    }
    
    @Override
    public int getHours()
    {
        return date.HOUR;
    }
    
    public int getMinutes()
    {
        return date.MINUTE;
    }
    
    @Override
    public DayInWeek getDayInWeek()
    {
        DayInWeek dag;
        
        switch(date.DAY_OF_WEEK)
        {
            case 1:
                dag = DayInWeek.SUN;
                break;
            case 2:
                dag = DayInWeek.MON;
                break;
            case 3:
                dag = DayInWeek.TUE;
                break;
            case 4:
                dag = DayInWeek.WED;
                break;
            case 5:
                dag = DayInWeek.THU;
                break;
            case 6:
                dag = DayInWeek.FRI;
                break;
            case 7:
                dag = DayInWeek.SAT;
                break;
            default:
                dag = DayInWeek.SUN;
                break;
        }
        System.out.println(dag);
        System.out.println(date.DAY_OF_WEEK);
        System.out.println(date.getInstance());
        return dag;
    }
    
    @Override
    public Time plus(int minutes)
    {
        long dateMin = date.getTimeInMillis();
        long milli = (minutes * 60 * 1000);
        
        dateMin += milli;
        
        date.setTimeInMillis(dateMin);
        
        return this;
    }
    
    @Override
    public int difference(ITime time)
    {
        int dif;
        long time1 = date.getTimeInMillis();

        
        return dif;
    }
    
    @Override
    public int compareTo(ITime time)
    {
        
    }
}
