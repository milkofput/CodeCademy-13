package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Inschrijving;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * InschrijvingDB beheert de informatie uit de database voor gegevens uit de 'Inschrijving' tabel.
 */
public class InschrijvingDB {
    
    private final CursistDB cursistdb = new CursistDB();
    private final CursusDB cursusdb = new CursusDB();
    
    //Maakt voor elke record in de 'Inschrijving' tabel een Inschrijving en retourneert deze in een ArrayList.
    public ArrayList<Inschrijving> getAllInschrijvingen() {
        ResultSet rs = DB.execWithRS("SELECT * FROM Inschrijving");
        ArrayList<Inschrijving> allInschrijvingen = new ArrayList<>();
        try {
            while (rs.next()) {
                allInschrijvingen.add(new Inschrijving(
                        rs.getInt("ID"),
                        cursusdb.getCursusById(rs.getInt("CursusID")),
                        cursistdb.getCursistById(rs.getInt("CursistID")),
                        rs.getDate("Datum").toLocalDate()                           
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allInschrijvingen;
    }
    
    //Voegt een inschrijving toe aan de database.
    public void addInschrijving(Inschrijving i) {
        String addInschrijving = String.format("INSERT INTO Inschrijving VALUES (%d,%d,\'%s\')",
                i.getCursus().getId(),
                i.getCursist().getId(),
                i.getDatum().toString());
        DB.exec(addInschrijving);
    }
    
    //Past een inschrijving aan in de database.
    public void updateInschrijving(Inschrijving oldI, Inschrijving newI) {
        String updateInschrijving = String.format("UPDATE Inschrijving SET "
            + "CursusID=%d,"
            + "CursistID=%d,"
            + "Datum=\'%s\'"
            + "WHERE ID=%d", newI.getCursus().getId(),newI.getCursist().getId(), newI.getDatum().toString(),oldI.getId());
        DB.exec(updateInschrijving);
    }
    
    //Verwijdert een inschrijving uit de database.
    public boolean deleteInschrijving(Inschrijving i) {
        String removeInschrijving = String.format("DELETE FROM Inschrijving WHERE ID=%d",i.getId());
        return DB.exec(removeInschrijving);
    }
    
    //Retourneert een inschrijving uit de database met een bepaald id.
    public Inschrijving getInschrijvingById(int id) {
        String SQL = String.format("SELECT * FROM Inschrijving WHERE id = %d", id);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                return new Inschrijving(
                        rs.getInt("ID"),
                        cursusdb.getCursusById(rs.getInt("CursusID")),
                        cursistdb.getCursistById(rs.getInt("CursistID")),
                        rs.getDate("Datum").toLocalDate()                            
                );
            }
        } catch (SQLException ex) {
        }
        return null;
    }
}
