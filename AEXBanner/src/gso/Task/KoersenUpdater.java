/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.Task;

import gso.shared.Fonds;
import gso.shared.IFonds;
import java.util.TimerTask;

/**
 *
 * @author Julius
 */
public class KoersenUpdater extends TimerTask{

    private IFonds[] fondsen;
    
    public KoersenUpdater(IFonds[] fondsen)
    {
        this.fondsen = fondsen;
    }
    
    @Override
    public void run() {
        
        for (IFonds f : this.fondsen)
        {
            try
            {
                Fonds fonds = (Fonds)f;
                
                updateFonds(fonds);
            }
            catch(Exception ex)
            {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }
    
    private void updateFonds(Fonds fonds)
    {
        double koers = fonds.getKoers() + (Math.random() * 2) - 1;
        
        fonds.setKoers(koers);
    }
}
