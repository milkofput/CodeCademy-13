/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecademy13.domain;

import java.time.Duration;
import java.time.LocalDate;

/**
 *
 * @author Milko
 */
public class Webcast extends ContentItem {
    private int version;
    private String speakerName;
    private String speakerCompany;
    private Duration duration;
    private String url;

    public Webcast(String title, int id, String description, LocalDate publicationDate, int version, String speakerName, String speakerCompany, Duration duration, String url) {
        super(title, id, description, publicationDate);
        this.version = version;
        this.speakerName = speakerName;
        this.speakerCompany = speakerCompany;
        this.duration = duration;
        this.url = url;
    }

    @Override
    public String toString() {
        //TODO
        return null;
    }
}
