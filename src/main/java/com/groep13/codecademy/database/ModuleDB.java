/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.database.ContentItemDB;
import com.groep13.codecademy.domain.ContentItem;
import com.groep13.codecademy.domain.Module;
import com.groep13.codecademy.domain.Status;
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
                " ContentItem.PublicatieDatum, ContentItem.Status, Titel, Versie, Beschrijving," +
                " NaamContactpersoon, EmailContactpersoon, Volgnummer, CursusID, ContentItemID" +
                " FROM Module JOIN ContentItem\n" +
                "ON Module.ContentItemID = ContentItem.ID");
        // pubdatum veranderd naar ci.pubdatum
        ArrayList<Module> allModules = new ArrayList<>();
        try {
            while (rs.next()) {
                allModules.add(new Module(
                        rs.getInt("ID"),
                        rs.getInt("ContentItemNummer"),
                        rs.getDate("PublicatieDatum").toLocalDate(),
                        Status.fromString(rs.getString("Status")),
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
    
    public Module getModuleById(int id) {
        ResultSet rs = DB.execWithRS(String.format("SELECT ContentItem.ID, ContentItem.ContentItemNummer," +
                " ContentItem.PublicatieDatum, ContentItem.Status, Titel, Versie, Beschrijving," +
                " NaamContactpersoon, EmailContactpersoon, Volgnummer, CursusID, ContentItemID" +
                " FROM Module JOIN ContentItem\n" +
                "ON Module.ContentItemID = ContentItem.ID WHERE ContentItem.ID = %d",id));
        try {
            while (rs.next()) {
                return new Module(
                        rs.getInt("ID"),
                        rs.getInt("ContentItemNummer"),
                        rs.getDate("PublicatieDatum").toLocalDate(),
                        Status.fromString(rs.getString("Status")),
                        rs.getString("Titel"),
                        rs.getInt("Versie"),
                        rs.getString("Beschrijving"),
                        rs.getString("NaamContactpersoon"),
                        rs.getString("EmailContactpersoon"),
                        rs.getInt("Volgnummer"),
                        rs.getInt("CursusID")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Module> getAllModulesWithoutCursus() {
        ArrayList<Module> allModules = getAllModules()
                .stream()
                .filter(m -> m.getCursusid() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
        return allModules;
    }
    
    public ArrayList<Module> getCourseModules(int cId) {
        ArrayList<Module> allModules = getAllModules()
                .stream()
                .filter(m -> m.getCursusid() == cId)
                .collect(Collectors.toCollection(ArrayList::new));
        return allModules;
    }
    
    public boolean moduleClearCursus(int moduleId) {
        String SQL = String.format("UPDATE Module SET CursusID = NULL WHERE ContentItemID = %d", moduleId);
        return DB.exec(SQL);
    }
    
    public boolean moduleSetCursus(int moduleId, int cId) {
        String SQL = String.format("UPDATE Module SET CursusID = %d WHERE ContentItemID = %d", cId, moduleId);
        return DB.exec(SQL);
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
