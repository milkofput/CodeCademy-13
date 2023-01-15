package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Cursist;
import com.groep13.codecademy.domain.Geslacht;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CursistDB beheert de informatie uit de database voor gegevens uit de 'Cursist' tabel.
 */
public class CursistDB {
    
    //Maakt voor elke record in de 'Cursist' tabel een Cursist en retourneert deze in een ArrayList.
    public ArrayList<Cursist> getAllCursisten() {
        ResultSet rs = DB.execWithRS("SELECT * FROM Cursist");
        ArrayList<Cursist> allCursisten = new ArrayList<>();
        try {
            while (rs.next()) {
                allCursisten.add(new Cursist(
                        rs.getInt("ID"),
                        rs.getString("EmailAdres"),
                        rs.getString("Naam"),
                        rs.getDate("GeboorteDatum").toLocalDate(),
                        Geslacht.fromString(rs.getString("Geslacht")),
                        rs.getString("Straat"),
                        rs.getString("Huisnummer"),
                        rs.getString("Postcode"),
                        rs.getString("Woonplaats"),
                        rs.getString("Land")          
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allCursisten;
    }
    
    //Voegt een cursist toe aan de 'Cursist' tabel.
    public void addCursist(Cursist c) {
        String addCursist = String.format("INSERT INTO Cursist VALUES (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                c.getEmailAdres(),
                c.getNaam(),
                c.getGeboortedatum().toString(),
                c.getGeslacht().getGeslachtNaam(),
                c.getStraat(),
                c.getHuisnr(),
                c.getPostcode(),
                c.getWoonplaats(),
                c.getLand());
        DB.exec(addCursist);
    }
    
    //Past een cursist in de database aan.
    public void updateCursist(Cursist oldC, Cursist newC) {
        String updateCursist = String.format("UPDATE Cursist SET "
            + "EmailAdres=\'%s\',"
            + "Naam=\'%s\',"
            + "GeboorteDatum=\'%s\',"
            + "Geslacht=\'%s\',"
            + "Straat=\'%s\',"
            + "Huisnummer=\'%s\',"
            + "Postcode=\'%s\',"
            + "Woonplaats=\'%s\',"
            + "Land=\'%s\'"              
            + "WHERE ID=%d", newC.getEmailAdres(),newC.getNaam(), newC.getGeboortedatum().toString(),newC.getGeslacht().getGeslachtNaam(),
            newC.getStraat(),newC.getHuisnr(),newC.getPostcode(),newC.getWoonplaats(),newC.getLand(),oldC.getId());
        DB.exec(updateCursist);
    }
    
    //Verwijdert een cursist in de database.
    public boolean deleteCursist(Cursist c) {
        String removeCursist = String.format("DELETE FROM Cursist WHERE ID=%d",c.getId());
        return DB.exec(removeCursist);
    }
    
    //Retourneert een cursist uit de database met een bepaald id.
    public Cursist getCursistById(int id) {
        String SQL = String.format("SELECT * FROM Cursist WHERE id = %d", id);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                return new Cursist(
                        rs.getInt("ID"),
                        rs.getString("EmailAdres"),
                        rs.getString("Naam"),
                        rs.getDate("GeboorteDatum").toLocalDate(),
                        Geslacht.fromString(rs.getString("Geslacht")),
                        rs.getString("Straat"),
                        rs.getString("Huisnummer"),
                        rs.getString("Postcode"),
                        rs.getString("Woonplaats"),
                        rs.getString("Land")          
                );
            }
        } catch (SQLException ex) {
        }
        return null;
    }
}

