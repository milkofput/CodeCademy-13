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
    
    private final CursistDB cursistdb = new CursistDB();
    private final CursusDB cursusdb = new CursusDB();
    
    public ArrayList<Inschrijving> getAllInschrijvingen() {
        ResultSet rs = DB.execWithRS("SELECT * FROM Inschrijving");
        ArrayList<Inschrijving> allInschrijvingen = new ArrayList<>();
        try {
            while (rs.next()) {
                allInschrijvingen.add(new Inschrijving(
                        rs.getInt("ID"),
                        cursusdb.getCursusById(rs.getInt("CursusID")),
                        cursistdb.getCursistById(rs.getInt("CursistID")),
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
        String addInschrijving = String.format("INSERT INTO Inschrijving VALUES (%d,%d,\'%s\',%d)",
                c.getCursus().getId(),
                c.getCursist().getId(),
                c.getDatum().toString(),
                c.getVoortgang());
        DB.exec(addInschrijving);
    }
    
    public void updateInschrijving(Inschrijving oldI, Inschrijving newI) {
        String updateInschrijving = String.format("UPDATE Inschrijving SET "
            + "CursusID=%d,"
            + "CursistID=%d,"
            + "Datum=\'%s\',"
            + "Voortgang=%d,"              
            + "WHERE ID=%d", newI.getCursus().getId(),newI.getCursist().getId(), newI.getDatum().toString(),newI.getVoortgang(),oldI.getId());
        DB.exec(updateInschrijving);
    }
    
    public boolean deleteInschrijving(Inschrijving i) {
        String removeInschrijving = String.format("DELETE FROM Inschrijving WHERE ID=%d",i.getId());
        return DB.exec(removeInschrijving);
    }
    
    public Inschrijving getInschrijvingById(int id) {
        String SQL = String.format("SELECT * FROM Inschrijving WHERE id = %d", id);
        ResultSet rs = DB.execWithRS(SQL);
        try {
            while (rs.next()) {
                return new Inschrijving(
                        rs.getInt("ID"),
                        cursusdb.getCursusById(rs.getInt("CursusID")),
                        cursistdb.getCursistById(rs.getInt("CursistID")),
                        rs.getDate("Datum").toLocalDate(),
                        rs.getInt("Voortgang")                             
                );
            }
        } catch (SQLException ex) {
        }
        return null;
    }
}
