/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.aexbanner;

/**
 * Een Fonds interface voor het verkijgen van de naam en waarde van een koers
 * @author Julius op den Brouw
 */
public interface IFonds {
    
    /**
     * Gets de naam van het IFonds
     * @return Return de naam van het IFonds
     */
    String getNaam();
    
    /**
     * Gets de waarde van de koers
     * @return Returns de waarde van de koers
     */
    double getKoers();
}
