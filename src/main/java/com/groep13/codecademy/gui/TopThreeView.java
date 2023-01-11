/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.gui;

import com.groep13.codecademy.database.StatistiekDB;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author milko
 */
public class TopThreeView {
    
    private final StatistiekDB sdb = new StatistiekDB();
    
    public Stage getScene() {
          
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        HBox hbox = new HBox();
        
        //Top 3 webcasts
        Label topThreeWebcasts = new Label("Top 3 webcasts: " + sdb.topDrieWebcasts());
        layout.getChildren().addAll(topThreeWebcasts);
        
        //Top 3 cursus met meeste certificaat
        Label topThreeCursus = new Label("Top 3 cursussen met meeste certificaten: " + sdb.topDrieCursussenMetMeesteCertificaten());
        layout.getChildren().add(topThreeCursus);
             
        //Return
        Button returnButton = new Button("Return");
        returnButton.setOnAction((e) -> {
            window.hide();
        });
        hbox.getChildren().add(returnButton);           
              
        //Buttons 
        layout.setSpacing(5);
        layout.setPadding(new Insets(5,5,5,5));
        hbox.setSpacing(5);
        layout.getChildren().add(hbox);
                       
        Scene topThreeScene = new Scene(layout);
        window.setScene(topThreeScene);
        return window; 
    }
}
