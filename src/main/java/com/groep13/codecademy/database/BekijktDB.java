package com.groep13.codecademy.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BekijktDB beheert de informatie uit de database voor gegevens uit de 'Bekijkt' tabel.
 */
public class BekijktDB {
    
    private final CursusDB cursusdb = new CursusDB();
    
    //Deze methode retourneert de voortgang van een cursist in een bepaald contentitem.
    public double getContentItemProgress(int contentItemId, int cursistId) {
        ResultSet rs = DB.execWithRS(String.format("SELECT Voortgang\n" +
            "FROM Bekijkt\n" +
            "WHERE ContentItemID = %d AND CursistID = %d;", contentItemId, cursistId));
        try {
            while (rs.next()) {
                return rs.getDouble("Voortgang");
            }
        } catch (SQLException ex) {
        }
        return 0.0;
    }
    
}
