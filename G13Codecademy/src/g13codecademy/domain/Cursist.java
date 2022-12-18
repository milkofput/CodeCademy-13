/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g13codecademy.domain;

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
    private String adres;
    private String woonplaats;
    private String land;

    public Cursist(int id, String emailAdres, String naam, LocalDate geboortedatum, Geslacht geslacht, String adres, String woonplaats, String land) {
        this.id = id;
        this.emailAdres = emailAdres;
        this.naam = naam;
        this.geboortedatum = geboortedatum;
        this.geslacht = geslacht;
        this.adres = adres;
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

    public String getAdres() {
        return adres;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public String getLand() {
        return land;
    }
    
    
    
    @Override
    public String toString() {
        return "Cursist{" + "id=" + id + ", emailAdres=" + emailAdres + ", naam=" + naam + ", geboortedatum=" + geboortedatum + ", geslacht=" + geslacht + ", adres=" + adres + ", woonplaats=" + woonplaats + ", land=" + land + '}';
    }
    
    
    
    
}
