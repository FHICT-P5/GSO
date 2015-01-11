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
    
    IBank getBank(int rekeningNr) throws RemoteException;
    
    IBank getBankFromName(String bankName) throws RemoteException;
    
    void addBank(IBank bank) throws RemoteException;
}
