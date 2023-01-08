/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Certificaat;
import com.groep13.codecademy.domain.Cursist;
import com.groep13.codecademy.domain.Cursus;
import com.groep13.codecademy.domain.Geslacht;
import com.groep13.codecademy.domain.Module;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milko
 */
public class StatistiekDB {
    
    private final CertificaatDB certdb = new CertificaatDB();
    private final CursusDB cursusdb = new CursusDB();
    private final ModuleDB moduledb = new ModuleDB();
    private final BekijktDB bekijktdb = new BekijktDB();
    
    public double percentageBehaaldeCursussenPerGeslacht(String g) {
        String SQL = String.format("SELECT\n" +
            "	1.00 * (SELECT COUNT(*)\n" +
            "	FROM Cursist JOIN Inschrijving ON Cursist.ID = Inschrijving.CursistID\n" +
            "	JOIN Certificaat ON Inschrijving.ID = Certificaat.InschrijvingID\n" +
            "	WHERE Geslacht = '%s')\n" +
            "	/\n" +
            "	(SELECT COUNT(*)\n" +
            "	FROM Cursist JOIN Inschrijving ON Cursist.ID = Inschrijving.CursistID\n" +
            "	WHERE Geslacht = '%s')\n" +
            "	* 100 AS Percentage", g.toString(),g.toString());
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                return rs.getDouble("Percentage");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiekDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public double voortgangModuleVanCursist(Cursist c, Module m){
        return 0;
    }

    public double gemiddeldeVoortgangPerCursus(Cursus c) {
        String SQL = String.format("(SELECT SUM(Voortgang) /\n" +
            "    ((SELECT COUNT(*) FROM Inschrijving WHERE Inschrijving.CursusID=%d) * (SELECT Count(*) FROM Module WHERE Module.CursusID = %d))) AS GemVoortgang\n" +
            "    FROM Cursus JOIN Module ON Module.CursusID = Cursus.ID\n" +
            "    JOIN Bekijkt ON Bekijkt.ContentItemID = Module.ContentItemID\n" +
            "    WHERE Cursus.ID=%d;", c.getId(),c.getId(),c.getId());
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                return rs.getDouble("GemVoortgang");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiekDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public double gemiddeldeVoortgangPerCursusByID(int cID) {
        String SQL = String.format("(SELECT SUM(Voortgang) /\n" +
            "    ((SELECT COUNT(*) FROM Inschrijving WHERE Inschrijving.CursusID=%d) * (SELECT Count(*) FROM Module WHERE Module.CursusID = %d))) AS GemVoortgang\n" +
            "    FROM Cursus JOIN Module ON Module.CursusID = Cursus.ID\n" +
            "    JOIN Bekijkt ON Bekijkt.ContentItemID = Module.ContentItemID\n" +
            "    WHERE Cursus.ID=%d;", cID,cID,cID);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                return rs.getDouble("GemVoortgang");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiekDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    // Hashmap als <ContentItemID, Percentage>
    public HashMap<Integer, Double> voortgangPerModuleVanCursusEnCursist(int cursusID, int cursistID) {
        ArrayList<Module> cmodules = moduledb.getCourseModules(cursusID);
        HashMap<Integer, Double> voortgang = new HashMap<>();
        for(Module module:cmodules) {
            voortgang.put(module.getId(), bekijktdb.getContentItemProgress(module.getId(), cursistID));
        }
        return voortgang;
    }
    
    public ArrayList<Certificaat> certificatenVanCursist(int cursistID) {
        ArrayList<Certificaat> certificaten = new ArrayList<>();
        String SQL = String.format("SELECT Cursus.ID, Cursus.CursusNaam, Cursus.IntroductieTekst, Cursus.NiveauAanduiding, Cursus.Onderwerp\n" +
            "FROM Certificaat JOIN Inschrijving ON Certificaat.InschrijvingID = Inschrijving.ID\n" +
            "JOIN Cursus on Inschrijving.CursusID = Cursus.ID\n" +
            "WHERE CursistID = %d;", cursistID);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                certificaten.add(certdb.getCertificaatByID(cursistID));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiekDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return certificaten;
    }
    
//    public ArrayList<Webcast> topDrieWebcasts() {
//        
//    }
    
    public ArrayList<Cursus> topDrieCursussenMetMeesteCertificaten() {
        ArrayList<Cursus> cursussen = new ArrayList<>();
        String SQL = String.format("SELECT TOP 3 Cursus.ID as CursusId, Cursus.CursusNaam, COUNT(*) as AmtCert\n" +
            "FROM Certificaat JOIN Inschrijving ON Certificaat.InschrijvingID = Inschrijving.ID\n" +
            "JOIN Cursus ON Cursus.ID = Inschrijving.CursusID\n" +
            "GROUP BY Cursus.ID, Cursus.CursusNaam;");
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                cursussen.add(cursusdb.getCursusById(rs.getInt("CursusID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiekDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursussen;
    }
    
    public ArrayList<Cursus> aanbevolenCursussenBijCursus(int cursusId) {
        ArrayList<Cursus> cursussen = new ArrayList<>();
        String SQL = String.format("SELECT *\n" +
            "FROM Aanbevolen\n" +
            "WHERE AanbevolenBijId = %d;", cursusId);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                cursussen.add(cursusdb.getCursusById(rs.getInt("CursusID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiekDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursussen;
    }
    
    public int hoeveelCertificatenPerCursus(int cursusId) {
        String SQL = String.format("SELECT Cursus.ID AS CursusID, COUNT(*) AS AmtCert\n" +
            "FROM Certificaat JOIN Inschrijving ON Certificaat.InschrijvingID = Inschrijving.ID\n" +
            "JOIN Cursus on Inschrijving.CursusID = Cursus.ID\n" +
            "WHERE Cursus.ID = %d\n" +
            "GROUP BY Cursus.ID;", cursusId);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                return rs.getInt("AmtCert");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiekDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
