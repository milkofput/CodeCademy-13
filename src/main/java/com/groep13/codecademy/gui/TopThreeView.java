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
public class TopThreeView extends View{
    
    //aanmaken van de statistiek database
    private final StatistiekDB sdb;

    // constructor methode van TopThreeView
    public TopThreeView(StatistiekDB sdb) {
        this.sdb = sdb;
    }

    // methode die een nieuwe stage returnt naar de GUI klasse, wanneer de gebruiker op de top 3's knop drukt
    // de gebruiker krijgt deze stage te zien
    public Stage getScene() {
          
        Stage window = new Stage();
        setTitle(window);
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        HBox hbox = new HBox();
        HBox button = new HBox();
        VBox vboxWebcast = new VBox();
        VBox vboxCursus = new VBox();
        
        //Top 3 webcasts
        String[] inputSplitter = toStringListCleaner(sdb.topDrieWebcasts().toString());
        Label topThreeWebcasts = new Label("Top 3 webcasts: ");
        vboxWebcast.getChildren().addAll(topThreeWebcasts);
        for (int i = 0; i < inputSplitter.length; i++) {
            Label l = new Label( (i+1) + ". " + inputSplitter[i]);
            vboxWebcast.getChildren().addAll(l);
        }
        hbox.getChildren().addAll(vboxWebcast);
        
        
        //Top 3 cursus met meeste certificaat
        String[] inputSplitter1 = toStringListCleaner(sdb.topDrieCursussenMetMeesteCertificaten().toString());
        Label topThreeCursus = new Label("Top 3 cursussen met meeste certificaten: ");
        vboxCursus.getChildren().addAll(topThreeCursus);
        for (int i = 0; i < inputSplitter1.length; i++) {
            Label l = new Label( (i+1) + ". " + inputSplitter1[i]);
            vboxCursus.getChildren().addAll(l);
        }
        hbox.getChildren().addAll(vboxCursus);
             
        //Return
        Button returnButton = new Button("Return");
        returnButton.setOnAction((e) -> {
            window.hide();
        });
        button.getChildren().add(returnButton);           
              
        //Buttons 
        layout.setSpacing(5);
        layout.setPadding(new Insets(5,5,5,5));
        hbox.setSpacing(5);
        layout.getChildren().add(hbox);
        layout.getChildren().add(button);
                       
        Scene topThreeScene = new Scene(layout);
        window.setScene(topThreeScene);
        return window; 
    }
}
