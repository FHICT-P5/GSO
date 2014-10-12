/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Juliusername
 */
public class Contact {
    
    private String name;
    private List<Appointment> appointments;
    
    /**
     * 
     * @param name 
     */
    public Contact(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException();
        }
        
        this.name = name;
        this.appointments = new ArrayList<Appointment>();
    }
    
    /**
     * 
     * @return 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * 
     * @param a
     * @return 
     */
    protected boolean addAppointment(Appointment a)
    {
        if (a == null)
        {
            throw new IllegalArgumentException();
        }
        
        if (!this.appointments.contains(a))
        {
            return this.appointments.add(a);
        }
        
        return false;
    }
    
    /**
     * 
     * @param a 
     */
    protected void removeAppointment(Appointment a)
    {
        if (a == null)
        {
            throw new IllegalArgumentException();
        }
        
        if (this.appointments.contains(a))
        {
            this.appointments.remove(a);
            
            a.removeContact(this);
        }
    }
    
    /**
     * 
     * @return 
     */
    public Iterator<Appointment> appointments()
    {
        return null;
    }
}
