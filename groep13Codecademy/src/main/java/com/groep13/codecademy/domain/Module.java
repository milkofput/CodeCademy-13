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
public class Module {
    private int id;
    private String titel;
    private String versie;
    private String beschrijving;
    private String naamcontactpersoon;
    private String emailcontactpersoon;
    private String volgnummer;
    private String cursusid;
    private String contentitemid;

    public Module(int id, String titel, String versie, String beschrijving, String naamcontactpersoon, String emailcontactpersoon, String volgnummer, String cursusid, String contentitemid) {
        this.id = id;
        this.titel = titel;
        this.versie = versie;
        this.beschrijving = beschrijving;
        this.naamcontactpersoon = naamcontactpersoon;
        this.emailcontactpersoon = emailcontactpersoon;
        this.volgnummer = volgnummer;
        this.cursusid = cursusid;
        this.contentitemid = contentitemid;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getVersie() {
        return versie;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getNaamcontactpersoon() {
        return naamcontactpersoon;
    }

    public String getEmailcontactpersoon() {
        return emailcontactpersoon;
    }

    public String getVolgnummer() {
        return volgnummer;
    }

    public String getCursusid() {
        return cursusid;
    }

    public String getContentitemid() {
        return contentitemid;
    }
 
    
    
}
