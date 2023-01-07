/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Module;
import com.groep13.codecademy.domain.Inschrijving;
import java.util.ArrayList;

/**
 *
 * @author milko
 */
public class BekijktDB {
    
    private final CursusDB cursusdb = new CursusDB();
    
    public void generateBekijktForInschrijving(Inschrijving i) {
        ArrayList<Module> modules = cursusdb.getCursusModulesByCursusId(i.getCursus().getId());
        
        for (Module module:modules) {
            String addBekijkt = String.format("INSERT INTO Bekijkt (Datum, Voortgang, CursistId, ContentItemId) VALUES (\'%s\',%d,%d,%d)",
                    i.getDatum(),
                    0,
                    i.getCursist().getId(),
                    module.getId()
                    );
            System.out.println(module.getId());
            DB.exec(addBekijkt); 
        }
    }
    
    public void deleteBekijktForInschrijving(Inschrijving i) {
        ArrayList<Module> modules = cursusdb.getCursusModulesByCursusId(i.getCursus().getId());
        
        for (Module module:modules) {
            String addBekijkt = String.format("DELETE FROM Bekijkt\n" +
                    "WHERE Datum = '%s' AND\n" +
                    "CursistID = %d AND\n" +
                    "ContentItemID = %d;",
                    i.getDatum(),
                    i.getCursist().getId(),
                    module.getId()
                    );
            System.out.println(module.getId());
            DB.exec(addBekijkt); 
        }
    }
}
