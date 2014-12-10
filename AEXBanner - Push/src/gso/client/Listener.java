/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

import gso.shared.IListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Julius
 */
public class Listener extends UnicastRemoteObject implements IListener {

    private BannerController controller;
    
    public Listener(BannerController controller) throws RemoteException
    {
        this.controller = controller;
    }
    
    @Override
    public void setKoersen(String fondsen) throws RemoteException {
        this.controller.setKoersen(fondsen);
        System.out.println("Koersen: " + fondsen);
    }
    
}
