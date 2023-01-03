/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Cursist;
import com.groep13.codecademy.domain.Geslacht;
import com.groep13.codecademy.domain.Inschrijving;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nikki
 */
public class InschrijvingDB {
    
    public ArrayList<Inschrijving> getAllInschrijvingen() {
        ResultSet rs = DB.execWithRS("SELECT * FROM Inschrijving");
        ArrayList<Inschrijving> allInschrijvingen = new ArrayList<>();
        try {
            while (rs.next()) {
                allInschrijvingen.add(new Inschrijving(
                        rs.getInt("ID"),
                        rs.getInt("CursusID"),
                        rs.getInt("CursistID"),
                        rs.getDate("Datum").toLocalDate(),
                        rs.getInt("Voortgang")                             
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allInschrijvingen;
    }
    
    public void addInschrijving(Inschrijving c) {
        String addInschrijving = String.format("INSERT INTO Inschrijving VALUES (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
                c.getCursusID(),
                c.getCursistID(),
                c.getDatum().toString(),
                c.getVoortgang());
        DB.exec(addInschrijving);
    }
    
    public void updateInschrijving(Inschrijving oldC, Inschrijving newC) {
        String updateInschrijving = String.format("UPDATE Inschrijving SET "
            + "CursusID=\'%s\',"
            + "CursistID=\'%s\',"
            + "Datum=\'%s\',"
            + "Voortgang=\'%s\',"              
            + "WHERE ID=%d", newC.getCursusID(),newC.getCursistID(), newC.getDatum().toString(),newC.getVoortgang(),oldC.getId());
        DB.exec(updateInschrijving);
    }
    
    public void deleteInschrijving(Inschrijving c) {
        String removeInschrijving = String.format("DELETE FROM Inschrijving WHERE ID=%d",c.getId());
        DB.exec(removeInschrijving);
    }
}
