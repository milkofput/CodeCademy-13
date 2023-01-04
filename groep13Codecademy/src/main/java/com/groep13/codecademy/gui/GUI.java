/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.gui;

import com.groep13.codecademy.database.CertificaatDB;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.groep13.codecademy.database.CursistDB;
import com.groep13.codecademy.database.CursusDB;
import com.groep13.codecademy.database.InschrijvingDB;
import com.groep13.codecademy.domain.Certificaat;
import com.groep13.codecademy.domain.Cursist;
import com.groep13.codecademy.domain.Cursus;
import com.groep13.codecademy.domain.Geslacht;
import com.groep13.codecademy.domain.Inschrijving;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author milko
 */
public class GUI extends Application {
    
    private static CursistDB cdb = new CursistDB();
    private static TableView cursistTable = new TableView();
    private static ObservableList<Cursist> cursist;
    
    private static CursusDB cursusdb = new CursusDB();
    private static TableView cursusTable = new TableView();
    private static ObservableList<Cursus> cursus;
    
    private static InschrijvingDB idb = new InschrijvingDB();
    private static TableView inschrijvingTable = new TableView();
    private static ObservableList<Inschrijving> inschrijving;
    
    private static CertificaatDB cerdb = new CertificaatDB();
    private static TableView certificaatTable = new TableView();
    private static ObservableList<Certificaat> certificaat;
   
    @Override
    public void start(Stage stage) throws SQLException {
        
        initCursistTable();
        initCursusTable();
        initInschrijvingTable();
        initCertificaatTable();
        
        BorderPane mainLayout = new BorderPane();       
        mainLayout.setMinHeight(300);
        mainLayout.setMinWidth(600);
        
        Label firstLabel = new Label("label");
        Button cursistButton = new Button("Cursisten");
        Button cursusButton = new Button("Cursussen");
        Button inschrijvingButton = new Button("Inschrijvingen");
        Button certificaatButton = new Button("Certificaten");
        
        HBox nav = new HBox();
        nav.getChildren().addAll(cursistButton, cursusButton, inschrijvingButton, certificaatButton);
        nav.setAlignment(Pos.CENTER);
              
        mainLayout.setTop(firstLabel);
        mainLayout.setCenter(nav);
               
        // BUTTON ACTIONS
        cursistButton.setOnAction((e) -> {
            Stage window;
            try {
                window = cursistScene();
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }          
        });
        
        cursusButton.setOnAction((e) -> {
            Stage window;
            try {
                window = cursusScene();
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }          
        });
        
        inschrijvingButton.setOnAction((e) -> {
            Stage window;
            try {
                window = inschrijvingScene();
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }          
        });
        
        certificaatButton.setOnAction((e) -> {
            Stage window;
            try {
                window = certificaatScene();
                window.show();
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }          
        });

        
        Scene mainScene = new Scene(mainLayout);
        stage.setScene(mainScene);
        stage.show();

    }
       

    // CURSISTEN -----------------------------------------------------------------------------
    
