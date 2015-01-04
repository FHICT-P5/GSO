/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import java.io.Serializable;

/**
 *
 * @author Juliusername
 */
public interface ICentraleBank extends Serializable {
    
    /**
     * Returns the bank from a list of bank registered to the Central Bank, that contains the given rekeningNr
     * @param rekeningNr
     * @return 
     */
    IBank getBank(int rekeningNr);
    
    /**
     * Adds a bank to the list
     * @param bank 
     */
    void addBank(IBank bank);
}
