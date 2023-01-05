/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;
import com.groep13.codecademy.database.ContentItemDB;
import com.groep13.codecademy.domain.ContentItem;
import com.groep13.codecademy.domain.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author nikki
 */
public class ModuleDB {

    private final ContentItemDB cdb = new ContentItemDB();
    public ArrayList<Module> getAllModules() {
        ResultSet rs = DB.execWithRS("SELECT ContentItem.ID, ContentItem.ContentItemNummer," +
                " PublicatieDatum, ContentItem.Status, Titel, Versie, Beschrijving," +
                " NaamContactpersoon, EmailContactpersoon, Volgnummer, CursusID, ContentItemID" +
                " FROM Module JOIN ContentItem\n" +
                "ON Module.ContentItemID = ContentItem.ID");
        ArrayList<Module> allModules = new ArrayList<>();
        try {
            while (rs.next()) {
                allModules.add(new Module(
                        rs.getInt("ID"),
                        rs.getInt("ContentItemNummer"),
                        rs.getDate("PublicatieDatum").toLocalDate(),
                        rs.getString("Status"),
                        rs.getString("Titel"),
                        rs.getInt("Versie"),
                        rs.getString("Beschrijving"),
                        rs.getString("NaamContactpersoon"),
                        rs.getString("EmailContactpersoon"),
                        rs.getInt("Volgnummer"),
                        rs.getInt("CursusID")
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allModules;
    }

    public ArrayList<Module> getAllModulesWithoutCursus() {
        ArrayList<Module> allModules = getAllModules()
                .stream()
                .filter(m -> m.getCursusid() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
        return allModules;
    }
    
//    public void addModule(Module m) {
//        int id = cdb.addContentItemAndReturnId(m);
//        String addModule = String.format("INSERT INTO Module VALUES (\'%s\',%d,\'%s\',\'%s\',\'%s\',%d,%d,%d)",
//                m.getTitel(),
//                m.getVersie(),
//                m.getBeschrijving(),
//                m.getNaamcontactpersoon(),
//                m.getEmailcontactpersoon(),
//                m.getVolgnummer(),
//                m.getCursusid(),
//                id
//                );
//        DB.exec(addModule);
//    }

}
