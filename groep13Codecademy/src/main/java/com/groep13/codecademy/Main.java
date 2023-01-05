/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy;

import com.groep13.codecademy.database.ContentItemDB;
import com.groep13.codecademy.database.ModuleDB;
import com.groep13.codecademy.domain.ContentItem;
import com.groep13.codecademy.domain.Module;
import com.groep13.codecademy.gui.GUI;
import java.time.LocalDate;
import java.time.Month;
import javafx.application.Application;
import javafx.collections.ObservableList;

/**
 *
 * @author milko
 */
public class Main {
    
    private static ContentItemDB cidb = new ContentItemDB();   
    private static ModuleDB mdb = new ModuleDB();
    
    //private static WebcastDB mdb = new WebcastDB();  
        
    public static void main(String[] args) {
        
        //addContentItems();
        //addModules();
        
        //addWebcasts();
        
        Application.launch(GUI.class);
    }
    
    public static void addContentItems(){
        ContentItem modJavaBasis = new ContentItem(
            0,
            1,
            LocalDate.of(2019,03,02),
            "Actief"
        );
        
        ContentItem modJavaProficient = new ContentItem(
            0,
            2,
            LocalDate.of(2019,04,04),
            "Actief"
        );
        
        ContentItem modSQLBasis = new ContentItem(
            0,
            3,
            LocalDate.of(2022,11,06),
            "Actief"
        );
        
        ContentItem webcJavaGUIs = new ContentItem(
            0,
            4,
            LocalDate.of(2023,01,01),
            "Concept"
        );
        
        cidb.addContentItem(modJavaBasis);
        cidb.addContentItem(modJavaProficient);
        cidb.addContentItem(modSQLBasis);
        cidb.addContentItem(webcJavaGUIs);    
    }
    
    public static void addModules(){
        
        Module javaBasis = new Module(
            0,
            "javaBasis",
            1,
            "Introduction to JAVA",
            "Henk van der Linden",
            "h.vanderlinden@codecademy.com",
            1,
            0,
            1
        );
        
        Module javaProficient = new Module(
            0,
            "javaProficient",
            1,
            "Proficient course for JAVA",
            "Henk van der Linden",
            "h.vanderlinden@codecademy.com",
            2,
            0,
            2
        );
        
        Module SQLBasis = new Module(
            0,
            "SQLBasis",
            1,
            "Introduction to SQL",
            "Hans Duits",
            "h.duits@codecademy.com",
            2,
            0,
            3
        );
        
        mdb.addModule(javaBasis);
        mdb.addModule(javaProficient);
        mdb.addModule(SQLBasis);        
       
    }
    
    public void addWebcasts(){
        // to do
        
        // Webcast JavaGUIs = new Webcast();
    }
    
    
    
}
