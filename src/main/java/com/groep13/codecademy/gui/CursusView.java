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
import javafx.collections.FXCollections;
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
    
        
    private final CursusDB cursusdb = new CursusDB();
    private final TableView cursusTable = new TableView();
    private final ObservableList<Cursus> cursus;   
    private final StatistiekDB sdb = new StatistiekDB();
    private final ModuleDB mdb = new ModuleDB();

    public CursusView() {       
        cursus = FXCollections.observableArrayList(cursusdb.getAllCursussen());
        TableColumn naamColumn = new TableColumn("Naam");
        TableColumn owColumn = new TableColumn("Onderwerp");
        TableColumn introColumn = new TableColumn("Introductie Tekst");
        TableColumn niveauColumn = new TableColumn("Niveau Aanduiding");
        naamColumn.setCellValueFactory(new PropertyValueFactory<Cursus,String>("naam"));
        owColumn.setCellValueFactory(new PropertyValueFactory<Cursus,String>("onderwerp")); 
        introColumn.setCellValueFactory(new PropertyValueFactory<Cursus,String>("introductietekst"));
        niveauColumn.setCellValueFactory(new PropertyValueFactory<Cursus,String>("niveauaanduiding"));
        
        cursusTable.setItems(cursus);
        cursusTable.getColumns().addAll(naamColumn,introColumn,owColumn,niveauColumn);
    }

    
    public Stage getScene() throws SQLException {
          
        Stage window = new Stage();
        setTitle(window);
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        layout.getChildren().add(cursusTable);
        
        Label percentageBehaaldMannen = new Label("Percentage voltooide cursussen mannen: " + sdb.percentageBehaaldeCursussenPerGeslacht("Man") + "%");
        Label percentageBehaaldVrouwen = new Label("Percentage voltooide cursussen vrouwen: " + sdb.percentageBehaaldeCursussenPerGeslacht("Vrouw") + "%");
        layout.getChildren().addAll(percentageBehaaldMannen, percentageBehaaldVrouwen);
        
        HBox cursusButtons = new HBox();
        
        //Error label
        Label errorLabel = new Label("");
               
        //Create
        Button create = new Button("Create");
        create.setOnAction((e) -> {
            Stage createWindow = createCursus();
            createWindow.setWidth(500);
            createWindow.setHeight(350);
            createWindow.show();
        });
        cursusButtons.getChildren().addAll(create);
        
        //Delete  
        Button delete = new Button("Delete");
        delete.setOnAction((e) -> {
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
        });
        cursusButtons.getChildren().add(delete);
        
        //Update
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            Stage updateWindow = editCursus((Cursus)cursusTable.getSelectionModel().getSelectedItem());
            updateWindow.setWidth(500);
            updateWindow.setHeight(350);
            updateWindow.show();
        });
        cursusButtons.getChildren().add(update);
        
        //add modules
        Button addModules = new Button("Add Modules");
        addModules.setOnAction((e) -> {
            Stage addModulesWindow = addCursusModules((Cursus)cursusTable.getSelectionModel().getSelectedItem());
            addModulesWindow.setWidth(500);
            addModulesWindow.setHeight(350);
            addModulesWindow.show();
        });
        cursusButtons.getChildren().add(addModules);
        
        //Update
        Button removeModules = new Button("Update Modules");
        removeModules.setOnAction((e) -> {
            Stage removeModulesWindow = removeCursusModules((Cursus)cursusTable.getSelectionModel().getSelectedItem());
            removeModulesWindow.setWidth(500);
            removeModulesWindow.setHeight(350);
            removeModulesWindow.show();
        });
        cursusButtons.getChildren().add(removeModules);
        
        //Update
        Button gemvoortgang = new Button("Voortgang per module");
        gemvoortgang.setOnAction((e) -> {
            Stage voortgangModulesWindow = gemiddeldeCursusVoortgang((Cursus)cursusTable.getSelectionModel().getSelectedItem());
            voortgangModulesWindow.setWidth(500);
            voortgangModulesWindow.setHeight(350);
            voortgangModulesWindow.show();
        });
        cursusButtons.getChildren().add(gemvoortgang);
        
        //Return
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
    
    public void refreshCursusTable() {
        cursus.clear();
        cursus.addAll(cursusdb.getAllCursussen());
    }
    
    public Stage gemiddeldeCursusVoortgang(Cursus c) {
        Stage window = new Stage();
        HBox layout = new HBox();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        HashMap<Integer,Double> voortgang = sdb.gemiddeldeVoortgangPerModulePerCursus(c);
        for (Map.Entry<Integer, Double> entry : voortgang.entrySet()) {
            HBox h = new HBox();
            Label module = new Label(mdb.getModuleById(entry.getKey()).toString());
            Label voortganglabel = new Label(entry.getValue().toString() + "%");
            h.getChildren().addAll(module,voortganglabel);
            layout.getChildren().add(h);
        }

        Scene editCursus = new Scene(layout);
        window.setScene(editCursus);
        return window;
    }
        
    public Stage createCursus() {
        Stage window = new Stage();
        GridPane layout = new GridPane();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setHgap(10);
        layout.setVgap(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);

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

        //layout.getChildren().addAll(naamField,owField,introField,niveauField,create);       
        

        Scene createCursus = new Scene(layout);
        window.setScene(createCursus);
        return window;
    
    }
    
    public Stage addCursusModules(Cursus c) {
        Stage window = new Stage();
        ScrollPane scroll = new ScrollPane();
        VBox layout = new VBox();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setMinHeight(300);
        layout.setMinWidth(600);

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
    
    public Stage removeCursusModules(Cursus c) {
        Stage window = new Stage();
        ScrollPane scroll = new ScrollPane();
        VBox layout = new VBox();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        
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
    
    public Stage editCursus(Cursus c) {
        Stage window = new Stage();
        GridPane layout = new GridPane();             
        layout.setPadding(new Insets(8,8,8,8));
        layout.setHgap(10);
        layout.setVgap(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);

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

        //layout.getChildren().addAll(naamField,owField,introField,niveauField,update);

        Scene editCursus = new Scene(layout);
        window.setScene(editCursus);
        return window;

    }

}