    private Stage cursistScene() throws SQLException {
          
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        //initCursistTable();
        layout.getChildren().add(cursistTable);
        HBox cursistButtons = new HBox();
               
        //Create
        Button create = new Button("Create");
        create.setOnAction((e) -> {
            Stage createWindow = createCursist();
            createWindow.setWidth(500);
            createWindow.setHeight(350);
            createWindow.show();
        });
        cursistButtons.getChildren().addAll(create);
        
        //Delete  
        Button delete = new Button("Delete");
        delete.setOnAction((e) -> {
            Cursist c = (Cursist) cursistTable.getSelectionModel().getSelectedItem();
            cdb.deleteCursist(c);
            cursist.clear();
            cursist.addAll(cdb.getAllCursisten());
        });
        cursistButtons.getChildren().add(delete);
        
        //Update
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            Stage deleteWindow = editCursist((Cursist)cursistTable.getSelectionModel().getSelectedItem());
            deleteWindow.setWidth(500);
            deleteWindow.setHeight(350);
            deleteWindow.show();
        });
        cursistButtons.getChildren().add(update);
        
        //Return
        Button returnButton = new Button("Return");
        returnButton.setOnAction((e) -> {
            window.hide();
        });
        cursistButtons.getChildren().add(returnButton);           
              
        //Buttons 
        layout.setSpacing(5);
        layout.setPadding(new Insets(5,5,5,5));
        cursistButtons.setSpacing(5);
        layout.getChildren().add(cursistButtons);
                       
        Scene cursistScene = new Scene(layout);
        window.setScene(cursistScene);
        return window; 
    }
       
    private void initCursistTable() throws SQLException {
               
        cursist = FXCollections.observableArrayList(cdb.getAllCursisten());
        TableColumn emailColumn = new TableColumn("EmailAdres");
        TableColumn naamColumn = new TableColumn("Naam");
        TableColumn datumColumn = new TableColumn("GeboorteDatum");
        TableColumn geslachtColumn = new TableColumn("Geslacht");
        TableColumn straatColumn = new TableColumn("Straat");
        TableColumn huisnummerColumn = new TableColumn("Huisnummer");
        TableColumn postcodeColumn = new TableColumn("Postcode");
        TableColumn woonplaatsColumn = new TableColumn("Woonplaats");
        TableColumn landColumn = new TableColumn("Land");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("emailAdres"));
        naamColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("naam"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("geboortedatum")); 
        geslachtColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("geslacht"));
        straatColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("straat"));
        huisnummerColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("huisnummer"));
        postcodeColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("postcode"));
        woonplaatsColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("woonplaats"));
        landColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("land"));
        
        cursistTable.setItems(cursist);
        cursistTable.getColumns().addAll(emailColumn,naamColumn,datumColumn,geslachtColumn,straatColumn,huisnummerColumn,postcodeColumn,woonplaatsColumn,landColumn);
    } 
    
    private Stage createCursist() {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);

        TextField emailField = new TextField("Email");
        TextField naamField = new TextField("Naam");
        TextField jaarField = new TextField("Geboortejaar");
        TextField maandField = new TextField("Geboortemaand");
        TextField dagField = new TextField("Geboortedag");
        TextField geslachtField = new TextField("Geslacht");
        TextField straatField = new TextField("Straat");
        TextField huisnrField = new TextField("Huisnummer");
        TextField postcodeField = new TextField("Postcode");
        TextField woonplaatsField = new TextField("Woonplaats");
        TextField landField = new TextField("Land");
        Button create = new Button("Create");

        create.setOnAction((e) -> {
            Cursist newC = new Cursist(
                0,
                emailField.getText(),
                naamField.getText(),
                LocalDate.of(Integer.parseInt(jaarField.getText()),Integer.parseInt(maandField.getText()),Integer.parseInt(dagField.getText())),
                Geslacht.fromString(geslachtField.getText()),
                straatField.getText(),
                huisnrField.getText(),
                postcodeField.getText(),
                woonplaatsField.getText(),
                landField.getText()
            );
            cdb.addCursist(newC);
            cursist.clear();
            cursist.addAll(cdb.getAllCursisten());
            window.hide();
        });

        layout.getChildren().addAll(emailField,naamField,jaarField,maandField,dagField,geslachtField,straatField,huisnrField,postcodeField,woonplaatsField,landField,create);

        Scene createCursist = new Scene(layout);
        window.setScene(createCursist);
        return window;
    
    }
    
    private Stage editCursist(Cursist c) {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);

        TextField emailField = new TextField(c.getEmailAdres());
        TextField naamField = new TextField(c.getNaam());
        TextField jaarField = new TextField(String.valueOf(c.getGeboortedatum().getYear()));
        TextField maandField = new TextField(String.valueOf(c.getGeboortedatum().getMonthValue()));
        TextField dagField = new TextField(String.valueOf(c.getGeboortedatum().getDayOfMonth()));
        TextField geslachtField = new TextField(c.getGeslacht().getGeslachtNaam());
        TextField straatField = new TextField(c.getStraat());
        TextField huisnrField = new TextField(c.getHuisnr());
        TextField postcodeField = new TextField(c.getPostcode());
        TextField woonplaatsField = new TextField(c.getWoonplaats());
        TextField landField = new TextField(c.getLand());
        Button update = new Button("Update");

        update.setOnAction((e) -> {
            Cursist newC = new Cursist(
                0,
                emailField.getText(),
                naamField.getText(),
                LocalDate.of(Integer.parseInt(jaarField.getText()),Integer.parseInt(maandField.getText()),Integer.parseInt(dagField.getText())),
                Geslacht.fromString(geslachtField.getText()),
                straatField.getText(),
                huisnrField.getText(),
                postcodeField.getText(),
                woonplaatsField.getText(),
                landField.getText()
            );
            cdb.updateCursist(c, newC);
            cursist.clear();
            cursist.addAll(cdb.getAllCursisten());
            window.hide();
        });

        layout.getChildren().addAll(emailField,naamField,jaarField,maandField,dagField,geslachtField,straatField,huisnrField,postcodeField,woonplaatsField,landField,update);

        Scene editCursist = new Scene(layout);
        window.setScene(editCursist);
        return window;

    }
       
    
    // -----------CURSUSSEN--------------------------------------------------------------
    // ------------------------------------------------------------------------------------
   
    
    private Stage cursusScene() throws SQLException {
          
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        layout.getChildren().add(cursusTable);
        HBox cursusButtons = new HBox();
               
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
            cursusdb.deleteCursus(c);
            cursus.clear();
            cursus.addAll(cursusdb.getAllCursussen());
        });
        cursusButtons.getChildren().add(delete);
        
        //Update
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            Stage deleteWindow = editCursus((Cursus)cursusTable.getSelectionModel().getSelectedItem());
            deleteWindow.setWidth(500);
            deleteWindow.setHeight(350);
            deleteWindow.show();
        });
        cursusButtons.getChildren().add(update);
        
        //Return
        Button returnButton = new Button("Return");
        returnButton.setOnAction((e) -> {
            window.hide();
        });
        cursusButtons.getChildren().add(returnButton);           
              
        //Buttons 
        layout.setSpacing(5);
        layout.setPadding(new Insets(5,5,5,5));
        cursusButtons.setSpacing(5);
        layout.getChildren().add(cursusButtons);
                       
        Scene cursusScene = new Scene(layout);
        window.setScene(cursusScene);
        return window; 
    }
    
    private void initCursusTable() throws SQLException {
               
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
        
    private Stage createCursus() {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);

        TextField naamField = new TextField("Naam");
        TextField owField = new TextField("Onderwerp");
        TextField introField = new TextField("Introductie Tekst");
        TextField niveauField = new TextField("Niveau Aanduiding");
        Button create = new Button("Create");

        create.setOnAction((e) -> {
            Cursus newC = new Cursus(
                0,
                naamField.getText(),
                owField.getText(),
                introField.getText(),
                niveauField.getText()
            );
            cursusdb.addCursus(newC);
            cursus.clear();
            cursus.addAll(cursusdb.getAllCursussen());
            window.hide();
        });

        layout.getChildren().addAll(naamField,owField,introField,niveauField,create);

        Scene createCursist = new Scene(layout);
        window.setScene(createCursist);
        return window;
    
    }
    
    private Stage editCursus(Cursus c) {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);

        TextField naamField = new TextField(c.getNaam());
        TextField owField = new TextField(c.getOnderwerp());
        TextField introField = new TextField(c.getIntroductietekst());
        TextField niveauField = new TextField(c.getNiveauaanduiding());
        Button update = new Button("Update");

        update.setOnAction((e) -> {
            Cursus newC = new Cursus(
                0,
                naamField.getText(),
                owField.getText(),
                introField.getText(),
                niveauField.getText()
            );
            cursusdb.updateCursus(c, newC);
            cursus.clear();
            cursus.addAll(cursusdb.getAllCursussen());
            window.hide();
        });

        layout.getChildren().addAll(naamField,owField,introField,niveauField,update);

        Scene editCursus = new Scene(layout);
        window.setScene(editCursus);
        return window;

    }
    
    
    
    // INSCHRIJVINGEN -----------------------------------------------------------------------------
    
    private Stage inschrijvingScene() throws SQLException {
          
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        layout.getChildren().add(inschrijvingTable);
        HBox buttons = new HBox();
               
        //Create
        Button create = new Button("Create");
        create.setOnAction((e) -> {
            Stage createWindow = createInschrijving();
            createWindow.setWidth(500);
            createWindow.setHeight(350);
            createWindow.show();
        });
        buttons.getChildren().addAll(create);
        
        //Delete  
        Button delete = new Button("Delete");
        delete.setOnAction((e) -> {
            Inschrijving c = (Inschrijving) inschrijvingTable.getSelectionModel().getSelectedItem();
            idb.deleteInschrijving(c);
            inschrijving.clear();
            inschrijving.addAll(idb.getAllInschrijvingen());
        });
        buttons.getChildren().add(delete);
        
        //Update
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            Stage deleteWindow = editInschrijving((Inschrijving)inschrijvingTable.getSelectionModel().getSelectedItem());
            deleteWindow.setWidth(500);
            deleteWindow.setHeight(350);
            deleteWindow.show();
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
       
    private void initInschrijvingTable() throws SQLException {
               
        inschrijving = FXCollections.observableArrayList(idb.getAllInschrijvingen());
        TableColumn cursusColumn = new TableColumn("CursusID");
        TableColumn cursistColumn = new TableColumn("CursistID");
        TableColumn datumColumn = new TableColumn("Datum");
        TableColumn voortgangColumn = new TableColumn("Voortgang");
        cursusColumn.setCellValueFactory(new PropertyValueFactory<Inschrijving,String>("Cursus"));
        cursistColumn.setCellValueFactory(new PropertyValueFactory<Inschrijving,String>("Cursist"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<Inschrijving,String>("Datum")); 
        voortgangColumn.setCellValueFactory(new PropertyValueFactory<Inschrijving,String>("Voortgang"));
        
        inschrijvingTable.setItems(inschrijving);
        inschrijvingTable.getColumns().addAll(cursusColumn, cursistColumn, datumColumn, voortgangColumn);
    }    
    
    private Stage createInschrijving() {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);

        //TextField cursusField = new TextField("CursusID");
        ComboBox cursusField = new ComboBox();
        cursusField.setItems(cursus);
        
        ComboBox cursistField = new ComboBox();
        cursistField.setItems(cursist);
        
        TextField jaarField = new TextField("Jaar");
        TextField maandField = new TextField("Maand");
        TextField dagField = new TextField("Dag");
        TextField voortgangField = new TextField("Voortgang");
        Button create = new Button("Create");

        create.setOnAction((e) -> {
            Inschrijving newC = new Inschrijving(
                0,
                //Integer.parseInt(cursusField.getText()),
                (Cursus) cursusField.getValue(),
                (Cursist) cursistField.getValue(),
                LocalDate.of(Integer.parseInt(jaarField.getText()),Integer.parseInt(maandField.getText()),Integer.parseInt(dagField.getText())),
                Integer.parseInt(voortgangField.getText())
            );
            idb.addInschrijving(newC);
            inschrijving.clear();
            inschrijving.addAll(idb.getAllInschrijvingen());
            window.hide();
        });

        layout.getChildren().addAll(cursusField,cursistField,jaarField,maandField,dagField,voortgangField,create);

        Scene createInschrijving = new Scene(layout);
        window.setScene(createInschrijving);
        return window;
    
    }
    
    private Stage editInschrijving(Inschrijving c) {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);

        //TextField cursusField = new TextField(String.valueOf(c.getCursusID()));
        
        ComboBox cursusField = new ComboBox();
        cursusField.setItems(cursus);
        
        ComboBox cursistField = new ComboBox();
        cursistField.setItems(cursist);
        
        TextField jaarField = new TextField(String.valueOf(c.getDatum().getYear()));
        TextField maandField = new TextField(String.valueOf(c.getDatum().getMonthValue()));
        TextField dagField = new TextField(String.valueOf(c.getDatum().getDayOfMonth()));
        TextField voortgangField = new TextField(String.valueOf(c.getVoortgang()));
        Button update = new Button("Update");

        
        update.setOnAction((e) -> {
            Inschrijving newC = new Inschrijving(
                0,
                //Integer.parseInt(cursusField.getText()),
                (Cursus) cursusField.getValue(),
                (Cursist) cursistField.getValue(),
                LocalDate.of(Integer.parseInt(jaarField.getText()),Integer.parseInt(maandField.getText()),Integer.parseInt(dagField.getText())),
                Integer.parseInt(voortgangField.getText())
            );
            idb.updateInschrijving(c, newC);
            inschrijving.clear();
            inschrijving.addAll(idb.getAllInschrijvingen());
            window.hide();
        });
        
        
        layout.getChildren().addAll(cursusField,cursistField,jaarField,maandField,dagField,voortgangField,update);

        Scene editCursist = new Scene(layout);
        window.setScene(editCursist);
        return window;

    }
    
    
    // CERTIFICATEN =============================================================================
    

    private Stage certificaatScene() throws SQLException {
          
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        layout.getChildren().add(certificaatTable);
        HBox buttons = new HBox();
               
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
            Certificaat c = (Certificaat) certificaatTable.getSelectionModel().getSelectedItem();
            cerdb.deleteCertificaat(c);
            certificaat.clear();
            certificaat.addAll(cerdb.getAllCertificaten());
        });
        buttons.getChildren().add(delete);
        
        //Update
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            Stage deleteWindow = editCertificaat((Certificaat)certificaatTable.getSelectionModel().getSelectedItem());
            deleteWindow.setWidth(500);
            deleteWindow.setHeight(350);
            deleteWindow.show();
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
       
    private void initCertificaatTable() throws SQLException {
               
        certificaat = FXCollections.observableArrayList(cerdb.getAllCertificaten());
        TableColumn cijferColumn = new TableColumn("Cijfer");
        TableColumn medewerkerColumn = new TableColumn("Medewerker");
        TableColumn certificaatColumn = new TableColumn("CertificaatNummer");
        TableColumn inschrijvingColumn = new TableColumn("InschrijvingID");
        cijferColumn.setCellValueFactory(new PropertyValueFactory<Certificaat,String>("Cijfer"));
        medewerkerColumn.setCellValueFactory(new PropertyValueFactory<Certificaat,String>("NaamMedewerker"));
        certificaatColumn.setCellValueFactory(new PropertyValueFactory<Certificaat,String>("CertificaatNummer")); 
        inschrijvingColumn.setCellValueFactory(new PropertyValueFactory<Certificaat,String>("InschrijvingID"));
        
        certificaatTable.setItems(certificaat);
        certificaatTable.getColumns().addAll(cijferColumn, medewerkerColumn, certificaatColumn, inschrijvingColumn);
    }    
    
    private Stage createCertificaat() {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(300);
        layout.setMinWidth(600);

        TextField cijferField = new TextField("Cijfer");
        TextField medewerkerField = new TextField("NaamMedewerker");
        TextField certificaatField = new TextField("CertificaatNummer");
        ComboBox inschrijvingField = new ComboBox();
        inschrijvingField.setItems(inschrijving);
        Button create = new Button("Create");

        create.setOnAction((e) -> {
            Certificaat newC = new Certificaat(
                0,
                Integer.parseInt(cijferField.getText()),
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
    
    private Stage editCertificaat(Certificaat c) {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);
        
        TextField cijferField = new TextField(String.valueOf(c.getCijfer()));
        TextField medewerkerField = new TextField(c.getMedewerker());
        TextField certificaatField = new TextField(String.valueOf(c.getNummer()));
        ComboBox inschrijvingField = new ComboBox();
        inschrijvingField.setItems(inschrijving);
        Button update = new Button("Update");
       
        
        update.setOnAction((e) -> {
            Certificaat newC = new Certificaat(
                0,
                Integer.parseInt(cijferField.getText()),
                medewerkerField.getText(),
                Integer.parseInt(certificaatField.getText()),
                (Inschrijving) inschrijvingField.getValue()
            );
            cerdb.updateCertificaat(c, newC);
            certificaat.clear();
            certificaat.addAll(cerdb.getAllCertificaten());
            window.hide();
        });
        
        
        layout.getChildren().addAll(cijferField, medewerkerField, certificaatField, inschrijvingField,update);

        Scene editCertificaat = new Scene(layout);
        window.setScene(editCertificaat);
        return window;

    }
    
    
      
}

