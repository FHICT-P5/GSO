/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fontys.time;


/**
 * A stretch of time with a begin time and end time.
 * The end time is always later then the begin time; the length of the period is
 * always positive
 * @author Julius op den Brouw (B2)
 */
public class Period implements IPeriod {
    
    private Time beginTime;
    private Time endTime;
    
    
    public Period(Time bt, Time et)
    {
        if (bt == null || et == null)
        {
            throw new IllegalArgumentException();
        }
        else if (bt.compareTo(et) != -1)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.beginTime = bt;
            this.endTime = et;
        }
    }
    
    /**
     * 
     * @return the begin time of this period
     */
    @Override
    public Time getBeginTime()
    {
        return beginTime;
    }
    
    /**
     * 
     * @return the end time of this period
     */
    @Override
    public Time getEndTime()
    {
        return endTime;
    }
    
    /**
     * 
     * @return the length of this period expressed in minutes (always positive)
     */
    @Override
    public int length()
    {
        return endTime.difference(beginTime);
    }
    
    /**
     * beginTime will be the new begin time of this period
     * @param beginTime must be earlier than the current end time
     * of this period
     */
    @Override
    public void setBeginTime(ITime bt)
    {
        Time beginTime = (Time)bt;
        
        if (beginTime == null)
        {
            throw new IllegalArgumentException();
        }
        else if (beginTime.compareTo(this.endTime) != -1)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.beginTime = beginTime;
        }
    }
    
    /**
     * endTime will be the new end time of this period
     * @param endTime must be later than the current begin time
     * of this period
     */
    @Override
    public void setEndTime(ITime et)
    {
        Time endTime = (Time)et;
                
        if (endTime == null)
        {
            throw new IllegalArgumentException();
            
        }
        else if (endTime.compareTo(this.beginTime) != 1)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.endTime = endTime;
        }
    }
    
    /**
     * the begin and end time of this period will be moved up both with [minutes]
     * minutes
     * @param minutes (a negative value is allowed)
     */
    @Override
    public void move(int minutes)
    {
        this.beginTime = this.beginTime.plus(minutes);
        this.endTime = this.endTime.plus(minutes);
    }
    
    /**
     * the end time of this period will be moved up with [minutes] minutes
     * @param minutes  minutes + length of this period must be positive  
     */
    @Override
    public void changeLengthWith(int minutes)
    {
        int length = length();
        if (length + minutes > 0)
        {
            this.endTime = this.endTime.plus(minutes);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * 
     * @param period 
     * @return true if all moments within this period are included within [period], 
     * otherwise false
     */
    @Override
    public boolean isPartOf(IPeriod period)
    {
        Period p = (Period)period;
        
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        else if (this.beginTime.getMinutes() >= p.getBeginTime().getMinutes() && this.endTime.getMinutes() <= p.getEndTime().getMinutes())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 
     * @param period
     * @return if this period and [period] are consecutive or possess a
     * common intersection, then the smallest period p will be returned, 
     * for which this period and [period] are part of p, 
     * otherwise null will be returned 
     */
    @Override
    public Period unionWith(IPeriod period)
    {
        if (period == null)
        {
            throw new IllegalArgumentException();
        }
               
        Period p = (Period)period;
        
        if (p == this)
        {
            return null;
        }
        
        int a1 = this.beginTime.getMinutes();
        int a2 = this.endTime.getMinutes();
        int p1 = period.getBeginTime().getMinutes();
        int p2 = period.getEndTime().getMinutes();
        
        if ((a1 >= p1 && a1 <= p2) || (a2 >= p1 && a2 <= p2) || (a1 < p1 && a2 > p2))
        {
            Time union1;
            Time union2;
            
            if (a1 < p1)
            {
                union1 = this.beginTime;
            }
            else
            {
                union1 = p.getBeginTime();
            }
            
            if (a2 > p2)
            {
                union2 = this.endTime;
            }
            else
            {
                union2 = p.getEndTime();
            }
            
            return new Period(union1, union2);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * 
     * @param period
     * @return the largest period which is part of this period 
     * and [period] will be returned; if the intersection is empty null will 
     * be returned
     */
    @Override
    public Period intersectionWith(IPeriod period)
    {
        Period p = (Period)period;
        
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        
        int a1 = this.beginTime.getMinutes();
        int a2 = this.endTime.getMinutes();
        int p1 = p.getBeginTime().getMinutes();
        int p2 = p.getEndTime().getMinutes();
        
        if ((a1 > p1 && a1 < p2) || (a2 > p1 && a2 < p2) || (a1 < p1 && a2 > p2))
        {
            Time intersection1;
            Time intersection2;
            
            if (a1 < p1)
            {
                intersection1 = p.getBeginTime();
            }
            else
            {
                intersection1 = this.beginTime;
            }
            
            if (a2 > p2)
            {
                intersection2 = p.getEndTime();
            }
            else
            {
                intersection2 = this.endTime;
            }
            
            return new Period(intersection1, intersection2);
        }
        else
        {
            return null;
        }
    }
}
