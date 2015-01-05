/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliusername
 */
public class CentraleBank{

    private List<IBank> banken;
    
    public CentraleBank()
    {
        this.banken = new ArrayList<>();
    }
    
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

    public void addBank(IBank bank) {
        this.banken.add(bank);
    }
    
}
