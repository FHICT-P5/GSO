/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

import com.sun.javafx.tk.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Julius
 */
public class AEXBanner extends Application {
    
    String koersenString;
    Label lblKoersen;
    private boolean continueMovingText;
    int index = 0;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
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
        BannerController bannerController = new BannerController(ipAddressInput, portNumberInput, this);
        
        this.koersenString = "";
        this.lblKoersen = new Label();
        
        lblKoersen.setFont(new javafx.scene.text.Font(100));
        this.lblKoersen.setText(this.koersenString);
        this.lblKoersen.setText("Hallo");
        this.continueMovingText = true;
        
        StackPane root = new StackPane();
        root.getChildren().add(lblKoersen);
        
        Scene scene;
        scene = new Scene(root, 900, 85);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("AEX Banner");
        primaryStage.show();
        
        return;
    }

    public void setKoersen(String koersen)
    {
        this.koersenString = koersen;
        if(this.lblKoersen != null)
        {
            showText();
        }
    }
    
    private void updateMovement()
    {
        //TODO moving text to be implemented
    }
    
    public static void main(String[] args) 
    {
        launch(args);    
    }
    
    public void showText()
    {
        String koersenTotal = "";
        index++;
        if(index > koersenString.length() - 1)
            index = 1;
        int endIndex = index + 20;
        if(endIndex > koersenString.length() - 1)
            endIndex = koersenString.length() - 1;
            
        
        String koersenPart = this.koersenString.substring(index, endIndex);
        koersenTotal += koersenPart;
        
        int rest = index + 20 - endIndex;
        while(rest > 0)
        {
            int newIndex = 0;
            endIndex = newIndex + rest;
            if(endIndex > koersenString.length() - 1)
                endIndex = koersenString.length() - 1;
            
            rest = rest - endIndex;
            koersenPart = koersenString.substring(newIndex, endIndex);
            koersenTotal += koersenPart;
        }
        
        this.lblKoersen.setText(koersenTotal);
    }
}
