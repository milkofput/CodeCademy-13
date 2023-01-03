/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.domain;

import java.time.LocalDate;

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
        return "Cursist{" + "id=" + id + ", emailAdres=" + emailAdres + ", naam=" + naam + ", geboortedatum=" + geboortedatum + ", geslacht=" + geslacht + ", straat=" + straat + ", huisnr=" + huisnr + ", postcode= " + postcode + ", woonplaats=" + woonplaats + ", land=" + land + '}';
    }
    
    
    
    
}
