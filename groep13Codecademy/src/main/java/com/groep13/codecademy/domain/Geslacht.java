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
public enum Geslacht {
    MAN("Man"), VROUW("Vrouw"), ANDERS("Anders");
    
    private String geslachtNaam;
    
    Geslacht(String geslachtNaam) {
        this.geslachtNaam = geslachtNaam;
    }

    public String getGeslachtNaam() {
        return geslachtNaam;
    }
    
    public static Geslacht fromString(String s) {
        if (s.equals("Man")) return Geslacht.MAN;
        if (s.equals("Vrouw")) return Geslacht.VROUW;
        if (s.equals("Anders")) return Geslacht.ANDERS;
        return Geslacht.ANDERS;
    }
    
}
