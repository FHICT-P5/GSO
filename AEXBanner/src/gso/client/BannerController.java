/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

import gso.Task.KoersenPuller;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 *
 * @author Julius op den Brouw
 */
public class BannerController {
    
    private AEXBanner banner;
    private Timer timer;
    
    public BannerController(String ipAddress, int portNumber, AEXBanner banner)
    {
        System.out.println("BannerController");
        this.banner = banner;
        
        System.out.println("Connection succesful");
        System.out.println("--IP: " + ipAddress);
        System.out.println("--PortNumber: " + portNumber);
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new KoersenPuller(this, ipAddress, portNumber), 0, 1000);
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
}
