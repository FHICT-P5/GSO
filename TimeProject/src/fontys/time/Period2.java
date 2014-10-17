/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fontys.time;

/**
 * A stretch of time with a begin time and a duration
 * the duration is always greater than 0
 * @author Bart Bouten (A2)
 */
public class Period2 implements IPeriod
{

    Time beginTime;
    long duration;
    
    public Period2(Time bt, Time et)
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
            this.duration = bt.difference(et);
        }
    }
    
    /**
     * 
     * @return the begin time of this period
     */
    @Override
    public ITime getBeginTime()
    {
        return beginTime;
    }

    /**
     * 
     * @return the end time of this period
     */
    @Override
    public ITime getEndTime()
    {
        int minutes = (int)duration;
        Time endTime = beginTime.plus(minutes);
        return endTime;
    }

    /**
     * 
     * @return the length of this period expressed in minutes (always positive)
     */
    @Override
    public int length()
    {
        return (int)duration;
    }

    /**
     * beginTime will be the new begin time of this period
     * @param bt must be earlier than the current end time
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
        else if (beginTime.compareTo(this.getEndTime()) != -1)
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
     * @param et must be later than the current begin time
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
            int minutes = beginTime.difference(endTime);
            if((int)duration != minutes && minutes > 0)
            {
                duration = (long)minutes;
            }
            else
            {
                throw new IllegalArgumentException();
            }
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
    }

    /**
     * the end time of this period will be moved up with [minutes] minutes
     * @param minutes  minutes + length of this period must be positive  
     */
    @Override
    public void changeLengthWith(int minutes)
    {
        if(this.duration + minutes > 0)
        {
            this.duration += (long)minutes;
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
        Period2 p = (Period2)period;
        
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        else if (this.beginTime.getMinutes() >= p.getBeginTime().getMinutes() && length() <= p.length())
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
    public Period2 unionWith(IPeriod period)
    {
        if (period == null)
        {
            throw new IllegalArgumentException();
        }
               
        Period2 p = (Period2)period;
        
        if (p == this)
        {
            return null;
        }
        
        int a1 = this.beginTime.getMinutes();
        int a2 = this.beginTime.plus(length()).getMinutes();
        int p1 = p.getBeginTime().getMinutes();
        int p2 = p.getBeginTime().plus(p.length()).getMinutes();
        
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
                union1 = (Time) p.getBeginTime();
            }
            
            if (a2 > p2)
            {
                union2 = (Time)this.getEndTime();
            }
            else
            {
                union2 = (Time) p.getEndTime();
            }
            
            return new Period2(union1, union2);
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
    public Period2 intersectionWith(IPeriod period)
    {
        Period2 p = (Period2)period;
        
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        
        int a1 = this.beginTime.getMinutes();
        int a2 = this.beginTime.plus(length()).getMinutes();
        int p1 = p.getBeginTime().getMinutes();
        int p2 = p.getBeginTime().plus(p.length()).getMinutes();
        
        if ((a1 > p1 && a1 < p2) || (a2 > p1 && a2 < p2) || (a1 < p1 && a2 > p2))
        {
            Time intersection1;
            Time intersection2;
            
            if (a1 < p1)
            {
                intersection1 = (Time) p.getBeginTime();
            }
            else
            {
                intersection1 = this.beginTime;
            }
            
            if (a2 > p2)
            {
                intersection2 = (Time) p.getEndTime();
            }
            else
            {
                intersection2 = (Time) this.getEndTime();
            }
            
            return new Period2(intersection1, intersection2);
        }
        else
        {
            return null;
        }
    }
    
}
