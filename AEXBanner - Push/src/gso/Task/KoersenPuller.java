/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.Task;

import gso.client.BannerController;
import gso.shared.IEffectenbeurs;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Platform;

/**
 *
 * @author Julius
 */
public class KoersenPuller implements Runnable, Serializable {

    private KoersenPuller me;
    private final BannerController controller;
    public String ipAddress;
    private static int portNumber;
    
    // Set binding name for effectenbeurs
    private static final String bindingName = "effectenbeurs";

    // References to registry and effectenbeurs
    private Registry registry = null;
    private IEffectenbeurs effectenbeurs = null;
    
    public KoersenPuller(BannerController controller, String ipAddress, int portNumber)
    {
        System.out.println("Constructing koersenpuller");
        
        this.controller = controller;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        this.me = this;
    }
    
    @Override
    public void run() {
        System.out.println("KOERSENPULLER RUN");
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
                            effectenbeurs = (IEffectenbeurs) registry.lookup(bindingName);
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

                    
                    effectenbeurs.meldAan(me);
                }
                catch(Exception ex)
                {
                     System.out.println("KoersenPuller Exception: " + ex.getMessage());
                }
            }
        });
                
        
    }
    
    public void setKoersen(String koersen)
    {
            System.out.println("IT IS WORKING");
    }
}
            
    

