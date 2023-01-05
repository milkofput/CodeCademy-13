/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groep13.codecademy.database;

import com.groep13.codecademy.domain.ContentItem;
import com.groep13.codecademy.domain.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nikki
 */
public class ContentItemDB {
    public ArrayList<ContentItem> getAllContentItems() {
        //GET ALL MODULES AND WEBCASTS
        return null;
    }
    
    public int addContentItemAndReturnId(ContentItem c) {
        String addContentItem = String.format("INSERT INTO ContentItem VALUES (%d,\'%s\',\'%s\')",
                c.getNummer(),
                c.getPublicatiedatum().toString(),
                c.getStatus()               
                );
        DB.exec(addContentItem);
        String getId = String.format("SELECT ID FROM ContentItem WHERE ContentItemNummer = %d",
                c.getNummer()
        );
        ResultSet rs = DB.execWithRS(getId);
        try {
            while (rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return -1;
    }
}
