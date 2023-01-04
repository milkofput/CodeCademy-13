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
    private int voortgang;

    public Inschrijving(int id, Cursus cursus, Cursist cursist, LocalDate datum, int voortgang) {
        this.id = id;
        this.cursus = cursus;
        this.cursist = cursist;
        this.datum = datum;
        this.voortgang = voortgang;
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

    public int getVoortgang() {
        return voortgang;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.cursus);
        hash = 59 * hash + Objects.hashCode(this.cursist);
        hash = 59 * hash + Objects.hashCode(this.datum);
        hash = 59 * hash + this.voortgang;
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
        if (this.voortgang != other.voortgang) {
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
    
}
