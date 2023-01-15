/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.gui;

import com.groep13.codecademy.database.CursusDB;
import com.groep13.codecademy.database.ModuleDB;
import com.groep13.codecademy.database.StatistiekDB;
import com.groep13.codecademy.domain.Cursus;
import com.groep13.codecademy.domain.Niveau;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.groep13.codecademy.domain.Module;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author milko
 */
public class CursusView extends View{
    
    //aanmaken van alle nodige databases, een tabel met alle cursus gegevens, en een lijst met alle cursus objecten    
    private final CursusDB cursusdb;
    private final TableView cursusTable = new TableView();
    private final ObservableList<Cursus> cursus;   
    private final StatistiekDB sdb;
    private final ModuleDB mdb;

    // constructormethode van CursusView
    public CursusView(ObservableList<Cursus> cursus, CursusDB cdb, StatistiekDB sdb, ModuleDB mdb) {
        this.cursus=cursus;
        this.cursusdb = cdb;
        this.sdb = sdb;
        this.mdb = mdb;

        TableColumn naamColumn = new TableColumn("Naam");
        TableColumn owColumn = new TableColumn("Onderwerp");
        TableColumn introColumn = new TableColumn("Introductie Tekst");
        TableColumn niveauColumn = new TableColumn("Niveau Aanduiding");
        naamColumn.setCellValueFactory(new PropertyValueFactory<Cursus,String>("naam"));
        owColumn.setCellValueFactory(new PropertyValueFactory<Cursus,String>("onderwerp")); 
        introColumn.setCellValueFactory(new PropertyValueFactory<Cursus,String>("introductietekst"));
        niveauColumn.setCellValueFactory(new PropertyValueFactory<Cursus,String>("niveauaanduiding"));
        
        // vullen van de TableView tabel met gegevens
        cursusTable.setItems(cursus);
        cursusTable.getColumns().addAll(naamColumn,introColumn,owColumn,niveauColumn);
    }

