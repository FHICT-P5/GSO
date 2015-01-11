/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Juliusername
 */
public interface ICentraleBank extends Remote {
    
    /**
     * Gets de Bank die het gegeven rekeningNr bevat
     * @param rekeningNr Het gegeven rekeningNr
     * @return De Bank die het gegeven rekeningNr bevat, anders return null
     * @throws RemoteException 
     */
    IBank getBank(int rekeningNr) throws RemoteException;
    
    /**
     * Gets de Bank met de gegeven banknaam
     * @param bankName De gegeven banknaam
     * @return Return de Bank met de gegeven banknaam, ander return null
     * @throws RemoteException 
     */
    IBank getBankFromName(String bankName) throws RemoteException;
    
    /**
     * Voeg een Bank toe aan de CentraleBank
     * @param bank De Bank die toegevoegd moet worden
     * @throws RemoteException 
     */
    void addBank(IBank bank) throws RemoteException;
}
