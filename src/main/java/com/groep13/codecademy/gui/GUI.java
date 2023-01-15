/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.gui;

import com.groep13.codecademy.database.*;
import com.groep13.codecademy.domain.Certificaat;
import com.groep13.codecademy.domain.Cursist;
import com.groep13.codecademy.domain.Cursus;
import com.groep13.codecademy.domain.Inschrijving;
import java.io.File;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author milko
 * 
 * Deze klasse wordt vanuit de Main klasse aangeroepen, en vormt het hoofdscherm dat de gebruiker als eerst te zien krijgt
 * 
 */
public class GUI extends Application {
    
    // aanmaken van alle nodige databases
    private final CertificaatDB certificaatdb = new CertificaatDB();
    private final InschrijvingDB inschrijvingdb = new InschrijvingDB();
    private final CursusDB cursusdb = new CursusDB();
    private final CursistDB cursistdb = new CursistDB();
    private final StatistiekDB statistiekdb = new StatistiekDB();
    private final ModuleDB moduledb = new ModuleDB();
    private final BekijktDB bekijktdb = new BekijktDB();
    private final WebcastDB webcastdb = new WebcastDB();

    // aanmaken van de lijsten waarin alle cursussen, cursisten, certificaten en inschrijvingen staan
    private final ObservableList<Cursus> cursus = FXCollections.observableArrayList(cursusdb.getAllCursussen());
    private final ObservableList<Cursist> cursist = FXCollections.observableArrayList(cursistdb.getAllCursisten());
    private final ObservableList<Certificaat> certificaat = FXCollections.observableArrayList(certificaatdb.getAllCertificaten());;
    private final ObservableList<Inschrijving> inschrijving = FXCollections.observableArrayList(inschrijvingdb.getAllInschrijvingen());;

    // aanmaken van alle nodige views
    private final TopThreeView topThreeView = new TopThreeView(statistiekdb);
    private final CertificaatView certificaatView = new CertificaatView(certificaatdb,certificaat,inschrijvingdb,inschrijving);
    private final InschrijvingView inschrijvingView = new InschrijvingView(inschrijvingdb,inschrijving,cursusdb,cursistdb,cursus,cursist);
    private final CursusView cursusView = new CursusView(cursus,cursusdb,statistiekdb,moduledb);
    private final CursistView cursistView = new CursistView(cursistdb,cursusdb,cursist,moduledb,bekijktdb,webcastdb,statistiekdb);

    // methode die aangeroepen wordt wanneer vanuit main de GUI klasse aangeroepen wordt, deze methode maakt het hoofdscherm van de applicatie
    @Override
    public void start(Stage stage) throws SQLException {
        
        // aanroep methode om titelbalk vorm te geven
        setTitle(stage);
        
        // er wordt een layout aangemaakt
        BorderPane mainLayout = new BorderPane();       
        mainLayout.setMinHeight(300);
        mainLayout.setMinWidth(650);
        
        // de navigatie buttons worden aangemaakt
        Button cursistButton = new Button("Cursisten");
        Button cursusButton = new Button("Cursussen");
        Button inschrijvingButton = new Button("Inschrijvingen");
        Button certificaatButton = new Button("Certificaten");
        Button topThreeButton = new Button("Top 3's");
        
        // de navigatie buttons worden in een horizontale box geplaatst
        HBox nav = new HBox();
        nav.getChildren().addAll(cursistButton, cursusButton, inschrijvingButton, certificaatButton, topThreeButton);
        nav.setAlignment(Pos.CENTER);
              
        // buttons worden toegevoegd aan de layout
        mainLayout.setCenter(nav);
               
        // acties van de buttons:
        
        // actie van de Cursisten button: er wordt een CursistView object gemaakt, de getScene methode van CursistView wordt aangeroepen,
        // deze geeft een stage terug, en deze wordt vervolgens getoont, 
        // waardoor de gebruiker een nieuw scherm te zien krijgt met de cursist gegevens
        cursistButton.setOnAction((e) -> {
            Stage window;
            try {
                cursistView.refreshCursistTable();
                window = cursistView.getScene();
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }          
        });
        
        // actie van de Cursussen button: er wordt een CursusView object gemaakt, en de getScene methode van CursusView wordt aangeroepen,
        // deze geeft een stage terug, en deze wordt vervolgens getoont, 
        // waardoor de gebruiker een nieuw scherm te zien krijgt met de cursus gegevens
        cursusButton.setOnAction((e) -> {
            Stage window;
            try {
                cursusView.refreshCursusTable();
                window = cursusView.getScene();
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }          
        });
        
        // actie van de Inschrijvingen button: er wordt een InschrijvingView object gemaakt, 
        // en de getScene methode van InschrijvingView wordt aangeroepen,
        // deze geeft een stage terug, en deze wordt vervolgens getoont, 
        // waardoor de gebruiker een nieuw scherm te zien krijgt met de gegevens van de inschrijvingen
        inschrijvingButton.setOnAction((e) -> {
            Stage window;
            try {
                inschrijvingView.refreshInschrijvingTable();
                window = inschrijvingView.getScene();
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }          
        });
        
        // actie van de Certificaten button: er wordt een CertificaatView object gemaakt, 
        // en de getScene methode van CertificaatView wordt aangeroepen,
        // deze geeft een stage terug, en deze wordt vervolgens getoont, 
        // waardoor de gebruiker een nieuw scherm te zien krijgt met de certificaat gegevens
        certificaatButton.setOnAction((e) -> {
            Stage window;
            try {
                certificaatView.refreshCertificaatTable();
                window = certificaatView.getScene();
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }          
        });
        
        // actie van de Top 3's button: er wordt een TopThreeView object gemaakt,
        // en de getScene methode van TopThreeView wordt aangroepen,
        // deze geeft een stage terug, en deze wordt vervolgens getoont, 
        // waardoor de gebruiker een nieuw scherm te zien krijg met de top 3 gegevens
        topThreeButton.setOnAction((e) -> {
            Stage window;
            window = topThreeView.getScene();
            window.show();        
        });

        // de layout wordt geplaatst in een scene, de scene wordt geplaatst in een stage, en deze stage wordt getoond
        Scene mainScene = new Scene(mainLayout);    
        stage.setScene(mainScene);
        stage.show();

    }
    
    // methode die de titelbalk vormt, wordt aangeroepen vanuit start
    public void setTitle(Stage stage){
        // stage krijgt een titel en een icoontje in de titelbalk
        stage.setTitle("Codecademy_ door: Nikki Stam 2145898, Milko Put 2210552, Jelle de Kok 2202704");
        stage.getIcons().add(new Image("https://seeklogo.com/images/C/codecademy-logo-2A19B928CF-seeklogo.com.png"));
    }
    
}

