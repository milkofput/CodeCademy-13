/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.gui;

import com.groep13.codecademy.database.CertificaatDB;
import com.groep13.codecademy.database.InschrijvingDB;
import com.groep13.codecademy.domain.Certificaat;
import com.groep13.codecademy.domain.Inschrijving;
import java.sql.SQLException;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author milko
 */
public class CertificaatView extends View{  
    
    private final CertificaatDB cerdb;
    private final TableView certificaatTable = new TableView();
    private final ObservableList<Certificaat> certificaat;
    private final ObservableList<Inschrijving> inschrijving;
    private final InschrijvingDB idb;

    public CertificaatView(CertificaatDB cerdb, ObservableList<Certificaat> certificaat, InschrijvingDB idb, ObservableList<Inschrijving> inschrijving) {
        this.cerdb=cerdb;
        this.certificaat=certificaat;
        this.idb=idb;
        this.inschrijving=inschrijving;
        TableColumn cijferColumn = new TableColumn("Cijfer");
        TableColumn medewerkerColumn = new TableColumn("Medewerker");
        TableColumn certificaatColumn = new TableColumn("CertificaatNummer");
        TableColumn inschrijvingColumn = new TableColumn("Inschrijving");
        cijferColumn.setCellValueFactory(new PropertyValueFactory<Certificaat,String>("cijfer"));
        medewerkerColumn.setCellValueFactory(new PropertyValueFactory<Certificaat,String>("medewerker"));
        certificaatColumn.setCellValueFactory(new PropertyValueFactory<Certificaat,String>("nummer")); 
        inschrijvingColumn.setCellValueFactory(new PropertyValueFactory<Certificaat,String>("inschrijving"));
        
        certificaatTable.setItems(certificaat);
        certificaatTable.getColumns().addAll(inschrijvingColumn, cijferColumn, medewerkerColumn, certificaatColumn);
    }

    public Stage getScene() throws SQLException {
          
        Stage window = new Stage();
        setTitle(window);
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        layout.getChildren().add(certificaatTable);
        HBox buttons = new HBox();
        
        //Error label
        Label errorLabel = new Label("");
               
        //Create
        Button create = new Button("Create");
        create.setOnAction((e) -> {
            Stage createWindow = createCertificaat();
            createWindow.setWidth(500);
            createWindow.setHeight(350);
            createWindow.show();
        });
        buttons.getChildren().addAll(create);
        
        //Delete  
        Button delete = new Button("Delete");
        delete.setOnAction((e) -> {
            try{
                Certificaat c = (Certificaat) certificaatTable.getSelectionModel().getSelectedItem();
                boolean deleted = cerdb.deleteCertificaat(c);
                if (!deleted) {
                    errorLabel.setText("Deletion failed, possible FK constraint");
                }   else    {
                    errorLabel.setText("");
                }
                certificaat.clear();
                certificaat.addAll(cerdb.getAllCertificaten());
            }catch(Exception ex){
                nothingSelected().show();
            }
        });
        buttons.getChildren().add(delete);
        
        //Update
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            try{
                Stage deleteWindow = editCertificaat((Certificaat)certificaatTable.getSelectionModel().getSelectedItem());
                deleteWindow.setWidth(500);
                deleteWindow.setHeight(350);
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
                       
        Scene certificaatScene = new Scene(layout);
        window.setScene(certificaatScene);
        return window; 
        
    }    
    
    public void refreshCertificaatTable() {
        certificaat.clear();
        certificaat.addAll(cerdb.getAllCertificaten());
    }
    
    public Stage createCertificaat() {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);
        setTitle(window);


        TextField cijferField = new TextField("Cijfer");
        TextField medewerkerField = new TextField("Naam Medewerker");
        TextField certificaatField = new TextField("CertificaatNummer");
        ComboBox inschrijvingField = new ComboBox();
        inschrijvingField.setItems(inschrijving);
        Button create = new Button("Create");

        create.setOnAction((e) -> {
            Certificaat newC = new Certificaat(
                0,
                Double.parseDouble(cijferField.getText()),
                medewerkerField.getText(),
                Integer.parseInt(certificaatField.getText()),
                (Inschrijving) inschrijvingField.getValue()
            );
            cerdb.addCertificaat(newC);
            certificaat.clear();
            certificaat.addAll(cerdb.getAllCertificaten());
            window.hide();
        });

        layout.getChildren().addAll(cijferField, medewerkerField, certificaatField, inschrijvingField ,create);

        Scene createCertificaat = new Scene(layout);
        window.setScene(createCertificaat);
        return window;
    
    }
    
    public Stage editCertificaat(Certificaat c) {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        setTitle(window);
        
        TextField cijferField = new TextField(String.valueOf(c.getCijfer()));
        TextField medewerkerField = new TextField(c.getMedewerker());
        TextField certificaatField = new TextField(String.valueOf(c.getNummer()));
        Label inschrijvingFieldLabel = new Label("Current value: " + c.getInschrijving().toString());
        ComboBox inschrijvingField = new ComboBox();
        inschrijvingField.setItems(inschrijving);
        Button update = new Button("Update");
       
        
        update.setOnAction((e) -> {
            Certificaat newC = new Certificaat(
                0,
                Double.parseDouble(cijferField.getText()),
                medewerkerField.getText(),
                Integer.parseInt(certificaatField.getText()),
                (Inschrijving) inschrijvingField.getValue()
            );
            cerdb.updateCertificaat(c, newC);
            certificaat.clear();
            certificaat.addAll(cerdb.getAllCertificaten());
            window.hide();
        });
        
        
        layout.getChildren().addAll(cijferField, medewerkerField, certificaatField, inschrijvingFieldLabel ,inschrijvingField,update);

        Scene editCertificaat = new Scene(layout);
        window.setScene(editCertificaat);
        return window;

    } 
}
