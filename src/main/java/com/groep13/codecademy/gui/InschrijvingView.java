/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.gui;

import com.groep13.codecademy.database.CursistDB;
import com.groep13.codecademy.database.CursusDB;
import com.groep13.codecademy.database.InschrijvingDB;
import com.groep13.codecademy.domain.Cursist;
import com.groep13.codecademy.domain.Cursus;
import com.groep13.codecademy.domain.Inschrijving;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author milko
 */
public class InschrijvingView extends View{
      
    private final InschrijvingDB idb;
    private final TableView inschrijvingTable = new TableView();
    private final ObservableList<Inschrijving> inschrijving;
    private final ObservableList<Cursus> cursus;
    private final ObservableList<Cursist> cursist;
    private final CursusDB cdb;
    private final CursistDB cursistdb;

    public InschrijvingView(InschrijvingDB idb, ObservableList<Inschrijving> inschrijving, CursusDB cdb, CursistDB cursistdb, ObservableList<Cursus> cursus, ObservableList<Cursist> cursist) {
        this.idb=idb;
        this.inschrijving=inschrijving;
        this.cdb=cdb;
        this.cursistdb=cursistdb;
        this.cursus=cursus;
        this.cursist=cursist;
        TableColumn cursusColumn = new TableColumn("CursusID");
        TableColumn cursistColumn = new TableColumn("CursistID");
        TableColumn datumColumn = new TableColumn("Datum");
        cursusColumn.setCellValueFactory(new PropertyValueFactory<Inschrijving,String>("Cursus"));
        cursistColumn.setCellValueFactory(new PropertyValueFactory<Inschrijving,String>("Cursist"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<Inschrijving,String>("Datum")); 
        
        inschrijvingTable.setItems(inschrijving);
        inschrijvingTable.getColumns().addAll(cursusColumn, cursistColumn, datumColumn);
    }
        
    public Stage getScene() throws SQLException {
          
        Stage window = new Stage();
        setTitle(window);
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        layout.getChildren().add(inschrijvingTable);
        HBox buttons = new HBox();
        
        //Error label
        Label errorLabel = new Label("");
               
        //Create
        Button create = new Button("Create");
        create.setOnAction((e) -> {
            Stage createWindow = createInschrijving();
            createWindow.setWidth(700);
            createWindow.setHeight(200);
            createWindow.show();
        });
        buttons.getChildren().addAll(create);
        
        //Delete  
        Button delete = new Button("Delete");
        delete.setOnAction((e) -> {
            try{
            Inschrijving c = (Inschrijving) inschrijvingTable.getSelectionModel().getSelectedItem();
            boolean deleted = idb.deleteInschrijving(c);
            if (!deleted) {
                errorLabel.setText("Deletion failed, possible FK constraint");
            }   else    {
                errorLabel.setText("");
            }
            inschrijving.clear();
            inschrijving.addAll(idb.getAllInschrijvingen());
            }catch(Exception ex){
                nothingSelected().show();
            }
        });
        buttons.getChildren().add(delete);
        
        //Update
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            try{
            Stage deleteWindow = editInschrijving((Inschrijving)inschrijvingTable.getSelectionModel().getSelectedItem());
            deleteWindow.setWidth(750);
            deleteWindow.setHeight(200);
            deleteWindow.show();
            }catch(Exception ex){
                nothingSelected().show();
            }
        });
        buttons.getChildren().add(update);
        
        //Return
        Button returnButton = new Button("Return");
        returnButton.setOnAction((e) -> {
            window.hide();
        });
        buttons.getChildren().add(returnButton);           
              
        //Buttons 
        layout.setSpacing(5);
        layout.setPadding(new Insets(5,5,5,5));
        buttons.setSpacing(5);
        layout.getChildren().add(buttons);
                       
