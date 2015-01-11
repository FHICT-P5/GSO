/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.centrale;

import bank.server.*;
import bank.bankieren.Bank;
import bank.bankieren.CentraleBank;
import bank.gui.BankierClient;
import bank.internettoegang.Balie;
import bank.internettoegang.IBalie;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Naming;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author frankcoenen
 */
public class CentraleBankServer extends Application {

    private CentraleBank centraleBank;
    private BalieServerCustom balieServer;
    private BalieServerCustom balieServer2;
    private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 600.0;
    private final double MINIMUM_WINDOW_HEIGHT = 200.0;
    private String nameCentraleBank;

    @Override
    public void start(Stage primaryStage) throws IOException {

        centraleBank = new CentraleBank();
        
//        balieServer = new BalieServer();
//        balieServer.addCentraleBank(centraleBank);
//        balieServer.start(new Stage());
//        
//        balieServer2 = new BalieServer();
//        balieServer2.addCentraleBank(centraleBank);
//        balieServer2.start(new Stage());
        
        SetupRMI();
        
        balieServer = new BalieServerCustom();
        balieServer.addCentraleBank(centraleBank);
        balieServer.start(new Stage());
        
        balieServer2 = new BalieServerCustom();
        balieServer2.addCentraleBank(centraleBank);
        balieServer2.start(new Stage());
        
        
    }
    
    private void SetupRMI()
    {
        FileOutputStream out = null;
        try {
            String address = java.net.InetAddress.getLocalHost().getHostAddress();
            int port = 1099;
            Properties props = new Properties();
            nameCentraleBank = "cb";
            String rmiCentraleBank = address + ":" + port + "/" + nameCentraleBank;
            props.setProperty("cb", rmiCentraleBank);
            out = new FileOutputStream(nameCentraleBank + ".props");
            props.store(out, null);
            out.close();
            java.rmi.registry.LocateRegistry.createRegistry(port);

            Naming.rebind(nameCentraleBank, centraleBank);


        } catch (IOException ex) {
            Logger.getLogger(BalieServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(BalieServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
