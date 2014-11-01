/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

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
    
    @Override
    public void start(Stage primaryStage) {
        
        //Button btn = new Button();
        //btn.setText("Say 'Hello World'");
        //btn.setOnAction(new EventHandler<ActionEvent>() {
        //    
        //    @Override
        //    public void handle(ActionEvent event) {
        //        System.out.println("Hello World!");
        //    }
        //});
        
        this.koersenString = "";
        this.lblKoersen = new Label();
        this.lblKoersen.setText(this.koersenString);
        this.continueMovingText = true;
        
        StackPane root = new StackPane();
        root.getChildren().add(lblKoersen);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setKoersen(String koersen)
    {
        this.koersenString = koersen;
        this.lblKoersen.setText(this.koersenString);
    }
    
    private void updateMovement()
    {
        //TODO moving text to be implemented
    }
}
