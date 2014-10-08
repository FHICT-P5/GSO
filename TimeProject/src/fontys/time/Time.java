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
            date = new GregorianCalendar(y, m - 1, d, h, min);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public int getYear()
    {
        return date.get(Calendar.YEAR);
    }
    
    @Override
    public int getMonth()
    {
        return date.get(Calendar.MONTH);
    }
    
    public int getDay()
    {
        return date.get(Calendar.DAY_OF_MONTH);
    }
    
    @Override
    public int getHours()
    {
        return date.get(Calendar.HOUR);
    }
    
    public int getMinutes()
    {
        return date.get(Calendar.MINUTE);
    }
    
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
    
    @Override
    public Time plus(int minutes)
    {
        date.add(Calendar.MINUTE, minutes);
        return this;
    }
    
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
    
    @Override
    public int compareTo(ITime time)
    {
        if (time == null)
        {
            throw new IllegalArgumentException();
        }
        
        Time t = (Time)time;
        
        int year1 = getYear();
        int year2 = t.getYear();
        
        if (year1 < year2)
        {
            return -1;
        }
        else if (year1 > year2)
        {
            return 1;
        }
        else
        {
            int month1 = getMonth();
            int month2 = t.getMonth();
            
            if (month1 < month2)
            {
                return -1;
            }
            else if (month1 > month2)
            {
                return 1;
            }
            else
            {
                int day1 = getDay();
                int day2 = t.getDay();
            
                if (day1 < day2)
                {
                 return -1;
                }
                else if (day1 > day2)
                {
                    return 1;
                }
                else
                {
                    
                    int hour1 = getHours();
                    int hour2 = t.getHours();
            
                    if (hour1 < hour2)
                    {
                        return -1;
                    }
                    else if (hour1 > hour2)
                    {
                        return 1;
                    }
                    else
                    {
                        int minutes1 = getMinutes();
                        int minutes2 = t.getMinutes();
            
                        if (minutes1 < minutes2)
                        {
                            return -1;
                        }
                        else if (minutes1 > minutes2)
                        {
                            return 1;
                        }
                        else
                        {
                            return 0;
                        }
                    }
                }
            }
        }
    }
}
