/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

import gso.shared.IEffectenbeursObserver;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Julius
 */
public class EffectenbeursObserver extends UnicastRemoteObject implements IEffectenbeursObserver {

    public EffectenbeursObserver(BannerController controller) throws RemoteException
    {
        
    }
    
    @Override
    public void setKoersen(String fondsen) throws RemoteException {
        
    }
    
}
