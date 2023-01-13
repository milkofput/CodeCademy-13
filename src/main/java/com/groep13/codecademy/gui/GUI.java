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
 */
public class GUI extends Application {

    private final CertificaatDB certificaatdb = new CertificaatDB();
    private final InschrijvingDB inschrijvingdb = new InschrijvingDB();
    private final CursusDB cursusdb = new CursusDB();
    private final CursistDB cursistdb = new CursistDB();
    private final StatistiekDB statistiekdb = new StatistiekDB();
    private final ModuleDB moduledb = new ModuleDB();
    private final BekijktDB bekijktdb = new BekijktDB();
    private final WebcastDB webcastdb = new WebcastDB();

    private final ObservableList<Cursus> cursus = FXCollections.observableArrayList(cursusdb.getAllCursussen());
    private final ObservableList<Cursist> cursist = FXCollections.observableArrayList(cursistdb.getAllCursisten());
    private final ObservableList<Certificaat> certificaat = FXCollections.observableArrayList(certificaatdb.getAllCertificaten());;
    private final ObservableList<Inschrijving> inschrijving = FXCollections.observableArrayList(inschrijvingdb.getAllInschrijvingen());;

    private final TopThreeView topThreeView = new TopThreeView(statistiekdb);
    private final CertificaatView certificaatView = new CertificaatView(certificaatdb,certificaat,inschrijvingdb,inschrijving);
    private final InschrijvingView inschrijvingView = new InschrijvingView(inschrijvingdb,inschrijving,cursusdb,cursistdb,cursus,cursist);
    private final CursusView cursusView = new CursusView(cursus,cursusdb,statistiekdb,moduledb);
    private final CursistView cursistView = new CursistView(cursistdb,cursusdb,cursist,moduledb,bekijktdb,webcastdb,statistiekdb);

   
    @Override
    public void start(Stage stage) throws SQLException {
        //title bar
        setTitle(stage);
        
        BorderPane mainLayout = new BorderPane();       
        mainLayout.setMinHeight(300);
        mainLayout.setMinWidth(650);
        
        Label firstLabel = new Label("Main menu");
        Button cursistButton = new Button("Cursisten");
        Button cursusButton = new Button("Cursussen");
        Button inschrijvingButton = new Button("Inschrijvingen");
        Button certificaatButton = new Button("Certificaten");
        Button topThreeButton = new Button("Top 3's");
        
        HBox nav = new HBox();
        nav.getChildren().addAll(cursistButton, cursusButton, inschrijvingButton, certificaatButton, topThreeButton);
        nav.setAlignment(Pos.CENTER);
              
        mainLayout.setTop(firstLabel);
        mainLayout.setCenter(nav);
               
        // BUTTON ACTIONS
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
        
        topThreeButton.setOnAction((e) -> {
            Stage window;
            window = topThreeView.getScene();
            window.show();        
        });

        
        Scene mainScene = new Scene(mainLayout);
        
        File f = new File("style.css");
        // tried this but does not really work:
//        mainScene.getStylesheets().clear();
//        mainScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));        
        stage.setScene(mainScene);
        stage.show();

    }
    
    public void setTitle(Stage stage){
        stage.setTitle("Codecademy_ door: Nikki Stam 2145898, Milko Put 2210552, Jelle de Kok 2202704");
        stage.getIcons().add(new Image("https://seeklogo.com/images/C/codecademy-logo-2A19B928CF-seeklogo.com.png"));
    }
    
}

