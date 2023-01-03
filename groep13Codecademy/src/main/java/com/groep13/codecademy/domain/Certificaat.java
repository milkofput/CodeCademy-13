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
public class Certificaat {
    
    private int id;
    private int cijfer;
    private String medewerker;
    private int nummer;
    private int inschrijvingID;

    public Certificaat(int id, int cijfer, String medewerker, int nummer, int inschrijvingID) {
        this.id = id;
        this.cijfer = cijfer;
        this.medewerker = medewerker;
        this.nummer = nummer;
        this.inschrijvingID = inschrijvingID;
    }

    public int getId() {
        return id;
    }

    public int getCijfer() {
        return cijfer;
    }

    public String getMedewerker() {
        return medewerker;
    }

    public int getNummer() {
        return nummer;
    }

    public int getInschrijvingID() {
        return inschrijvingID;
    }
    
    
}
