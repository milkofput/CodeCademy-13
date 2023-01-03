/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.domain;

import java.time.LocalDate;

/**
 *
 * @author nikki
 */
public class Inschrijving {
    private int id;
    // nog veranderen in Cursus cursus en Cursist cursist??
    private Cursus cursusID;
    private Cursist cursistID;
    private LocalDate datum;
    private int voortgang;

    public Inschrijving(int id, Cursus cursusID, Cursist cursistID, LocalDate datum, int voortgang) {
        this.id = id;
        this.cursusID = cursusID;
        this.cursistID = cursistID;
        this.datum = datum;
        this.voortgang = voortgang;
    }

    public int getId() {
        return id;
    }

    public Cursus getCursusID() {
        return cursusID;
    }

    public Cursist getCursistID() {
        return cursistID;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public int getVoortgang() {
        return voortgang;
    }
    
    
}
