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
 * A period of time containing a subject and a list of (@Link Contact) contacts.
 * @author Juliusername
 */
public class Appointment { //implements IPeriod
    
    private String subject;
    private IPeriod period;
    private List<Contact> Contacts;
    
    public Appointment(String subject, IPeriod period)
    {
        this.subject = subject;
        this.period = period;
        this.Contacts = new ArrayList<Contact>();
    }
    
    /**
     * Returns the subject of the appointment.
     * @return A String containing the subject.
     */
    public String getSubject()
    {
        return this.subject;
    }
    
    /**
     * Returns the period of time of the appointment.
     * @return An IPeriod containing the period of time.
     */
    public IPeriod getPeriod()
    {
        return this.period;
    }
    
    /**
     * Returns an iterated contact from the contacts of the appointment.
     * @return A Contact
     */
    public Iterator<Contact> invitees()
    {
        return null;
    }
    
    /**
     * Adds a contact to the list of contacts in the appointment.
     * @param c The contact to be added.
     * @return A boolean containing the result of success of adding the contact.
     */
    public boolean addContact(Contact c)
    {
        this.Contacts.add(c);
        return true;
    }
    
    
    /**
     * Removes a contact from the list of contacts in the appointment.
     * @param c The contact to be removed.
     * @return A boolean containing the result of succes of removing the contact.
     */
    public boolean removeContact(Contact c)
    {
        this.Contacts.remove(c);
        return true;
    }
}
