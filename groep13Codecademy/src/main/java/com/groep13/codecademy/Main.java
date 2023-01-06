/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy;

import com.groep13.codecademy.database.ContentItemDB;
import com.groep13.codecademy.database.ModuleDB;
import com.groep13.codecademy.database.StatistiekDB;
import com.groep13.codecademy.gui.GUI;
import javafx.application.Application;

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
        
        StatistiekDB sdb = new StatistiekDB();
        System.out.println(sdb.aanbevolenCursussenBijCursus(1));
        
        Application.launch(GUI.class);
    }
    
    public static void addContentItems(){
//        ContentItem modJavaBasis = new ContentItem(
//            0,
//            1,
//            LocalDate.of(2019,03,02),
//            "Actief"
//        );
//
//        ContentItem modJavaProficient = new ContentItem(
//            0,
//            2,
//            LocalDate.of(2019,04,04),
//            "Actief"
//        );
//
//        ContentItem modSQLBasis = new ContentItem(
//            0,
//            3,
//            LocalDate.of(2022,11,06),
//            "Actief"
//        );
//
//        ContentItem webcJavaGUIs = new ContentItem(
//            0,
//            4,
//            LocalDate.of(2023,01,01),
//            "Concept"
//        );
//
//        cidb.addContentItem(modJavaBasis);
//        cidb.addContentItem(modJavaProficient);
//        cidb.addContentItem(modSQLBasis);
//        cidb.addContentItem(webcJavaGUIs);
    }
    
//    public static void addModules(){
//
//        Module javaBasis = new Module(
//            0,
//            1,
//            LocalDate.of(2023,05,01),
//            "Actief",
//            "javaBasis",
//            1,
//            "Introduction to JAVA",
//            "Henk van der Linden",
//            "h.vanderlinden@codecademy.com",
//            1,
//            1
//        );
//
//        mdb.addModule(javaBasis);
//
//    }
    
    public void addWebcasts(){
        // to do
        
        // Webcast JavaGUIs = new Webcast();
    }
    
    
    
}
