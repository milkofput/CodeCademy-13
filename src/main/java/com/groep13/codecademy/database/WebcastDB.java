package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.Webcast;
import com.groep13.codecademy.domain.Status;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * WebcastDB beheert de informatie uit de database voor gegevens uit de 'Webcast' tabel.
 */
public class WebcastDB {
    
    //Maakt voor elke record in de 'Webcast' tabel een Webcast en retourneert deze in een ArrayList.
    public ArrayList<Webcast> getAllWebcasts() {
        ResultSet rs = DB.execWithRS("SELECT ContentItem.ID, ContentItem.ContentItemNummer,\n" +
            "ContentItem.PublicatieDatum, ContentItem.Status, Titel, Beschrijving, Spreker.NaamSpreker,\n" +
            "Spreker.Organisatie, Tijdsduur, URL\n" +
            "FROM Webcast JOIN ContentItem\n" +
            "ON Webcast.ContentItemID = ContentItem.ID\n" +
            "JOIN Spreker ON Spreker.SprekerID = Webcast.Spreker;");
        ArrayList<Webcast> allWebcasts = new ArrayList<>();
        try {
            while (rs.next()) {
                allWebcasts.add(new Webcast(
                        rs.getInt("ID"),
                        rs.getInt("ContentItemNummer"),
                        rs.getDate("PublicatieDatum").toLocalDate(),
                        Status.fromString(rs.getString("Status")),                         
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
    
    //Retourneert een inschrijving uit de database met een bepaald id.
    public Webcast getWebcastById(int id) {
        ArrayList<Webcast> allWebcasts = getAllWebcasts()
                .stream()
                .filter(w -> w.getId() == id)
                .collect(Collectors.toCollection(ArrayList::new));
        for (Webcast webcast:allWebcasts) {
            if (webcast.getId()==id) return webcast;
        }
        return null;
    }

}
