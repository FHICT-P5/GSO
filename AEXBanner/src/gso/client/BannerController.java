/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

import gso.Task.KoersenPuller;
import gso.shared.IEffectenbeurs;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Timer;

/**
 *
 * @author Julius op den Brouw
 */
public class BannerController {
    
    private AEXBanner banner;
    private Timer timer;
    
    public BannerController(String ipAddress, int portNumber)
    {
        this.banner = new AEXBanner();
        
        System.out.println("Connection succesful");
        System.out.println("--IP: " + ipAddress);
        System.out.println("--PortNumber: " + portNumber);
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new KoersenPuller(ipAddress, portNumber), 0, 1000);
    }
    
    // Main method
    public static void main(String[] args) {

        // Welcome message
        System.out.println("CLIENT USING REGISTRY");

        // Get ip address of server
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String ipAddressInput = input.nextLine();

        // Get port number
        System.out.print("Client: Enter port number: ");
        int portNumberInput = input.nextInt();

        // Create client
        BannerController bannerController = new BannerController(ipAddressInput, portNumberInput);
    }
}
