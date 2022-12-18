/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g13codecademy.gui;

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
import g13codecademy.database.CursistDB;
import g13codecademy.domain.Cursist;
import g13codecademy.domain.Geslacht;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author milko
 */
public class GUI extends Application {
    
    private static CursistDB cdb = new CursistDB();
    private static TableView table = new TableView();
    private static ObservableList<Cursist> cursist;
    
    @Override
    public void start(Stage stage) throws SQLException {
        
        VBox layout = new VBox();
        initTable();
        layout.getChildren().add(table);
        HBox buttons = new HBox();
        
        //Create

        Button create = new Button("Create");
        create.setOnAction((e) -> {
            Stage window = createCursist();
            window.setWidth(500);
            window.setHeight(350);
            window.show();
        });
        buttons.getChildren().addAll(create);
        
 
        //Delete  
        Button delete = new Button("Delete");
        delete.setOnAction((e) -> {
            Cursist c = (Cursist) table.getSelectionModel().getSelectedItem();
            cdb.deleteCursist(c);
            cursist.clear();
            cursist.addAll(cdb.getAllCursisten());
        });
        buttons.getChildren().add(delete);
        
        //Update
        Button update = new Button("Update");
        update.setOnAction((e) -> {
            Stage window = editCursist((Cursist)table.getSelectionModel().getSelectedItem());
            window.setWidth(500);
            window.setHeight(350);
            window.show();
        });
        buttons.getChildren().add(update);
        
        //Buttons 
        layout.setSpacing(5);
        layout.setPadding(new Insets(5,5,5,5));
        buttons.setSpacing(5);
        layout.getChildren().add(buttons);
        
        //scene and stage
        Scene scene = new Scene(layout);
        stage.setMinWidth(800);
        stage.setScene(scene);
        stage.show();
    }
    
    private void initTable() throws SQLException {
        cursist = FXCollections.observableArrayList(cdb.getAllCursisten());
        TableColumn emailColumn = new TableColumn("EmailAdres");
        TableColumn naamColumn = new TableColumn("Naam");
        TableColumn datumColumn = new TableColumn("GeboorteDatum");
        TableColumn geslachtColumn = new TableColumn("Geslacht");
        TableColumn adresColumn = new TableColumn("Adres");
        TableColumn woonplaatsColumn = new TableColumn("Woonplaats");
        TableColumn landColumn = new TableColumn("Land");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("emailAdres"));
        naamColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("naam"));
        datumColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("geboortedatum")); 
        geslachtColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("geslacht"));
        adresColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("adres"));
        woonplaatsColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("woonplaats"));
        landColumn.setCellValueFactory(new PropertyValueFactory<Cursist,String>("land"));
        
        table.setItems(cursist);
        table.getColumns().addAll(emailColumn,naamColumn,datumColumn,geslachtColumn,adresColumn,woonplaatsColumn,landColumn);
    }
    
    private Stage createCursist() {
        Stage window = new Stage();
        VBox layout = new VBox();
        layout.setPadding(new Insets(8,8,8,8));
        layout.setSpacing(5);
        layout.setMinHeight(200);
        layout.setMinWidth(600);

        TextField emailField = new TextField("Email");
        TextField naamField = new TextField("Naam");
        TextField jaarField = new TextField("Geboortejaar");
        TextField maandField = new TextField("Geboortemaand");
        TextField dagField = new TextField("Geboortedag");
        TextField geslachtField = new TextField("Geslacht");
        TextField adresField = new TextField("Adres");
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
                adresField.getText(),
                woonplaatsField.getText(),
                landField.getText()
            );
            cdb.addCursist(newC);
            cursist.clear();
            cursist.addAll(cdb.getAllCursisten());
            window.hide();
        });

        layout.getChildren().addAll(emailField,naamField,jaarField,maandField,dagField,geslachtField,adresField,woonplaatsField,landField,create);

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
        TextField adresField = new TextField(c.getAdres());
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
                adresField.getText(),
                woonplaatsField.getText(),
                landField.getText()
            );
            cdb.updateCursist(c, newC);
            cursist.clear();
            cursist.addAll(cdb.getAllCursisten());
            window.hide();
        });

        layout.getChildren().addAll(emailField,naamField,jaarField,maandField,dagField,geslachtField,adresField,woonplaatsField,landField,update);

        Scene editCursist = new Scene(layout);
        window.setScene(editCursist);
        return window;

    }
      
}

