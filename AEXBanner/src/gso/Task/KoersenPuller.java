/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.Task;

import gso.client.AEXBanner;
import gso.client.BannerController;
import gso.server.Effectenbeurs;
import gso.shared.Fonds;
import gso.shared.IEffectenbeurs;
import gso.shared.IFonds;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author Julius
 */
public class KoersenPuller extends TimerTask{

    private BannerController controller;
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
    }
    
    @Override
    public void run() {
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

                    String koersen = " ";
                    // Test RMI connection
                    if (effectenbeurs != null) {
                        //testStudentAdministration();

                        System.out.println("Koersen: ");
                        try
                        {
                            for (IFonds f : effectenbeurs.getKoersen())
                            {
                                Fonds fonds = (Fonds)f;
                                String fondsText = fonds.getNaam() + ": " + getRoundedFonds(fonds.getKoers());
                                System.out.println(fondsText);
                                koersen += fondsText + " ";
                            }
                            controller.setKoersen(koersen);

                        }
                        catch (RemoteException ex)
                        {
                            System.out.println("RemoteException: " + ex.getMessage());
                        }
                    }
                }
                catch(Exception ex)
                {

                }
            }
            
            private double getRoundedFonds(double fonds)
            {
                return Math.round(fonds * 100.00) / 100.00;
            }
        });
    }
    
    
}
