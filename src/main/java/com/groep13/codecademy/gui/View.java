/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 *
 * @author jelle
 * 
 * deze klasse is abstract, de cursistview, cursusview, certificaatview, inschrijvingview en topthreeview extenden deze klasse
 * deze klasses gebruiken de methodes in deze abstracte klasse
 * 
 */
public abstract class View {
    
    // geeft de titelbalk een naam en een icoon
    public void setTitle(Stage stage){
        stage.setTitle("Codecademy_ door: Nikki Stam 2145898, Milko Put 2210552, Jelle de Kok 2202704");
        stage.getIcons().add(new Image("https://seeklogo.com/images/C/codecademy-logo-2A19B928CF-seeklogo.com.png"));
    }
    
    // deze methode returnt een valide string
    public String[] toStringListCleaner(String string){
        StringBuilder input = new StringBuilder();
        input.append(string);
        input.deleteCharAt(0);
        input.deleteCharAt(input.length()-1);
        if(input.toString().isEmpty()){
            String[] empty = {};
            return empty;
        }
        String[] inputSplitter = input.toString().split(", ");
        return inputSplitter;
    }
    
    // deze methode returnt een stage 
    // wordt aangeroepen wanneer de gebruiker een methode wil aanroepen die een gegeven uit de tabel mee moet krijgen in 
    // cursistview, cursusview, certificaatview en inschrijvingview
    public Stage nothingSelected(){
        VBox errorBox = new VBox();
            Label message = new Label("Niks geselecteerd");
            errorBox.getChildren().addAll(message);
            Stage errorWindow = new Stage();
            VBox errorLayout = new VBox();             
            errorLayout.setPadding(new Insets(8,8,8,8));
            errorLayout.setMinHeight(300);
            errorLayout.setMinWidth(600);
            setTitle(errorWindow);
            //Return
            Button returnButton = new Button("Return");
            returnButton.setOnAction((e) -> {
                errorWindow.hide();
            });
            errorBox.getChildren().add(returnButton);
            errorLayout.getChildren().add(errorBox);
            Scene errorScene = new Scene(errorLayout);
            errorWindow.setScene(errorScene);
            return errorWindow;
    }
}
