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
                        rs.getInt("InschrijvingID")                             
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allCertificaten;
    }
    
    public void addCertificaat(Certificaat c) {
        String addCertificaat = String.format("INSERT INTO Certificaat VALUES (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                c.getId(),
                c.getCijfer(),
                c.getMedewerker(),
                c.getNummer(),
                c.getInschrijvingID());
        DB.exec(addCertificaat);
    }
    
    public void updateCertificaat(Certificaat oldC, Certificaat newC) {
        String updateCertificaat = String.format("UPDATE Certificaat SET "
            + "ID\'%s\',"
            + "Cijfer=\'%s\',"
            + "NaamMedewerker=\'%s\',"
            + "CertificaatNummer=\'%s\',"  
            + "InschrijvingID=\'%s\',"     
            + "WHERE ID=%d", newC.getId(),newC.getCijfer(), newC.getMedewerker(),newC.getNummer(),newC.getInschrijvingID(),oldC.getId());
        DB.exec(updateCertificaat);
    }
    
    public void deleteCertificaat(Certificaat c) {
        String removeCertificaat = String.format("DELETE FROM Certificaat WHERE ID=%d",c.getId());
        DB.exec(removeCertificaat);
    }
}