    // methode die een nieuwe stage returnt naar de GUI klasse, wanneer de gebruiker op de cursussen knop drukt
    // de gebruiker krijgt deze stage te zien
    public Stage getScene() throws SQLException {
          
        Stage window = new Stage();
        setTitle(window);
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(900);
        
        layout.getChildren().add(cursusTable);
        
        // deze labels laten het percentage zien van het aantal cursisten dat de cursussen behaald heeft, gefilterd op geslacht
        Label percentageBehaaldMannen = new Label("Percentage voltooide cursussen mannen: " + sdb.percentageBehaaldeCursussenPerGeslacht("Man") + "%");
        Label percentageBehaaldVrouwen = new Label("Percentage voltooide cursussen vrouwen: " + sdb.percentageBehaaldeCursussenPerGeslacht("Vrouw") + "%");
        layout.getChildren().addAll(percentageBehaaldMannen, percentageBehaaldVrouwen);
        
        // als de gebruiker een cursus selecteert en op deze knop drukt wordt de methode amtCertStage aangeroepen
        // deze methode geeft een stage terug, deze krijgt de gebruiker te zien
        // op deze stage zijn het aantal behaalde certificaten en de aanbevolen cursussen voor de cursus te zien
        Button amtCert = new Button("Certificaten & Aanbevolen Cursussen");
        amtCert.setOnAction((e) -> {
            try{
                Stage amtCertWindow = amtCertStage(((Cursus) cursusTable.getSelectionModel().getSelectedItem()).getId());
                amtCertWindow.setWidth(500);
                amtCertWindow.setHeight(350);
                amtCertWindow.show();
            }catch(Exception ex){
                nothingSelected().show();
            }
        });
        layout.getChildren().add(amtCert);
        
        HBox cursusButtons = new HBox();
        
        //Error label
        Label errorLabel = new Label("");
               
        // CRUD buttons voor cursus
        
        // als de gebruiker op de create button drukt wordt de createCursist methode aangeroepen
        // deze methode geeft een stage terug en deze wordt getoond aan de gebruiker
        Button create = new Button("Create");
        create.setOnAction((e) -> {
            Stage createWindow = createCursus();
            createWindow.setWidth(500);
            createWindow.setHeight(350);
            createWindow.show();
        });
        cursusButtons.getChildren().addAll(create);
        
        // als de gebruiker een cursus selecteert uit de tabel en op de delete button drukt wordt de cursus uit de cursus database verwijderd
        Button delete = new Button("Delete");
        delete.setOnAction((e) -> {
            try{
            Cursus c = (Cursus) cursusTable.getSelectionModel().getSelectedItem();
            boolean deleted = cursusdb.deleteCursus(c);
            if (!deleted) {
                errorLabel.setText("Deletion failed, possible FK constraint");
            }   else    {
                errorLabel.setText("");
            }
            System.out.println(deleted);
            cursus.clear();
            cursus.addAll(cursusdb.getAllCursussen());
            }catch(Exception ex){
                nothingSelected().show();
            }
        });
        cursusButtons.getChildren().add(delete);
        
        // als de gebruiker een cursus selecteert uit de tabel en op de update button drukt wordt de editCursus methode aangeroepen
        // deze methode geeft een stage terug en deze wordt getoond aan de gebruiker
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            try{
            Stage updateWindow = editCursus((Cursus)cursusTable.getSelectionModel().getSelectedItem());
            updateWindow.setWidth(500);
            updateWindow.setHeight(350);
            updateWindow.show();
            }catch(Exception ex){
                nothingSelected().show(); // doesn't work?
            }
        });
        cursusButtons.getChildren().add(update);
        
        // als de gebruiker een cursus uit de tabel selecteert en op de add modules knop drukt wordt de addCursusModules methode aangeroepen
        // deze geeft een stage terug en deze wordt getoond aan de gebruiker
        Button addModules = new Button("Add Modules");
        addModules.setOnAction((e) -> {
            try{
            Stage addModulesWindow = addCursusModules((Cursus)cursusTable.getSelectionModel().getSelectedItem());
            addModulesWindow.setWidth(500);
            addModulesWindow.setHeight(350);
            addModulesWindow.show();
            }catch(Exception ex){
                nothingSelected().show(); // doesn't work?
            }
        });
        cursusButtons.getChildren().add(addModules);
        
        // als de gebruiker een cursus selecteert uit de tabel en op de delete modules knop drukt wordt de removeCursusModules methode aangeroepen
        // deze geeft een stage terug en deze wordt getoond aan de gebruiker
        Button removeModules = new Button("Delete Modules");
        removeModules.setOnAction((e) -> {
            try{
                Stage removeModulesWindow = removeCursusModules((Cursus)cursusTable.getSelectionModel().getSelectedItem());
                removeModulesWindow.setWidth(500);
                removeModulesWindow.setHeight(350);
                removeModulesWindow.show();
            }catch(Exception ex){
                nothingSelected().show();
            }
        });
        cursusButtons.getChildren().add(removeModules);
        
        // als de gebruiker een cursus uit de tabel selecteert en op de voortgang per module knop drukt wordt de gemiddeldeCursusVoortgang methode aangeroepen
        // deze geeft een stage terug en deze wordt getoond aan de gebruiker
        Button gemvoortgang = new Button("Voortgang per module");
        gemvoortgang.setOnAction((e) -> {
            try{
                Stage voortgangModulesWindow = gemiddeldeCursusVoortgang((Cursus)cursusTable.getSelectionModel().getSelectedItem());
                voortgangModulesWindow.setWidth(500);
                voortgangModulesWindow.setHeight(350);
                voortgangModulesWindow.show();
            }catch(Exception ex){
                nothingSelected().show();
            }
        });
        cursusButtons.getChildren().add(gemvoortgang);
        
        // return knop sluit de stage
        Button returnButton = new Button("Return");
        returnButton.setOnAction((e) -> {
            window.hide();
        });
        cursusButtons.getChildren().addAll(returnButton,errorLabel);           
              
        //Buttons 
        layout.setSpacing(5);
        layout.setPadding(new Insets(5,5,5,5));
        cursusButtons.setSpacing(5);
        layout.getChildren().add(cursusButtons);
                       
        Scene cursusScene = new Scene(layout);
        window.setScene(cursusScene);
        return window; 
    }
    
    // methode die alle cursus gegevens verwijderd en opnieuw de getAllCursussen methode aanroept in de cursus database
    public void refreshCursusTable() {
        cursus.clear();
        cursus.addAll(cursusdb.getAllCursussen());
    }
    
    // methode returnt een stage
    // in deze stage kan de gebruiker de gemiddelde voortgang voor elke module van een cursus zien
    public Stage gemiddeldeCursusVoortgang(Cursus c) {
        Stage window = new Stage();
        HBox layout = new HBox();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        setTitle(window);
        VBox v = new VBox();
        // gemiddeldeVoortgangPerModulePerCursus uit de statistieken database wordt aangeroepen
        HashMap<Integer,Double> voortgang = sdb.gemiddeldeVoortgangPerModulePerCursus(c);
        for (Map.Entry<Integer, Double> entry : voortgang.entrySet()) {  
            Label l = new Label(mdb.getModuleById(entry.getKey()).toString() + ": " + entry.getValue().toString() + "%");
            v.getChildren().add(l);
        }
        //Return
        Button returnButton = new Button("Return");
        returnButton.setOnAction((e) -> {
            window.hide();
        });
        v.getChildren().add(returnButton); 
        layout.getChildren().add(v);
        Scene editCursus = new Scene(layout);
        window.setScene(editCursus);
        return window;
    }
    
    // methode return een stage
    // in deze stage kan de gebruiker het aantal behaalde certificaten en de aanbevolen cursussen voor een cursus zien
    public Stage amtCertStage(int cursusId) {
        Stage window = new Stage();
        VBox layout = new VBox();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        setTitle(window);

        // methodes uit de statistiek database worden aangeroepen
        Label amtCertLabel = new Label("Certificaten: " + sdb.hoeveelCertificatenPerCursus(cursusId));
        Label aanbevolen = new Label("Aanbevolen cursussen:");
        layout.getChildren().addAll(amtCertLabel, aanbevolen);
        ArrayList<Cursus> aanbevolenCursus = sdb.aanbevolenCursussenBijCursus(cursusId);
        if (!(aanbevolenCursus.isEmpty())) {
            for (Cursus cursus : aanbevolenCursus) {
                Label cursusLabel = new Label(cursus.toString());
                layout.getChildren().add(cursusLabel);
            }
        }
        
        Scene amtCertScene = new Scene(layout);
        window.setScene(amtCertScene);
        return window;
    }
        
    // deze methode returnt een stage waarin de gebruiker input kan invullen en een nieuwe cursus kan aanmaken
    public Stage createCursus() {
        Stage window = new Stage();
        GridPane layout = new GridPane();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setHgap(10);
        layout.setVgap(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        setTitle(window);

        Label cursusLabel = new Label("Create Cursus:");
        layout.add(cursusLabel, 0, 0);
        Label naamLabel = new Label("Naam");
        layout.add(naamLabel, 0, 1);
        TextField naamField = new TextField("Naam");
        layout.add(naamField, 1, 1);
        Label owLabel = new Label("Onderwerp");
        layout.add(owLabel, 0, 2);
        TextField owField = new TextField("Onderwerp");
        layout.add(owField, 1, 2);
        Label introLabel = new Label("Introductie Tekst");
        layout.add(introLabel, 0, 3);
        TextField introField = new TextField("Introductie Tekst");
        layout.add(introField, 1, 3);
        Label niveauLabel = new Label("Niveau");
        layout.add(niveauLabel, 0, 4);
        ComboBox niveauField = new ComboBox();
        niveauField.getItems().addAll("Beginner","Gevorderd","Expert");
        layout.add(niveauField, 1, 4);
        Button create = new Button("Create");
        layout.add(create, 0, 5);

        // create button sluit de stage af, en maakt een nieuwe cursus in de cursus database aan met de gegevens uit de input velden
        create.setOnAction((e) -> {
            Cursus newC = new Cursus(
                0,
                naamField.getText(),
                owField.getText(),
                introField.getText(),
                Niveau.fromString((String)niveauField.getValue())
            );
            cursusdb.addCursus(newC);
            cursus.clear();
            cursus.addAll(cursusdb.getAllCursussen());
            window.hide();
        });  
        
        Scene createCursus = new Scene(layout);
        window.setScene(createCursus);
        return window;
    
    }
    
    // deze methode returnt een stage waarin de gebruiker modules kan toevoegen aan een cursus
    public Stage addCursusModules(Cursus c) {
        Stage window = new Stage();
        ScrollPane scroll = new ScrollPane();
        VBox layout = new VBox();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        setTitle(window);
        
        VBox pm = new VBox();
        Label possibleModules = new Label("Beschikbare modules voor de cursus " + c.getNaam() + ": ");
        ArrayList<Module> possibleModulesList = mdb.getAllModulesWithoutCursus();
        layout.getChildren().add(possibleModules);
        for (Module module: possibleModulesList) {
            HBox h = new HBox();
            Label l = new Label(module.toString());
            Button attach = new Button("Attach");
            attach.setOnAction((e) -> {
                mdb.moduleSetCursus(module.getId(), c.getId());
                pm.getChildren().removeAll(h);
            });           
            h.getChildren().addAll(l,attach);
            pm.getChildren().add(h);
        }      
        layout.getChildren().add(pm);
        
        scroll.setContent(layout);
        Scene editCursusModules = new Scene(scroll);
        window.setScene(editCursusModules);
        return window;
    }
    
    // deze methode returnt een stage waarin de gebruiker modules kan verwijderen uit een cursus
    public Stage removeCursusModules(Cursus c) {
        Stage window = new Stage();
        ScrollPane scroll = new ScrollPane();
        VBox layout = new VBox();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        setTitle(window);
        
        VBox cm = new VBox();
        Label courseModules = new Label("Modules in de cursus " + c.getNaam() + ": ");
        ArrayList<Module> courseModulesList = mdb.getCourseModules(c.getId());
        layout.getChildren().add(courseModules);
        for (Module module:courseModulesList) {
            HBox h = new HBox();
            Label l = new Label(module.toString());
            Button detach = new Button("Detach");
            detach.setOnAction((e) -> {
                mdb.moduleClearCursus(module.getId());
                cm.getChildren().removeAll(h);
            }); 
            h.getChildren().addAll(l,detach);
            cm.getChildren().add(h);
        }
        layout.getChildren().add(cm);
        
        scroll.setContent(layout);
        Scene editCursusModules = new Scene(scroll);
        window.setScene(editCursusModules);
        return window;
    }
    
    // methode returnt een stage waarop de gebruiker gegevens van een cursist kan wijzigen
    public Stage editCursus(Cursus c) {
        Stage window = new Stage();
        GridPane layout = new GridPane();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setHgap(10);
        layout.setVgap(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        setTitle(window);
        Label cursusLabel = new Label("Update Cursus:");
        layout.add(cursusLabel, 0, 0);
        Label naamLabel = new Label("Naam");
        layout.add(naamLabel, 0, 1);
        TextField naamField = new TextField(c.getNaam());
        layout.add(naamField, 1, 1);
        Label owLabel = new Label("Onderwerp");
        layout.add(owLabel, 0, 2);
        TextField owField = new TextField(c.getOnderwerp());
        layout.add(owField, 1, 2);
        Label introLabel = new Label("Introductie Tekst");
        layout.add(introLabel, 0, 3);
        TextField introField = new TextField(c.getIntroductietekst());
        layout.add(introField, 1, 3);
        Label niveauLabel = new Label("Niveau (" + c.getNiveauaanduiding().getNiveauNaam() + ")");
        layout.add(niveauLabel, 0, 4);
        ComboBox niveauField = new ComboBox();
        niveauField.getItems().addAll("Beginner","Gevorderd","Expert");
        layout.add(niveauField, 1, 4);
        Button update = new Button("Update");
        layout.add(update, 0, 5);
               
        // update button sluit de stage af, en maakt een nieuwe cursist aan en deze vervangt de oude cursist in de cursist database
        update.setOnAction((e) -> {
            Cursus newC = new Cursus(
                0,
                naamField.getText(),
                owField.getText(),
                introField.getText(),
                Niveau.fromString((String)niveauField.getValue())
            );
            cursusdb.updateCursus(c, newC);
            cursus.clear();
            cursus.addAll(cursusdb.getAllCursussen());
            window.hide();
        });
        Scene editCursus = new Scene(layout);
        window.setScene(editCursus);
        return window;

    }

}
