/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author nikki
 */
public class Inschrijving {
    private int id;
    // nog veranderen in Cursus cursus en Cursist cursist??
    private Cursus cursus;
    private Cursist cursist;
    private LocalDate datum;

    public Inschrijving(int id, Cursus cursus, Cursist cursist, LocalDate datum) {
        this.id = id;
        this.cursus = cursus;
        this.cursist = cursist;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public Cursus getCursus() {
        return cursus;
    }

    public Cursist getCursist() {
        return cursist;
    }

    public LocalDate getDatum() {
        return datum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.cursus);
        hash = 59 * hash + Objects.hashCode(this.cursist);
        hash = 59 * hash + Objects.hashCode(this.datum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inschrijving other = (Inschrijving) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cursus, other.cursus)) {
            return false;
        }
        if (!Objects.equals(this.cursist, other.cursist)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Cursist: " + cursist.getNaam() + ", cursus: " + cursus.getNaam();
    }
    
}
