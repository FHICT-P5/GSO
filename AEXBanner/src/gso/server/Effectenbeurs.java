/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.server;

import gso.Task.KoersenUpdater;
import gso.shared.Fonds;
import gso.shared.IEffectenbeurs;
import gso.shared.IFonds;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;


/**
 *
 * @author Bart
 */
public class Effectenbeurs extends UnicastRemoteObject implements IEffectenbeurs 
{
    private IFonds[] fondsen;
    private Timer timer;

    public Effectenbeurs() throws RemoteException
    {
        this.fondsen = new IFonds[] {new Fonds("Philips"), new Fonds("Unilever"), new Fonds("Fontys")};
        setTimer();
    }
    
    @Override
    public IFonds[] getKoersen()
    {
        return this.fondsen;
    }
    
    private void setTimer()
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new KoersenUpdater(this.fondsen), 0, 1000);
    }
}
