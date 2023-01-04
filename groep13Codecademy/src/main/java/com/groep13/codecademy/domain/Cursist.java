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
 * @author milko
 */
public class Cursist {
    private int id;
    private String emailAdres;
    private String naam;
    private LocalDate geboortedatum;
    private Geslacht geslacht;
    private String straat;
    private String huisnr;
    private String postcode;
    private String woonplaats;
    private String land;

    public Cursist(int id, String emailAdres, String naam, LocalDate geboortedatum, Geslacht geslacht, String straat, String huisnr, String postcode, String woonplaats, String land) {
        this.id = id;
        this.emailAdres = emailAdres;
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.geslacht = geslacht;
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
        this.land = land;
    }

    public int getId() {
        return id;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public String getNaam() {
        return naam;
    }

    public LocalDate getGeboortedatum() {
        return geboortedatum;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public String getStraat() {
        return straat;
    }
    
    public String getHuisnr() {
        return huisnr;
    }
    
    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String getLand() {
        return land;
    }
    
    public String getWoonadres() {
        return straat + " " + huisnr + " " + postcode + " " + woonplaats + " " + land;
    }
    
    @Override
    public String toString() {
        return emailAdres;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.emailAdres);
        hash = 37 * hash + Objects.hashCode(this.naam);
        hash = 37 * hash + Objects.hashCode(this.geboortedatum);
        hash = 37 * hash + Objects.hashCode(this.geslacht);
        hash = 37 * hash + Objects.hashCode(this.straat);
        hash = 37 * hash + Objects.hashCode(this.huisnr);
        hash = 37 * hash + Objects.hashCode(this.postcode);
        hash = 37 * hash + Objects.hashCode(this.woonplaats);
        hash = 37 * hash + Objects.hashCode(this.land);
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
        final Cursist other = (Cursist) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.emailAdres, other.emailAdres)) {
            return false;
        }
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        if (!Objects.equals(this.straat, other.straat)) {
            return false;
        }
        if (!Objects.equals(this.huisnr, other.huisnr)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.woonplaats, other.woonplaats)) {
            return false;
        }
        if (!Objects.equals(this.land, other.land)) {
            return false;
        }
        if (!Objects.equals(this.geboortedatum, other.geboortedatum)) {
            return false;
        }
        if (this.geslacht != other.geslacht) {
            return false;
        }
        return true;
    }
    
    
}
