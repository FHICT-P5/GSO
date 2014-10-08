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
    
    public void setBeginTime(Time bt)
    {
        if (bt == null)
        {
            throw new IllegalArgumentException();
        }
        else if (bt.compareTo(this.endTime) != -1)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.beginTime = bt;
        }
    }
    
    public void setEndTime(Time et)
    {
        if (et == null)
        {
            throw new IllegalArgumentException();
        }
        else if (et.compareTo(this.beginTime) != 1)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            this.endTime = et;
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
    }
    
    public boolean isPartOf(Period period)
    {
        if (period == null)
        {
            throw new IllegalArgumentException();
        }
        else if (this.beginTime.getMinutes() >= period.getBeginTime().getMinutes() && this.endTime.getMinutes() <= period.getEndTime().getMinutes())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Period unionWith(Period period)
    {
        if (period == null)
        {
            throw new IllegalArgumentException();
        }
        
        int a1 = this.beginTime.getMinutes();
        int a2 = this.endTime.getMinutes();
        int b1 = period.getBeginTime().getMinutes();
        int b2 = period.getEndTime().getMinutes();
        
        if ((a1 >= b1 && a1 <= b2) || (a2 >= b1 && a2 <= b2))
        {
            Time u1;
            Time u2;
            
            if (a1 < b1)
            {
                u1 = this.beginTime;
            }
            else
            {
                u1 = period.getBeginTime();
            }
            
            if (a2 > b2)
            {
                u2 = this.endTime;
            }
            else
            {
                u2 = period.getEndTime();
            }
            
            return new Period(u1, u2);
        }
        else
        {
            return null;
        }
    }
    
    public Period intersectionWith(Period period)
    {
        if (period == null)
        {
            throw new IllegalArgumentException();
        }
        
        int a1 = this.beginTime.getMinutes();
        int a2 = this.endTime.getMinutes();
        int b1 = period.getBeginTime().getMinutes();
        int b2 = period.getEndTime().getMinutes();
        
        if ((a1 > b1 && a1 < b2) || (a2 > b1 && a2 < b2))
        {
            Time u1;
            Time u2;
            
            if (a1 < b1)
            {
                u1 = period.getBeginTime();
            }
            else
            {
                u1 = this.beginTime;
            }
            
            if (a2 > b2)
            {
                u2 = period.getEndTime();
            }
            else
            {
                u2 = this.endTime;
            }
            
            return new Period(u1, u2);
        }
        else
        {
            return null;
        }
    }
}