        Scene inschrijvingScene = new Scene(layout);
        window.setScene(inschrijvingScene);
        return window; 
    } 
    
    public void refreshInschrijvingTable() {
        inschrijving.clear();
        inschrijving.addAll(idb.getAllInschrijvingen());
    }
    
    public Stage createInschrijving() {
        Stage window = new Stage();
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setHgap(10);
        layout.setVgap(5);
        setTitle(window);

        // dit doet volgens mij niks:
        //layout.setMinHeight(300);
        //layout.setMinWidth(900);

        Label inschrijvingLabel = new Label("Create Inschrijving:");
        layout.add(inschrijvingLabel, 0, 0);
        
        Label cursusLabel = new Label("Cursus:");
        layout.add(cursusLabel, 0, 1);
        ComboBox cursusField = new ComboBox();
        cursusField.setItems(cursus);
        layout.add(cursusField, 1, 1);
        
        Label cursistLabel = new Label("Cursist:");
        layout.add(cursistLabel, 0, 2);
        ComboBox cursistField = new ComboBox();
        cursistField.setItems(cursist);
        layout.add(cursistField, 1,2 );
        
        Label datumLabel = new Label("Datum (jjjj-mm-dd)");
        layout.add(datumLabel, 0, 3);
        TextField jaarField = new TextField("Jaar");
        layout.add(jaarField, 1,3);
        TextField maandField = new TextField("Maand");
        layout.add(maandField,2,3);
        TextField dagField = new TextField("Dag");
        layout.add(dagField,3,3);

        Button create = new Button("Create");
        layout.add(create, 0,4);

        create.setOnAction((e) -> {
            Inschrijving newC = new Inschrijving(
                0,
                (Cursus) cursusField.getValue(),
                (Cursist) cursistField.getValue(),
                LocalDate.of(Integer.parseInt(jaarField.getText()),Integer.parseInt(maandField.getText()),Integer.parseInt(dagField.getText()))
            );
            idb.addInschrijving(newC);
            inschrijving.clear();
            inschrijving.addAll(idb.getAllInschrijvingen());
            window.hide();
        });

        Scene createInschrijving = new Scene(layout);
        window.setScene(createInschrijving);
        return window;
    
    }
    
    public Stage editInschrijving(Inschrijving i) {
        Stage window = new Stage();
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setHgap(10);
        layout.setVgap(5);
        setTitle(window);
       
//        layout.setMinHeight(200);
//        layout.setMinWidth(600);

        
        
        Label inschrijvingLabel = new Label("Create Inschrijving:");
        layout.add(inschrijvingLabel, 0, 0);
        
        Label cursusLabel = new Label("Cursus (" + i.getCursus().toString() + "):");
        layout.add(cursusLabel, 0, 1);
        ComboBox cursusField = new ComboBox();
        cursusField.setItems(cursus);
        layout.add(cursusField, 1, 1);
        
        Label cursistLabel = new Label("Cursist ("+ i.getCursist().toString() + "):");
        layout.add(cursistLabel, 0, 2);
        ComboBox cursistField = new ComboBox();
        cursistField.setItems(cursist);
        layout.add(cursistField, 1,2 );
        
        Label datumLabel = new Label("Datum (" + i.getDatum().getYear() + "-" + i.getDatum().getMonthValue() + "-" + i.getDatum().getDayOfMonth() + ")");
        layout.add(datumLabel, 0, 3);
        TextField jaarField = new TextField("Jaar");
        layout.add(jaarField, 1,3);
        TextField maandField = new TextField("Maand");
        layout.add(maandField,2,3);
        TextField dagField = new TextField("Dag");
        layout.add(dagField,3,3);

        Button update = new Button("Update");
        layout.add(update, 0,4);
        
        
        update.setOnAction((e) -> {
            Inschrijving newC = new Inschrijving(
                0,
                //Integer.parseInt(cursusField.getText()),
                (Cursus) cursusField.getValue(),
                (Cursist) cursistField.getValue(),
                LocalDate.of(Integer.parseInt(jaarField.getText()),Integer.parseInt(maandField.getText()),Integer.parseInt(dagField.getText()))
            );
            idb.updateInschrijving(i, newC);
            inschrijving.clear();
            inschrijving.addAll(idb.getAllInschrijvingen());
            window.hide();
        });
        

        Scene editCursist = new Scene(layout);
        window.setScene(editCursist);
        return window;

    }
}
