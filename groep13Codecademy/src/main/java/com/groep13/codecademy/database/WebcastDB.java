/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Webcast;
import com.groep13.codecademy.database.ContentItemDB;
import com.groep13.codecademy.domain.ContentItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author nikki
 */
public class WebcastDB {
    
    private final ContentItemDB cdb = new ContentItemDB();
    public ArrayList<Webcast> getAllWebcasts() {
        ResultSet rs = DB.execWithRS("SELECT ContentItem.ID, ContentItem.ContentItemNummer," +
                " ContentItem.PublicatieDatum, ContentItem.Status, Titel, Beschrijving, NaamSpreker," +
                " Organisatie, Tijdsduur, URL" +
                " FROM Webcast JOIN ContentItem\n" +
                "ON Webcast.ContentItemID = ContentItem.ID");
        ArrayList<Webcast> allWebcasts = new ArrayList<>();
        try {
            while (rs.next()) {
                allWebcasts.add(new Webcast(
                        rs.getInt("ID"),
                        rs.getInt("ContentItemNummer"),
                        rs.getDate("PublicatieDatum").toLocalDate(),
                        rs.getString("Status"),                         
                        rs.getString("Titel"),
                        rs.getString("Beschrijving"),
                        rs.getString("NaamSpreker"),
                        rs.getString("Organisatie"),
                        rs.getTime("Tijdsduur"),
                        rs.getString("URL")
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allWebcasts;
    }

}
