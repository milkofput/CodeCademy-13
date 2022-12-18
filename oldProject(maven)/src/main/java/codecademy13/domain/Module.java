/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecademy13.domain;

import java.time.LocalDate;

/**
 *
 * @author Milko
 */
public class Module extends ContentItem {
    private int version;
    private String description;
    private String contactName;
    private String contactEmail;
    private int number;

    public Module(String title, int id,  String description, LocalDate publicationDate, int version, String contactName, String contactEmail, int number) {
        super(title, id, description, publicationDate);
        this.version = version;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.number = number;
    }

    @Override
    public String toString() {
        //TODO
        return null;
    }
    
}
