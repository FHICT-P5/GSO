/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.Task;

import gso.server.Effectenbeurs;
import gso.server.Publisher;
import gso.shared.Fonds;
import gso.shared.IListener;
import gso.shared.IFonds;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author Julius
 */
public class KoersenPusher extends TimerTask {

    private Effectenbeurs effectenbeurs;
    private final List<IListener> listeners;
        
    public KoersenPusher(Effectenbeurs effectenbeurs)
    {
        System.out.println("Constructing koersenpusher");
        this.effectenbeurs = effectenbeurs;
        this.listeners = new ArrayList<>();
    }
    
    public void meldAan(IListener listener)
    {
        if (listener != null)
        {
            this.listeners.add(listener);
        }
    }
    
    @Override
    public void run() {
                System.out.println("Pushing");
        
            try
                {
                    for (IListener il : this.listeners)
                    {
//                        System.out.println("Is running.");
//                        // Locate registry at IP address and port number
//                        try {
//                            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
//                        } catch (RemoteException ex) {
//                            System.out.println("Client: Cannot locate registry");
//                            System.out.println("Client: RemoteException: " + ex.getMessage());
//                            registry = null;
//                        }
//
//                        // Print result locating registry
//                        if (registry != null) {
//                            System.out.println("Client: Registry located");
//                        } else {
//                            System.out.println("Client: Cannot locate registry");
//                            System.out.println("Client: Registry is null pointer");
//                        }
//
//                        // Print contents of registry
//                        if (registry != null) {
//                            //printContentsRegistry();
//                        }
//
//                        // Bind student administration using registry
//                        if (registry != null) {
//                            try {
//                                controller = (BannerController) registry.lookup(bindingName);
//                            } catch (RemoteException ex) {
//                                System.out.println("Client: Cannot bind effectenbeurs");
//                                System.out.println("Client: RemoteException: " + ex.getMessage());
//                                effectenbeurs = null;
//                            } catch (NotBoundException ex) {
//                                System.out.println("Client: Cannot bind effectenbeurs");
//                                System.out.println("Client: NotBoundException: " + ex.getMessage());
//                                effectenbeurs = null;
//                            }
//                        }

                        // Print result binding student administration
                        //EffectenbeursObserver eobserver = (EffectenbeursObserver)eo;
                        if (il != null) {
                            System.out.println("Server: Listener bound");
                            
                            String koersen = " ";

                            System.out.println("Koersen: ");
                            try
                            {
                                for (IFonds f : effectenbeurs.getKoersen())
                                {
                                    Fonds fonds = (Fonds)f;
                                    String fondsText = fonds.getNaam() + ": " + getRoundedFonds(fonds.getKoers());
                                    //System.out.println(fondsText);
                                    koersen += fondsText + " ";
                                }
                                il.setKoersen(koersen);
                                System.out.println("Pushing success");
                            }
                            catch (Exception ex)
                            {
                                System.out.println("RemoteException: " + ex.getMessage());
                            }
                        } else {
                            System.out.println("Server: Listener is null pointer");
                        }

                        
                    }
                }
                catch(Exception ex)
                {
                    System.out.println("KoersenPusher Exception: " + ex.getMessage());
                }
            }

     private double getRoundedFonds(double fonds)
     {
                return Math.round(fonds * 100.00) / 100.00;
     }
    
}
