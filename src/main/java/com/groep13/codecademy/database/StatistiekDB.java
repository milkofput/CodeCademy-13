package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Certificaat;
import com.groep13.codecademy.domain.Cursist;
import com.groep13.codecademy.domain.Cursus;
import com.groep13.codecademy.domain.Module;
import com.groep13.codecademy.domain.Webcast;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * StatistiekDB beheert de informatie uit de database die bestemd zijn voor de statistiekoverzichten uit de opdracht.
 */
public class StatistiekDB {
    
    private final CertificaatDB certdb = new CertificaatDB();
    private final CursusDB cursusdb = new CursusDB();
    private final ModuleDB moduledb = new ModuleDB();
    private final BekijktDB bekijktdb = new BekijktDB();
    private final WebcastDB webcastdb = new WebcastDB();
    
    //Retourneert als double het percentage van de inschrijvingen waar een certificaat voor is behaald voor het geslacht in de parameters.
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
            System.out.println(ex);
        }
        return 0;
    }
    
    public double voortgangModuleVanCursist(Cursist c, Module m){
        return 0;
    }
    
    //Retourneert de gemiddelde voortgang voor ingeschreven cursisten
    //per module van een bepaalde cursus, als HashMap<ContentItemID, GemiddeldeVoortgang>
    public HashMap<Integer, Double> gemiddeldeVoortgangPerModulePerCursus(Cursus c) {
        HashMap<Integer, Double> voortgang = new HashMap<>();
        String SQL = String.format("SELECT Module.ContentItemID, SUM(Voortgang) / ((SELECT COUNT(*) FROM Inschrijving WHERE Inschrijving.CursusID=%d)) AS GemVoortgang\n" +
            "FROM Module JOIN Bekijkt ON Bekijkt.ContentItemID = Module.ContentItemID\n" +
            "WHERE Module.CursusID = %d AND Bekijkt.CursistID IN (SELECT Inschrijving.CursistID FROM Inschrijving WHERE CursusID = %d)\n" +
            "GROUP BY Module.ContentItemId;", c.getId(),c.getId(),c.getId());
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                voortgang.put(rs.getInt("ContentItemID"),rs.getDouble("GemVoortgang"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return voortgang;
    }
    
    //Retourneert de voortgang per module van een cursist voor een cursus, als Hashmap <ContentItemID, Percentage>
    public HashMap<Integer, Double> voortgangPerModuleVanCursusEnCursist(int cursusID, int cursistID) {
        ArrayList<Module> cmodules = moduledb.getCourseModules(cursusID);
        HashMap<Integer, Double> voortgang = new HashMap<>();
        for(Module module:cmodules) {
            voortgang.put(module.getId(), bekijktdb.getContentItemProgress(module.getId(), cursistID));
        }
        return voortgang;
    }
    
    //Retourneert alle certificaten die een bepaalde cursist bezit in een ArrayList.
    public ArrayList<Certificaat> certificatenVanCursist(int cursistID) {
        ArrayList<Certificaat> certificaten = new ArrayList<>();
        String SQL = String.format("SELECT Cursus.ID, Cursus.CursusNaam, Cursus.IntroductieTekst, Cursus.NiveauAanduiding, Cursus.Onderwerp\n" +
            "FROM Certificaat JOIN Inschrijving ON Certificaat.InschrijvingID = Inschrijving.ID\n" +
            "JOIN Cursus on Inschrijving.CursusID = Cursus.ID\n" +
            "WHERE CursistID = %d;", cursistID);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                certificaten.add(certdb.getCertificaatByID(rs.getInt("ID")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return certificaten;
    }
    
    //Retourneert de top 3 webcasts die het meest bekeken zijn in een ArrayList.
    public ArrayList<Webcast> topDrieWebcasts() {
        ArrayList<Webcast> webcasts = new ArrayList<>();
        String SQL = String.format("SELECT TOP 3 Webcast.ContentItemID, COUNT(*) as Amt\n" +
            "FROM Bekijkt JOIN Webcast ON Bekijkt.ContentItemID = Webcast.ContentItemID\n" +
            "GROUP BY Webcast.ContentItemID;");
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                webcasts.add(webcastdb.getWebcastById(rs.getInt("ContentItemID")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return webcasts;
    }
    
    //Retourneert de top 3 cursussen die het meeste certificaten hebben in een ArrayList.
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
            System.out.println(ex);
        }
        return cursussen;
    }
    
    //Retourneert een ArrayList met aanbevolen cursussen bij een bepaalde cursus.
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
            System.out.println(ex);
        }
        return cursussen;
    }
    
    //Retourneert als int de hoeveelheid certificaten die zijn uitgegeven bij een bepaalde cursus.
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
            System.out.println(ex);
        }
        return 0;
    }
}
