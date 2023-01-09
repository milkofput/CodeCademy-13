/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.domain;

/**
 *
 * @author milko
 */
public enum Status {
    CONCEPT("Concept"), ACTIEF("Actief"), GEARCHIVEERD("Gearchiveerd");
    
    private String statusNaam;
    
    Status(String statusNaam) {
        this.statusNaam = statusNaam;
    }

    public String getGeslachtNaam() {
        return statusNaam;
    }
    
    public static Status fromString(String s) {
        if (s.equals("Concept")) return Status.CONCEPT;
        if (s.equals("Actief")) return Status.ACTIEF;
        if (s.equals("Gearchiveerd")) return Status.GEARCHIVEERD;
        return Status.CONCEPT;
    }
}
