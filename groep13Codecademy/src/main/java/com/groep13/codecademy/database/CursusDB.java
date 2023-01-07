/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Cursist;
import com.groep13.codecademy.domain.Geslacht;
import com.groep13.codecademy.domain.Cursus;
import com.groep13.codecademy.domain.Module;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nikki
 */
public class CursusDB {
    public ArrayList<Cursus> getAllCursussen() {
        ResultSet rs = DB.execWithRS("SELECT * FROM Cursus");
        ArrayList<Cursus> allCursussen = new ArrayList<>();
        try {
            while (rs.next()) {
                allCursussen.add(new Cursus(
                        rs.getInt("ID"),
                        rs.getString("CursusNaam"),
                        rs.getString("Onderwerp"),
                        rs.getString("IntroductieTekst"),
                        rs.getString("NiveauAanduiding")      
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allCursussen;
    }
    
    public boolean addCursus(Cursus c) {
        String addCursus = String.format("INSERT INTO Cursus VALUES (\'%s\',\'%s\',\'%s\',\'%s\')",
                c.getNaam(),
                c.getOnderwerp(),
                c.getIntroductietekst(),
                c.getNiveauaanduiding());
        return DB.exec(addCursus);
    }
    
    public boolean updateCursus(Cursus oldC, Cursus newC) {
        String updateCursus = String.format("UPDATE Cursus SET "
            + "CursusNaam=\'%s\',"
            + "Onderwerp=\'%s\',"
            + "IntroductieTekst=\'%s\',"
            + "NiveauAanduiding=\'%s\'"              
            + "WHERE ID=%d", newC.getNaam(),newC.getOnderwerp(), newC.getIntroductietekst(), newC.getNiveauaanduiding(), oldC.getId());
        return DB.exec(updateCursus);
    }
    
    public boolean deleteCursus(Cursus c) {
        String removeCursus = String.format("DELETE FROM Cursus WHERE ID=%d",c.getId());
        return DB.exec(removeCursus);
    }
    
    public Cursus getCursusById(int id) {
        String SQL = String.format("SELECT * FROM Cursus WHERE id = %d", id);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                return new Cursus(
                        rs.getInt("ID"),
                        rs.getString("CursusNaam"),
                        rs.getString("Onderwerp"),
                        rs.getString("IntroductieTekst"),
                        rs.getString("NiveauAanduiding")      
                );
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    
    //public Module(int id, int nummer, LocalDate publicatiedatum, String status,
    //String titel, int versie, String beschrijving, String naamcontactpersoon,
    //String emailcontactpersoon, int volgnummer, int cursusid) {
    public ArrayList<Module> getCursusModulesByCursusId(int cursusId) {
        ArrayList<Module> modules = new ArrayList<>();
        String SQL = String.format("SELECT *\n" +
            "FROM Cursus JOIN Module\n" +
            "ON Cursus.ID = Module.CursusID\n" +
            "JOIN ContentItem ON Module.ContentItemID = ContentItem.ID\n" +
            "WHERE Cursus.ID = %d;", cursusId);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                modules.add(new Module(
                        rs.getInt("ContentItemID"),
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
        }
        return modules;
    }
    
}
