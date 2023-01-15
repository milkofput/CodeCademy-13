/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author jelle
 */
public abstract class View {
    public void setTitle(Stage stage){
        stage.setTitle("Codecademy_ door: Nikki Stam 2145898, Milko Put 2210552, Jelle de Kok 2202704");
        stage.getIcons().add(new Image("https://seeklogo.com/images/C/codecademy-logo-2A19B928CF-seeklogo.com.png"));
    }
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
}
