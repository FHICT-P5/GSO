/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.server;

import gso.Task.KoersenPusher;
import gso.shared.IListener;
import gso.shared.IPublisher;
import java.rmi.RemoteException;
import java.util.Timer;

/**
 *
 * @author Julius
 */
public class Publisher implements IPublisher {

    private KoersenPusher koersenPusher;
    private Timer timerPush;
    
    public Publisher(KoersenPusher koersenPusher)
    {
        this.koersenPusher = koersenPusher;
        this.timerPush = new Timer();
        this.timerPush.scheduleAtFixedRate(this.koersenPusher, 0, 1000);
    }
    
    @Override
    public void meldAan(IListener listener) throws RemoteException {
        
        System.out.println("AANMELDEN...");
        this.koersenPusher.meldAan(listener);
        System.out.println("AANGEMELD");
    }
    
}
