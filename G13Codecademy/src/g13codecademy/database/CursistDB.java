/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g13codecademy.database;

import g13codecademy.domain.Cursist;
import g13codecademy.domain.Geslacht;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milko
 * 
 */
public class CursistDB {
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
                        rs.getString("Adres"),
                        rs.getString("Woonplaats"),
                        rs.getString("Land")          
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allCursisten;
    }
    
    public void addCursist(Cursist c) {
        String addCursist = String.format("INSERT INTO Cursist VALUES (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                c.getEmailAdres(),
                c.getNaam(),
                c.getGeboortedatum().toString(),
                c.getGeslacht().getGeslachtNaam(),
                c.getAdres(),
                c.getWoonplaats(),
                c.getLand());
        DB.exec(addCursist);
    }
    
    public void updateCursist(Cursist oldC, Cursist newC) {
        String updateCursist = String.format("UPDATE Cursist SET "
            + "EmailAdres=\'%s\',"
            + "Naam=\'%s\',"
            + "GeboorteDatum=\'%s\',"
            + "Geslacht=\'%s\',"
            + "Adres=\'%s\',"
            + "Woonplaats=\'%s\',"
            + "Land=\'%s\'"              
            + "WHERE ID=%d", newC.getEmailAdres(),newC.getNaam(), newC.getGeboortedatum().toString(),newC.getGeslacht().getGeslachtNaam(),
            newC.getAdres(),newC.getWoonplaats(),newC.getLand(),oldC.getId());
        DB.exec(updateCursist);
    }
    
    public void deleteCursist(Cursist c) {
        String removeCursist = String.format("DELETE FROM Cursist WHERE ID=%d",c.getId());
        DB.exec(removeCursist);
    }
}
