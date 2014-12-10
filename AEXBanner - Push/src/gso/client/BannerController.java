/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

import gso.server.Effectenbeurs;
import gso.shared.IEffectenbeurs;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Julius op den Brouw
 */
public class BannerController extends UnicastRemoteObject {
    
    private AEXBanner banner;
    private Listener listener;
    private IEffectenbeurs effectenbeurs;
    private static final String bindingName = "effectenbeurs";
    private Registry registry = null;
    
    public BannerController(String ipAddress, int portNumber, AEXBanner banner) throws RemoteException
    {
        
        try
        {
            System.out.println("BannerController");
            this.banner = banner;

            System.out.println("Connection succesful");
            System.out.println("--IP: " + ipAddress);
            System.out.println("--PortNumber: " + portNumber);

            listener = new Listener(this);

            meldAan(ipAddress, portNumber);
        }
        catch (Exception ex)
        {
            System.out.println("BannerController Exception: " + ex.getMessage());
        }
    }
    
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
    
    private void meldAan(String ipAddress, int portNumber)
    {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try
                {
                    System.out.println("Is running.");
                    // Locate registry at IP address and port number
                    try {
                        registry = LocateRegistry.getRegistry(ipAddress, portNumber);
                    } catch (RemoteException ex) {
                        System.out.println("Client: Cannot locate registry");
                        System.out.println("Client: RemoteException: " + ex.getMessage());
                        registry = null;
                    }

                    // Print result locating registry
                    if (registry != null) {
                        System.out.println("Client: Registry located");
                    } else {
                        System.out.println("Client: Cannot locate registry");
                        System.out.println("Client: Registry is null pointer");
                    }

                    // Print contents of registry
                    if (registry != null) {
                        //printContentsRegistry();
                    }

                    // Bind student administration using registry
                    if (registry != null) {
                        try {
                            effectenbeurs = (IEffectenbeurs)registry.lookup(bindingName);
                        } catch (RemoteException ex) {
                            System.out.println("Client: Cannot bind effectenbeurs");
                            System.out.println("Client: RemoteException: " + ex.getMessage());
                            effectenbeurs = null;
                        } catch (NotBoundException ex) {
                            System.out.println("Client: Cannot bind effectenbeurs");
                            System.out.println("Client: NotBoundException: " + ex.getMessage());
                            effectenbeurs = null;
                        }
                    }

                    // Print result binding student administration
                    if (effectenbeurs != null) {
                        System.out.println("Client: effectenbeurs bound");
                    } else {
                        System.out.println("Client: effectenbeurs is null pointer");
                    }

                    
                    effectenbeurs.meldAan(listener);
                }
                catch(Exception ex)
                {
                     System.out.println("BannerController Exception: " + ex.getMessage());
                }
            }
        });
    }
}
