/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

import gso.Task.KoersenPuller;
import gso.shared.IBannerController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;

/**
 *
 * @author Julius op den Brouw
 */
public class BannerController extends UnicastRemoteObject implements IBannerController {
    
    private AEXBanner banner;
    private Timer timer;
    
    public BannerController(String ipAddress, int portNumber, AEXBanner banner) throws RemoteException
    {
        try
        {
        System.out.println("BannerController");
        this.banner = banner;
        
        System.out.println("Connection succesful");
        System.out.println("--IP: " + ipAddress);
        System.out.println("--PortNumber: " + portNumber);
        
        System.out.println("NEW KOERSENPULLER");
        KoersenPuller koersenPuller = new KoersenPuller(this, ipAddress, portNumber);
        koersenPuller.run();
        }
        catch (Exception ex)
        {
            System.out.println("BannerController Exception: " + ex.getMessage());
        }
    }
    
    @Override
    public void setKoersen(String koersen)
    {
        try
        {
            this.banner.setKoersen(koersen);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
