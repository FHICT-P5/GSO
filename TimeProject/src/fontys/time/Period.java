/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fontys.time;


/**
 *
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
    
    public Time getBeginTime()
    {
        return beginTime;
    }
    
    public Time getEndTime()
    {
        return endTime;
    }
    
    public int length()
    {
        return endTime.getMinutes() - beginTime.getMinutes();
    }
    
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
    
    public void move(int minutes)
    {
        this.beginTime = this.beginTime.plus(minutes);
        this.endTime = this.endTime.plus(minutes);
    }
    
    public void changeLengthWith(int minutes)
    {
        int length = length();
        if (length + minutes > 0)
        {
            this.endTime.plus(minutes);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
    
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
    
    @Override
    public Period unionWith(IPeriod period)
    {
        Period p = (Period)period;
        
        if (p == null)
        {
            throw new IllegalArgumentException();
        }
        
        int a1 = this.beginTime.getMinutes();
        int a2 = this.endTime.getMinutes();
        int p1 = period.getBeginTime().getMinutes();
        int p2 = period.getEndTime().getMinutes();
        
        if ((a1 >= p1 && a1 <= p2) || (a2 >= p1 && a2 <= p2))
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
        
        if ((a1 > p1 && a1 < p2) || (a2 > p1 && a2 < p2))
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
