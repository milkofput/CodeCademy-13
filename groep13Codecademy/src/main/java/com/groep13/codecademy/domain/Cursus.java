/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.domain;



/**
 *
 * @author nikki
 */
public class Cursus {
    private int id;
    private String naam;
    private String onderwerp;
    private String introductietekst;
    private String niveauaanduiding;

    public Cursus(int id, String naam, String onderwerp, String introductietekst, String niveauaanduiding) {
        this.id = id;
        this.naam = naam;
        this.onderwerp = onderwerp;
        this.introductietekst = introductietekst;
        this.niveauaanduiding = niveauaanduiding;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getOnderwerp() {
        return onderwerp;
    }

    public String getIntroductietekst() {
        return introductietekst;
    }

    public String getNiveauaanduiding() {
        return niveauaanduiding;
    }
    
    
    
}
