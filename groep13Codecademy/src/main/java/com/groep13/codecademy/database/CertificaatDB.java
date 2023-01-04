/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Certificaat;
import com.groep13.codecademy.domain.Inschrijving;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nikki
 */
public class CertificaatDB {
    
    private final InschrijvingDB idb = new InschrijvingDB();
    
    public ArrayList<Certificaat> getAllCertificaten() {
        ResultSet rs = DB.execWithRS("SELECT * FROM Certificaat");
        ArrayList<Certificaat> allCertificaten = new ArrayList<>();
        try {
            while (rs.next()) {
                allCertificaten.add(new Certificaat(
                        rs.getInt("ID"),
                        rs.getInt("Cijfer"),
                        rs.getString("NaamMedewerker"),
                        rs.getInt("CertificaatNummer"),
                        idb.getInschrijvingById(rs.getInt("InschrijvingID"))                             
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allCertificaten;
    }
    
    public void addCertificaat(Certificaat c) {
        String addCertificaat = String.format("INSERT INTO Certificaat VALUES (%d,\'%s\',%d,%d)",
                c.getCijfer(),
                c.getMedewerker(),
                c.getNummer(),
                c.getInschrijving().getId());
        DB.exec(addCertificaat);
    }
    
    public void updateCertificaat(Certificaat oldC, Certificaat newC) {
        String updateCertificaat = String.format("UPDATE Certificaat SET "
            + "Cijfer=%d,"
            + "NaamMedewerker=\'%s\',"
            + "CertificaatNummer=%d,"  
            + "InschrijvingID=%d',"     
            + "WHERE ID=%d", newC.getCijfer(), newC.getMedewerker(), newC.getNummer(), newC.getInschrijving().getId(), oldC.getId());
        DB.exec(updateCertificaat);
    }
    
    public boolean deleteCertificaat(Certificaat c) {
        String removeCertificaat = String.format("DELETE FROM Certificaat WHERE ID=%d",c.getId());
        return DB.exec(removeCertificaat);
    }
}
