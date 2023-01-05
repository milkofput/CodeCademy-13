/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;
import com.groep13.codecademy.domain.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nikki
 */
public class ModuleDB {
    
    public ArrayList<Module> getAllModules() {
        ResultSet rs = DB.execWithRS("SELECT * FROM Module");
        ArrayList<Module> allModules = new ArrayList<>();
        try {
            while (rs.next()) {
                allModules.add(new Module(
                        rs.getInt("ID"),
                        rs.getString("Titel"),
                        rs.getInt("Versie"),
                        rs.getString("Beschrijving"),
                        rs.getString("NaamContactpersoon"),
                        rs.getString("EmailContactpersoon"),
                        rs.getInt("Volgnummer"),
                        rs.getInt("CursusID"),
                        rs.getInt("ContentItemID")          
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allModules;
    }
    
    public void addModule(Module c) {
        String addModule = String.format("INSERT INTO Module VALUES (\'%s\',%d,\'%s\',\'%s\',\'%s\',%d,%d,%d)",
                c.getTitel(),
                c.getVersie(),
                c.getBeschrijving(),
                c.getNaamcontactpersoon(),
                c.getEmailcontactpersoon(),
                c.getVolgnummer(),
                c.getCursusid(),
                c.getContentitemid()
                );
        DB.exec(addModule);
    }

}
