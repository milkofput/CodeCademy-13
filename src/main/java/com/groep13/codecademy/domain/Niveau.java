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
public enum Niveau {
    BEGINNER("Beginner"), GEVORDERD("Gevorderd"), EXPERT("Expert");
    
    private String niveauNaam;
    
    Niveau(String niveauNaam) {
        this.niveauNaam = niveauNaam;
    }

    public String getNiveauNaam() {
        return niveauNaam;
    }
    
    public static Niveau fromString(String s) {
        if (s.equals("Beginner")) return Niveau.BEGINNER;
        if (s.equals("Gevorderd")) return Niveau.GEVORDERD;
        if (s.equals("Expert")) return Niveau.EXPERT;
        return Niveau.BEGINNER;
    }
}
