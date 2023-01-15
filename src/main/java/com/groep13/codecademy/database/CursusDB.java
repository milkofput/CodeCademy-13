package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Cursus;
import com.groep13.codecademy.domain.Niveau;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CursusDB beheert de informatie uit de database voor gegevens uit de 'Cursus' tabel.
 */
public class CursusDB {
    
    //Maakt voor elke record in de 'Cursus' tabel een Cursus en retourneert deze in een ArrayList.
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
                        Niveau.fromString(rs.getString("NiveauAanduiding"))      
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allCursussen;
    }
    
    //Voegt een cursus toe aan de 'Cursus' tabel in de database.
    public boolean addCursus(Cursus c) {
        String addCursus = String.format("INSERT INTO Cursus VALUES (\'%s\',\'%s\',\'%s\',\'%s\')",
                c.getNaam(),
                c.getOnderwerp(),
                c.getIntroductietekst(),
                c.getNiveauaanduiding().getNiveauNaam());
        return DB.exec(addCursus);
    }
    
    //Past een cursus aan in de database.
    public boolean updateCursus(Cursus oldC, Cursus newC) {
        String updateCursus = String.format("UPDATE Cursus SET "
            + "CursusNaam=\'%s\',"
            + "Onderwerp=\'%s\',"
            + "IntroductieTekst=\'%s\',"
            + "NiveauAanduiding=\'%s\'"              
            + "WHERE ID=%d", newC.getNaam(),newC.getOnderwerp(), newC.getIntroductietekst(), newC.getNiveauaanduiding().getNiveauNaam(), oldC.getId());
        return DB.exec(updateCursus);
    }
    
    //Verwijdert een cursus in de database.
    public boolean deleteCursus(Cursus c) {
        String removeCursus = String.format("DELETE FROM Cursus WHERE ID=%d",c.getId());
        return DB.exec(removeCursus);
    }
    
    //Retourneert een cursus uit de database met een bepaald id.
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
                        Niveau.fromString(rs.getString("NiveauAanduiding"))      
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    
}
