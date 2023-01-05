/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.ContentItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nikki
 */
public class ContentItemDB {
    public ArrayList<ContentItem> getAllContentItems() {
        ResultSet rs = DB.execWithRS("SELECT * FROM ContentItem");
        ArrayList<ContentItem> allContentItems = new ArrayList<>();
        try {
            while (rs.next()) {
                allContentItems.add(new ContentItem(
                        rs.getInt("ID"),
                        rs.getInt("ContentItemNummer"),
                        rs.getDate("PublicatieDatum").toLocalDate(),
                        rs.getString("Status")   
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return allContentItems;
    }
    
    public void addContentItem(ContentItem c) {
        String addContentItem = String.format("INSERT INTO ContentItem VALUES (%d,\'%s\',\'%s\')",
                c.getNummer(),
                c.getPublicatiedatum().toString(),
                c.getStatus()               
                );
        DB.exec(addContentItem);
    }
}
