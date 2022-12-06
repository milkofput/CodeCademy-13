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
public abstract class ContentItem {
   protected int id;
   protected String title;
   protected String description;
   protected LocalDate publicationDate;
   //TODO: protected Status_Enum status;

    public ContentItem(String title, int id, String description, LocalDate publicationDate) {
        this.title = title;
        this.id = id;
        this.publicationDate = publicationDate;
    }
   
   @Override
   public String toString() {
       //TODO
       return null;
   }
}
