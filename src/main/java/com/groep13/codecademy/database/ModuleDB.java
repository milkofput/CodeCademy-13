package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Module;
import com.groep13.codecademy.domain.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * ModuleDB beheert de informatie uit de database voor gegevens uit de 'Module' tabel.
 */
public class ModuleDB {
    
    //Maakt voor elke record in de 'Module' tabel een module en retourneert deze in een ArrayList
    public ArrayList<Module> getAllModules() {
        ResultSet rs = DB.execWithRS("SELECT ContentItem.ID, ContentItem.ContentItemNummer,\n" +
            "ContentItem.PublicatieDatum, ContentItem.Status, Titel, Versie, Beschrijving, ContactPersoon.Naam AS NaamContactpersoon, ContactPersoon.Email AS EmailContactpersoon, Volgnummer, CursusID, ContentItemID\n" +
            "FROM Module JOIN ContentItem\n" +
            "ON Module.ContentItemID = ContentItem.ID\n" +
            "JOIN ContactPersoon ON Module.ContactPersoon = ContactPersoon.ContactPersoonID;");
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
    
    //Retourneert een module uit de database met een bepaald id.
    public Module getModuleById(int id) {
        ResultSet rs = DB.execWithRS(String.format("SELECT ContentItem.ID, ContentItem.ContentItemNummer,\n" +
            "ContentItem.PublicatieDatum, ContentItem.Status, Titel, Versie, Beschrijving, ContactPersoon.Naam AS NaamContactpersoon, ContactPersoon.Email AS EmailContactpersoon, Volgnummer, CursusID, ContentItemID\n" +
            "FROM Module JOIN ContentItem\n" +
            "ON Module.ContentItemID = ContentItem.ID\n" +
            "JOIN ContactPersoon ON Module.ContactPersoon = ContactPersoon.ContactPersoonID\n" +
            "WHERE ContentItem.ID = %d;",id));
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
    
    //Retourneert alle modules die niet aan een cursus gekoppeld zijn in een ArrayList.
    public ArrayList<Module> getAllModulesWithoutCursus() {
        ArrayList<Module> allModules = getAllModules()
                .stream()
                .filter(m -> m.getCursusid() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
        return allModules;
    }
    
    //Retourneert alle modules die aan een bepaalde cursus gekoppeld zijn in een ArrayList.
    public ArrayList<Module> getCourseModules(int cId) {
        ArrayList<Module> allModules = getAllModules()
                .stream()
                .filter(m -> m.getCursusid() == cId)
                .collect(Collectors.toCollection(ArrayList::new));
        return allModules;
    }
    
    //Leegt 'CursusID' in de database van een bepaalde module.
    public boolean moduleClearCursus(int moduleId) {
        String SQL = String.format("UPDATE Module SET CursusID = NULL WHERE ContentItemID = %d", moduleId);
        return DB.exec(SQL);
    }
    
    //Wijzigt 'CursusID' in de database van een bepaalde module naar een het cursusid in de parameters.
    public boolean moduleSetCursus(int moduleId, int cId) {
        String SQL = String.format("UPDATE Module SET CursusID = %d WHERE ContentItemID = %d", cId, moduleId);
        return DB.exec(SQL);
    }
}
