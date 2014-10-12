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
 * A person with a name and a list of (@Link Appointment) appointments.
 * @author Bart Bouten (A2) specification
 * @author Julius op den Brouw (B2) implementation
 */
public class Contact {
    
    private String name;
    private List<Appointment> appointments;
    
    /**
     * Instantiates a new Contacts
     * @param name The value the name should be set tos
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
     * Gets the name of the contact
     * @return Return a string containing the names
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Adds an appointment to the list of appointments in the contact
     * @param a The appointment to be added
     * @return Returns true if addition is successful.
     * Returns false if addition is not successful.
     * throws IllegalArgumentException if parameter Appointment a is null.
     */
    protected boolean addAppointment(Appointment a)
    {
        if (a == null)
        {
            throw new IllegalArgumentException();
        }
        
        if (!this.appointments.contains(a))
        {
            for(Appointment app : appointments)
            {
                if(a.getPeriod() == app.getPeriod())
                {
                    return false;
                }
            }
            this.appointments.add(a);
            return true;
        }
        
        return false;
    }
    
    /**
     * Removes an appointment from the list of appointments in the contact.
     * @param a The appointment to be removed.
     * @return Returns true if removal is successful.
     * Returns false if removal is not successful.
     */
    protected boolean removeAppointment(Appointment a)
    {
        if (a == null)
        {
            throw new IllegalArgumentException();
        }
        
        if (this.appointments.contains(a))
        {
            this.appointments.remove(a);
            
            a.removeContact(this);
            return true;
        }
        return false;
    }
    
    /**
     * Returns an iterated appointment from the list of appointments in the contact
     * @return list of appointments
     */
    public Iterator<Appointment> appointments()
    {
        return appointments.iterator();
    }
}
