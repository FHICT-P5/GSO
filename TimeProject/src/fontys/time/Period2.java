/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fontys.time;

/**
 *
 * @author Bart
 */
public class Period2 implements IPeriod
{

    Time beginTime;
    long duration;
    
    public Period2(Time bt, long dur)
    {
        if(bt == null)
        {
            throw new IllegalArgumentException();
        }
        
        this.beginTime = bt;
        this.duration = dur;
    }
    
    @Override
    public ITime getBeginTime()
    {
        return beginTime;
    }

    @Override
    public ITime getEndTime()
    {
        int minutes = (int)duration;
        Time endTime = beginTime.plus(minutes);
        return endTime;
    }

    @Override
    public int length()
    {
        return (int)duration;
    }

    @Override
    public void setBeginTime(ITime bt)
    {
        Time begintime = (Time)bt;
        
        if (begintime == null)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.beginTime = begintime;
        }
    }

    @Override
    public void setEndTime(ITime endTime)
    {
        if (endTime == null)
        {
            throw new IllegalArgumentException();
        }
        
        int minutes = beginTime.difference(endTime);
        if((int)duration != minutes)
        {
            duration = (long)minutes;
        }
    }

    @Override
    public void move(int minutes)
    {
        this.beginTime = this.beginTime.plus(minutes);
        this.duration += (long)minutes;
    }

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

    @Override
    public IPeriod unionWith(IPeriod period)
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
        int aDuration = this.length();
        int p1 = period.getBeginTime().getMinutes();
        int pDuration = period.length();
        
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
            
            return new Period2(union1, union2);
        }
        else
        {
            return null;
        }
    }

    @Override
    public IPeriod intersectionWith(IPeriod period)
    {
        Period2 p = (Period2)period;
        
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        
        int a1 = this.beginTime.getMinutes();
        int aDuration = this.length();
        int p1 = period.getBeginTime().getMinutes();
        int pDuration = period.length();
        
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
            
            return new Period2(intersection1, intersection2);
        }
        else
        {
            return null;
        }
    }
    
}