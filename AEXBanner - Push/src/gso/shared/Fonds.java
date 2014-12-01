/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.shared;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Julius
 */
public class Fonds implements IFonds, Serializable {
    
    private String naam;
    private double koers;
    
    public Fonds(String naam)
    {
        this.naam = naam;
        this.koers = Math.random() * 100;
    }
    
    public String getNaam()
    {
        return this.naam;
    }
    
    public double getKoers()
    {
        return this.koers;
    }
    
    public void setKoers(double value)
    {
        this.koers = value;
    }
}
