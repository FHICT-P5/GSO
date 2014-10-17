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
 * a moment on the time line with a precision of one minute
 * @author Bart Bouten (A2)
 */
public class Time implements ITime {
    
    Calendar date;
    
    public Time(int y, int m, int d, int h, int min) {
        
        if(m >= 1 && m <= 12 && d >= 1 && d <= 31 && h >= 0 && h <= 23 && min >= 0 && min <= 59)
        {
            date = new GregorianCalendar(y, m - 1, d, h, min);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * 
     * @return the concerning year of this time
     */
    @Override
    public int getYear()
    {
        return date.get(Calendar.YEAR);
    }
    
    /**
     * 
     * @return the number of the month of this time (1..12)
     */
    @Override
    public int getMonth()
    {
        return date.get(Calendar.MONTH);
    }
    
    /**
     * 
     * @return the number of the day in the month of this time (1..31)
     */
    public int getDay()
    {
        return date.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 
     * @return the number of hours in a day of this time (0..23)
     */
    @Override
    public int getHours()
    {
        return date.get(Calendar.HOUR);
    }
    
    /**
     * 
     * @return the number of minutes in a hour of this time (0..59)
     */
    public int getMinutes()
    {
        return date.get(Calendar.MINUTE);
    }
    
    /**
     * 
     * @return the concerning day in the week of this time
     */
    @Override
    public DayInWeek getDayInWeek()
    {
        DayInWeek dag;
        
        switch(date.get(Calendar.DAY_OF_WEEK))
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
        return dag;
    }
    
    /**
     * 
     * @param minutes (a negative value is allowed)
     * @return  this time plus minutes
     */
    @Override
    public Time plus(int minutes)
    {
        //System.out.println(date.get(Calendar.YEAR) + " - " + date.get(Calendar.MONTH) + " - " + date.get(Calendar.DAY_OF_MONTH) + " - " + date.get(Calendar.HOUR) + " - " + date.get(Calendar.MINUTE));
        
        //Calendar c = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.HOUR), date.get(Calendar.MINUTE));
        Calendar c = (GregorianCalendar)this.date.clone();
        c.add(Calendar.MINUTE, minutes);
               
        //System.out.println(c.get(Calendar.YEAR) + " - " + c.get(Calendar.MONTH) + " - " + c.get(Calendar.DAY_OF_MONTH) + " - " + c.get(Calendar.HOUR) + " - " + c.get(Calendar.MINUTE));
        
        Time t = new Time(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR), c.get(Calendar.MINUTE));
        return t;
    }
    
    /**
     * 
     * @param time
     * @return the difference between this time and [time] expressed in minutes
     */
    @Override
    public int difference(ITime time)
    {
        if(time == null)
        {
            throw new IllegalArgumentException();
        }
        
        Time t = (Time)time;
               
        long dif = ((date.getTimeInMillis() - t.date.getTimeInMillis()) / 1000) / 60;
 
        return Math.abs((int)dif);
    }
    
    /**
     * 
     * @param time
     * @return an int value refering to whether the given ITime is earlier, later of at the exact same moment as this Time
     * returns -1 when this Time is earlier than the given ITime
     * return 0 when this Time is exacly the same moment in time as the given the ITime
     * return 1 when this Time is later than the given ITime
     * throws IllegalArgumentException if the given ITime is null
     */
    @Override
    public int compareTo(ITime time)
    {
        if (time == null)
        {
            throw new IllegalArgumentException();
        }
        
        Time t = (Time)time;
        
        System.out.println(this.date.get(Calendar.YEAR) + " - " + this.date.get(Calendar.MONTH) + " - " + this.date.get(Calendar.DAY_OF_MONTH) + " - " + this.date.get(Calendar.HOUR) + " - " + this.date.get(Calendar.MINUTE) + " - " + this.date.get(Calendar.MINUTE));
        System.out.println(t.date.get(Calendar.YEAR) + " - " + t.date.get(Calendar.MONTH) + " - " + t.date.get(Calendar.DAY_OF_MONTH) + " - " + t.date.get(Calendar.HOUR) + " - " + t.date.get(Calendar.MINUTE) + " - " + t.date.get(Calendar.MINUTE));
                
        System.out.println(this.date.compareTo(t.date));
        
        return this.date.compareTo(t.date);
    }
}
