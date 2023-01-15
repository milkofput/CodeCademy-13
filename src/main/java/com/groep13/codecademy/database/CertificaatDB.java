package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Certificaat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * CertifaatDB beheert de informatie uit de database voor gegevens uit de 'Certificaat' tabel.
 */
public class CertificaatDB {
    
    private final InschrijvingDB idb = new InschrijvingDB();
    
    //Maakt voor elke record in de 'Certificaat' tabel een Certificaat en retourneert deze in een ArrayList.
    public ArrayList<Certificaat> getAllCertificaten() {
        ResultSet rs = DB.execWithRS("SELECT * FROM Certificaat");
        ArrayList<Certificaat> allCertificaten = new ArrayList<>();
        try {
            while (rs.next()) {
                allCertificaten.add(new Certificaat(
                        rs.getInt("ID"),
                        rs.getDouble("Cijfer"),
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
    
    //Voegt een certificaat toe aan de 'Certificaat' tabel.
    public void addCertificaat(Certificaat c) {
        String addCertificaat = String.format(Locale.US, "INSERT INTO Certificaat VALUES (%.1f,\'%s\',%d,%d)",
                c.getCijfer(),
                c.getMedewerker(),
                c.getNummer(),
                c.getInschrijving().getId());
        DB.exec(addCertificaat);
    }
    
    //Past een certificaat in de database aan.
    public void updateCertificaat(Certificaat oldC, Certificaat newC) {
        String updateCertificaat = String.format(Locale.US, "UPDATE Certificaat SET "
            + "Cijfer=%.1f,"
            + "NaamMedewerker=\'%s\',"
            + "CertificaatNummer=%d,"  
            + "InschrijvingID=%d"     
            + "WHERE ID=%d", newC.getCijfer(), newC.getMedewerker(), newC.getNummer(), newC.getInschrijving().getId(), oldC.getId());
        System.out.println(updateCertificaat);
        DB.exec(updateCertificaat);
    }
    
    //Verwijdert een certificaat uit de database.
    public boolean deleteCertificaat(Certificaat c) {
        String removeCertificaat = String.format("DELETE FROM Certificaat WHERE ID=%d",c.getId());
        return DB.exec(removeCertificaat);
    }
    
    //Retourneert een certificaat uit de database met een bepaald id.
    public Certificaat getCertificaatByID(int cid) {
        ResultSet rs = DB.execWithRS(String.format("SELECT * FROM Certificaat WHERE ID=%d", cid));
        try {
            while (rs.next()) {
                return (new Certificaat(
                        rs.getInt("ID"),
                        rs.getDouble("Cijfer"),
                        rs.getString("NaamMedewerker"),
                        rs.getInt("CertificaatNummer"),
                        idb.getInschrijvingById(rs.getInt("InschrijvingID"))                             
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
