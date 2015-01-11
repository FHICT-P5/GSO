/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliusername
 */
public class CentraleBank extends UnicastRemoteObject implements ICentraleBank {

    private List<IBank> banken;
    
    public CentraleBank() throws RemoteException
    {
        this.banken = new ArrayList<>();
    }
    
    @Override
    public IBank getBankFromName(String bankName)
    {
        try
        {     
            for (IBank b : this.banken)
            {
                if (b.getName().equals(bankName))
                {
                    return b;
                }
            }
            return null;
        }
        catch (RemoteException ex)
        {
            System.out.println("RemoteException: " + ex.getMessage());
            return null;
        }
    }
    
    @Override
    public IBank getBank(int rekeningNr) {
        IRekeningTbvBank rekening = null;
        IBank bank = null;
        
        for (IBank b : banken)
        {
            try
            {
                rekening = (IRekeningTbvBank)b.getRekening(rekeningNr);
                bank = b;
                break;
            }
            catch (Exception ex)
            {
                System.out.println("Exception: " + ex.getMessage());
                rekening = null;
            }
            
            
        }
        
        if (rekening == null || bank == null)
        {
            return null;
        }
        else
        {
            return bank;
        }
    }

    @Override
    public void addBank(IBank bank) {
        
        try
        {
        for (IBank b : this.banken)
        {
            if (b.getName() == bank.getName())
            {
                return;
            }
        }
        
        this.banken.add(bank);
        }
        catch (RemoteException ex)
        {
            System.out.println("RemoteException: " + ex.getMessage());
        }
    }
    
}
